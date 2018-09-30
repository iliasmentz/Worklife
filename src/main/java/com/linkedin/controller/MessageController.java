package com.linkedin.controller;

import com.linkedin.entities.model.messages.ChatOverviewDto;
import com.linkedin.entities.model.messages.MessageDto;
import com.linkedin.entities.model.messages.UserChatDto;
import com.linkedin.security.AuthenticationFacade;
import com.linkedin.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = MessageController.tag)
@RestController
@RequestMapping("/api/messages/")
public class MessageController {
	public static final String tag = "Messages";

	private final MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@GetMapping("/")
	@ApiOperation(value = "Messages", notes = "Returns all users that the authorized user has communicated with", response = ChatOverviewDto.class)
	public List<ChatOverviewDto> getInbox() {
		return messageService.getUsersMessages(AuthenticationFacade.getUserId());
	}

	@GetMapping("/{userId}")
	@ApiOperation(value = "Chat", notes = "Returns the historty of messages with a specific user", response = MessageDto.class)
	public UserChatDto getSingleChat(@PathVariable Long userId) {
		return messageService.getChatMessages(AuthenticationFacade.getUserId(), userId);
	}

	@PostMapping("/{userId}")
	@ApiOperation(value = "Message", notes = "Sends a new message to a specific user", response = MessageDto.class)
	public UserChatDto sendNewMessage(@PathVariable Long userId, @RequestBody String message) {
		messageService.sendMessageToUser(AuthenticationFacade.getUserId(), userId, message);
		return getSingleChat(userId);
	}

}
