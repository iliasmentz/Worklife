package com.linkedin.entities.model.messages;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatOverviewDto {

	@ApiModelProperty(value = "userId", example = "11", position = 1)
	private Long userId;

	@ApiModelProperty(value = "name", example = "Marinos", position = 2)
	private String name;

	@ApiModelProperty(value = "lastMessage", example = "Hello !", position = 3)
	private String lastMessage;

	@ApiModelProperty(value = "lastMessageTime", example = "2003-10-31T06:30:00.000+0000", position = 4)
	private Date lastMessageTime;

	@ApiModelProperty(value = "hasUnreadMessage", example = "always false", position = 5)
	private Boolean hasUnreadMessage;

	@ApiModelProperty(value = "icon", example = "https://...", position = 6)
	private String icon;
}
