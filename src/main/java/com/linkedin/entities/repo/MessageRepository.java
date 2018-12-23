package com.linkedin.entities.repo;

import com.linkedin.entities.Message;
import com.linkedin.features.messages.model.ChatOverviewDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

  @Query(" select new com.linkedin.entities.model.messages.ChatOverviewDto( "
      + " u.id"
      + ", u.name"
      + ", (SELECT context FROM Message where sentDate = max(m.sentDate) and (senderId = u.id or recipientId = u.id) )"
      + ", max(m.sentDate)"
      + ", false "
      + ", u.imgPath"
      + " )"
      + " from User u inner join Message m on ((m.senderId = u.id or m.recipientId = u.id) and u.id != :userId) "
      + " where  m.recipientId=:userId or m.senderId=:userId "
      + " group by u.id "
  )
  List<ChatOverviewDto> findAllUsersThatUsersHasCommunicateWith(@Param("userId") Long userId);

  @Query("select m from Message m where (m.recipientId = :userId0 and m.senderId = :userId1 ) or (m.senderId = :userId0 and m.recipientId = :userId1) order by m.sentDate asc")
  List<Message> findAllMessagesBeetwenUsers(@Param("userId0") Long userId0, @Param("userId1") Long userId1);
}
