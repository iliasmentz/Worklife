package com.linkedin.entities.model.messages;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {

		@ApiModelProperty(value = "message", example = "Hello", position = 0)
		private String message;

		@ApiModelProperty(value = "senderUserId", example = "1", position = 1)
		private Long senderUserId;

		@ApiModelProperty(value = "receiverUserId", example = "2", position = 2)
		private Long receiverUserId;

		@ApiModelProperty(value = "sendDate", example = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", position = 3)
		private Date sendDate;
}
