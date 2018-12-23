package com.linkedin.features.notifications;

import com.linkedin.entities.Notification;
import com.linkedin.entities.User;
import com.linkedin.entities.repo.UserRepository;
import com.linkedin.features.users.UserConverter;
import com.linkedin.model.notifications.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Autowired
  public NotificationConverter(UserRepository userRepository, UserConverter userConverter) {

    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  public NotificationDto toNotificationDto(Notification notification) {
    NotificationDto notificationDto = new NotificationDto();
    notificationDto.setMessage(notification.getMessage());
    notificationDto.setNotificationId(notification.getNotificationId());
    notificationDto.setStatus(notification.getStatus());
    notificationDto.setType(notification.getType());
    notificationDto.setUser(userConverter.toUserSimpleDto(userRepository.findById(notification.getUserId()).orElse(new User())));
    notificationDto.setTargetUserId(notification.getTargetUserId());
    notificationDto.setLikeCommentConnectionId(notification.getLikeCommentConnectionId());

    return notificationDto;
  }
}
