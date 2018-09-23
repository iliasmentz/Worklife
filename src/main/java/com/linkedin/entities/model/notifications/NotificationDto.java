package com.linkedin.entities.model.notifications;

import com.linkedin.entities.model.Comment.CommentDto;
import com.linkedin.entities.model.Post.PostDto;
import com.linkedin.entities.model.UserSimpleDto;
import com.linkedin.entities.model.connection.ConnectionRequestDto;
import com.linkedin.entities.model.likes.LikeDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(description = "Notification response object")
public class NotificationDto {


  @ApiModelProperty(value = "NotificationId", example = "1", position = 1)
  public Long notificationId;

  @ApiModelProperty(value = "UserSimpleDto", example = "userSimpleDto}", position = 2)
  public UserSimpleDto user;

  @ApiModelProperty(value = "target_user_id", example = "1", position = 3)
  public Long targetUserId;

  @ApiModelProperty(value = "status", example = "0", position = 4)
  public Integer status;

  @ApiModelProperty(value = "message", example = "User iliasmetz commented on your post ", position = 5)
  public String message;

  @ApiModelProperty(value = "type", example = "0", position = 6)
  public Integer type;

  @ApiModelProperty(value = "1", example = "1", position = 7)
  private Long likeCommentConnectionId ;

}
