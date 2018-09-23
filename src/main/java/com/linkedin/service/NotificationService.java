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
  public void  createNotification(Long userId , Integer type){
	Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();

	Notification notification = new Notification();
	notification.setTargetUserId(userId);
	notification.setStatus(0);
	notification.setType(type);
   // notification.setUser(userConverter.toUserDto(userRepository.findById(logedUserId).orElse(null)));
	notification.setUser(userRepository.findById(logedUserId).map(userConverter::toUserSimpleDto).orElseThrow(()->new RuntimeException()));

//	if(type==0)//like
//	{
//
//	}
//	else if (type==1)
	notificationRepository.save(notification);
  }

  public List<NotificationDto> getNotifications() {
	Long logedUserId = AuthenticationFacade.authenticatedUser().getUserId();
	try {
	  notificationRepository.findAll();
	}
	catch (Exception e){
	  System.out.println(e.getMessage());
	}
	//return null;
	return  notificationRepository.findAllByTargetUserId(logedUserId).stream().map(notificationConverter::toNotificationDto).collect(Collectors.toList());
	//return notificationRepository.findAllByTargetUserId(logedUserId).stream().map(notificationConverter ::toNotificationDto).collect(Collectors.toList());
  }
}
