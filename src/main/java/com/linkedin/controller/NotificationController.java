package com.linkedin.controller;

import com.linkedin.entities.database.Notification;
import com.linkedin.entities.model.notifications.NotificationDto;
import com.linkedin.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = NotificationController.tag)
@RestController
@RequestMapping("/api/notifications/")
public class NotificationController {
  public static final String  tag = "Notifications Controller";

  public final NotificationService notificationService;

  @Autowired
  public NotificationController(NotificationService notificationService){
	this.notificationService = notificationService;
  }


  @GetMapping("/")
  @ApiOperation(value = "Posts", notes = "Returns All Notifications of logged User", response = NotificationDto.class,responseContainer = "List")
  public List<NotificationDto> getAllUsersNotifications() {
	return notificationService.getNotifications();
  }

}
