package com.linkedin.features.messages.model;

import com.linkedin.model.UserSimpleDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserChatDto {

  @ApiModelProperty(value = "user on chat", position = 1)
  private UserSimpleDto user;

  @ApiModelProperty(value = "messages", position = 2)
  private List<MessageDto> messages;

}
