package com.linkedin.converter;

import com.linkedin.entities.database.Notification;
import com.linkedin.entities.model.notifications.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {

  public NotificationDto toNotificationDto(Notification notification){
    NotificationDto notificationDto = new NotificationDto();
    notificationDto.setMessage(notification.getMessage());
    notificationDto.setNotificationId(notification.getNotificationId());
    notificationDto.setStatus(notification.getStatus());
    notificationDto.setType(notification.getType());
    notificationDto.setUser(notification.getUser());
    notificationDto.setTargetUserId(notification.getTargetUserId());
    return notificationDto;
  }
}
