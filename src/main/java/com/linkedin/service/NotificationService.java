package com.linkedin.service;

import com.linkedin.converter.NotificationConverter;
import com.linkedin.converter.UserConverter;
import com.linkedin.entities.database.Notification;
import com.linkedin.entities.database.User;
import com.linkedin.entities.database.repo.NotificationRepository;
import com.linkedin.entities.database.repo.UserRepository;
import com.linkedin.entities.model.notifications.NotificationDto;
import com.linkedin.security.AuthenticationFacade;
import org.apache.tomcat.util.modeler.NotificationInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

  private final NotificationConverter notificationConverter;
  private final NotificationRepository notificationRepository;
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  public NotificationService(NotificationConverter notificationConverter, NotificationRepository notificationRepository, UserRepository userRepository, UserConverter userConverter) {
	this.notificationConverter = notificationConverter;
	this.notificationRepository = notificationRepository;
	this.userRepository = userRepository;
	this.userConverter = userConverter;
  }

  //userId aytou gia ton opoio proorizetai to notification , type einai 0,1,2 -> like,comment,connectionRequest
  public void createNotification(Long targetUserId, Integer type , Long likeCommentConnectionId) {
	Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();

	Notification notification = new Notification();
	notification.setTargetUserId(targetUserId);
	notification.setStatus(0);
	notification.setType(type);
	// notification.setUser(userConverter.toUserDto(userRepository.findById(logedUserId).orElse(null)));
	//notification.setUser(userRepository.findById(logedUserId).map(userConverter::toUserSimpleDto).orElseThrow(()->new RuntimeException()));
	notification.setUserId(logedUserId);
	notification.setLikeCommentConnectionId(likeCommentConnectionId);
	if (type == 0) {//like
	  notification.setMessage("User : " + userRepository.findById(logedUserId).get().getUsername() + " liked your post");
	} else if (type == 1) {//comment
	  notification.setMessage("User : " + userRepository.findById(logedUserId).get().getUsername() + " commented on  your post");
	} else if (type == 2) {//connectionRequest
	  notification.setMessage("User : " + userRepository.findById(logedUserId).get().getUsername() + " requested to add you to his Network ");
	}
	notificationRepository.save(notification);
  }

  public List<NotificationDto> getNotifications() {
	Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();
	return notificationRepository.findAllByTargetUserId(logedUserId).stream().map(notificationConverter::toNotificationDto).collect(Collectors.toList());
  }

  public void makeNotificationSeen(Long notificationId) {
    Notification notification = notificationRepository.findById(notificationId).orElseThrow(()-> new RuntimeException());
    notification.setStatus(1);
    notificationRepository.save(notification);
  }
}
