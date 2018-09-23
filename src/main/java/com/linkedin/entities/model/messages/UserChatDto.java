package com.linkedin.entities.model.messages;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserChatDto {

	@ApiModelProperty(value = "userId", example = "5", position = 1)
	private Long userId;

	@ApiModelProperty(value = "messages", position = 2)
	private List<MessageDto> messages;

	@ApiModelProperty(value = "lastMessage", example = "Goodnight!", position = 3)
	private String lastMessage;

	@ApiModelProperty(value = "lastMessageTime", example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", position = 4)
	private Date lastMessageTime;

	@ApiModelProperty(value = "hasUnreadMessage", example = "false", position = 5)
	private Boolean hasUnreadMessage;
}
