package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository  extends JpaRepository<Message, Long> {
}
