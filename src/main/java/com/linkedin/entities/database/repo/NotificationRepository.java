package com.linkedin.entities.database.repo;

import com.linkedin.entities.database.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository  extends JpaRepository<Notification,Long> {
}
