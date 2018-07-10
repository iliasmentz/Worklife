package com.linkedin.database.repo;

import com.linkedin.database.Conversation;
import com.linkedin.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation,Long> {

}
