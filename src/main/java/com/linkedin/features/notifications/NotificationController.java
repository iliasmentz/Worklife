package com.linkedin.features.notifications;

import com.linkedin.model.notifications.NotificationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = NotificationController.tag)
@RestController
@RequestMapping("/api/notifications/")
public class NotificationController {
  public static final String tag = "Notifications Controller";

  private final NotificationService notificationService;

  @Autowired
  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }


  @GetMapping("/")
  @ApiOperation(value = "Posts", notes = "Returns All Notifications of logged User", response = NotificationDto.class, responseContainer = "List")
  public List<NotificationDto> getAllUsersNotifications() {
    return notificationService.getNotifications();
  }

  @PostMapping("/{notificationId}")
  @ApiOperation(value = "Posts", notes = "Posts that the Notification is seen")
  public void makeNotificationSeen(@PathVariable Long notificationId) {
    notificationService.makeNotificationSeen(notificationId);
  }

}
