package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation,Long> {

}
