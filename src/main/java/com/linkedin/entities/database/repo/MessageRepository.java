package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Notification,Long> {
}
