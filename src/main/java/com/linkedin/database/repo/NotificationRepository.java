package com.linkedin.database.repo;

import com.linkedin.database.Notification;
import com.linkedin.database.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository  extends JpaRepository<Notification,Long> {
}
