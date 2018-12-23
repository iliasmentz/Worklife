package com.linkedin.model.notifications;

import com.linkedin.model.UserSimpleDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Notification response object")
public class NotificationDto {


  @ApiModelProperty(value = "NotificationId", example = "1", position = 1)
  private Long notificationId;

  @ApiModelProperty(value = "UserSimpleDto", example = "userSimpleDto}", position = 2)
  private UserSimpleDto user;

  @ApiModelProperty(value = "target_user_id", example = "1", position = 3)
  private Long targetUserId;

  @ApiModelProperty(value = "status", example = "0", position = 4)
  private Integer status;

  @ApiModelProperty(value = "message", example = "User iliasmetz commented on your post ", position = 5)
  private String message;

  @ApiModelProperty(value = "type", example = "0", position = 6)
  private Integer type;

  @ApiModelProperty(value = "1", example = "1", position = 7)
  private Long likeCommentConnectionId;

}
