package com.linkedin.service;

import com.linkedin.entities.database.Message;
import com.linkedin.entities.database.repo.MessageRepository;
import com.linkedin.entities.model.messages.ChatOverviewDto;
import com.linkedin.entities.model.messages.MessageDto;
import com.linkedin.entities.model.messages.UserChatDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
	private final MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}


	public List<ChatOverviewDto> getUsersMessages(Long userId) {
		List<ChatOverviewDto> chatOverview = messageRepository.findAllUsersThatUsersHasCommunicateWith(userId);
		updateIconPaths(chatOverview);
		return chatOverview;
	}

	private void updateIconPaths(List<ChatOverviewDto> chatOverview) {
		for (ChatOverviewDto chatOverviewDto : chatOverview) {
			String icon = chatOverviewDto.getIcon();
			chatOverviewDto.setIcon(FileService.getFileFullUrl(icon));
		}
	}

	public UserChatDto getChatMessages(Long userId1, Long userId2) {
		UserChatDto userChatDto = new UserChatDto();
		userChatDto.setHasUnreadMessage(false);

		List<Message> allMessages = messageRepository.findAllMessagesBeetwenUsers(userId1, userId2);

		if (allMessages.size() != 0) {
			userChatDto.setLastMessage(allMessages.get(0).getSentDate().toString());
			userChatDto.setLastMessage(allMessages.get(0).getContext());
			userChatDto.setUserId(userId2);
			userChatDto.setMessages(convertToMessagesDto(allMessages));
		} else {
			return null;
		}
		return userChatDto;
	}

	private List<MessageDto> convertToMessagesDto(List<Message> allMessages) {
		return allMessages.stream()
						.map(this::convertToMessageDto)
						.collect(Collectors.toList());
	}

	private MessageDto convertToMessageDto(Message x) {
		MessageDto messageDto = new MessageDto();
		messageDto.setMessage(x.getContext());
		messageDto.setReceiverUserId(x.getRecipientId());
		messageDto.setSenderUserId(x.getSenderId());
		messageDto.setSendDate(x.getSentDate());
		return messageDto;
	}

	public void sendMessageToUser(Long senderId, Long reiceverId, String messageContext) {
		Message message = new Message();
		message.setContext(messageContext);
		message.setRecipientId(reiceverId);
		message.setSenderId(senderId);
		message.setSentDate(new Date());
		messageRepository.save(message);
	}
}