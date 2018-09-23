package com.linkedin.converter;

import com.linkedin.entities.database.Notification;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.notifications.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Autowired
  public NotificationConverter(UserRepository userRepository, UserConverter userConverter){

    this.userRepository = userRepository;
    this.userConverter = userConverter;
  }

  public NotificationDto toNotificationDto(Notification notification){
    NotificationDto notificationDto = new NotificationDto();
    notificationDto.setMessage(notification.getMessage());
    notificationDto.setNotificationId(notification.getNotificationId());
    notificationDto.setStatus(notification.getStatus());
    notificationDto.setType(notification.getType());
    notificationDto.setUser( userConverter.toUserSimpleDto( userRepository.findById(notification.getUserId()).orElse(null) ));
    notificationDto.setTargetUserId(notification.getTargetUserId());
    notificationDto.setLikeCommentConnectionId(notification.getLikeCommentConnectionId());

    return notificationDto;
  }
}
