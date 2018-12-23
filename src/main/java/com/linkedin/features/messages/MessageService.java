package com.linkedin.features.messages;

import com.linkedin.config.security.AuthenticationFacade;
import com.linkedin.entities.Message;
import com.linkedin.entities.repo.MessageRepository;
import com.linkedin.features.files.FileService;
import com.linkedin.features.messages.model.ChatOverviewDto;
import com.linkedin.features.messages.model.MessageDto;
import com.linkedin.features.messages.model.UserChatDto;
import com.linkedin.features.users.UserConverter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
  private final MessageRepository messageRepository;
  private final UserConverter userConverter;

  public MessageService(MessageRepository messageRepository, UserConverter userConverter) {
    this.messageRepository = messageRepository;
    this.userConverter = userConverter;
  }


  public List<ChatOverviewDto> getUsersMessages(Long userId) {
    List<ChatOverviewDto> chatOverview = messageRepository.findAllUsersThatUsersHasCommunicateWith(userId);
    updateIconPaths(chatOverview);
    return chatOverview.stream()
        .sorted(Comparator.comparing(ChatOverviewDto::getLastMessageTime))
        .collect(Collectors.toList());
  }

  private void updateIconPaths(List<ChatOverviewDto> chatOverview) {
    for (ChatOverviewDto chatOverviewDto : chatOverview) {
      String icon = chatOverviewDto.getIcon();
      chatOverviewDto.setIcon(FileService.getFileFullUrl(icon));
    }
  }

  public UserChatDto getChatMessages(Long userId1, Long userId2) {
    UserChatDto userChatDto = new UserChatDto();
    List<Message> allMessages = messageRepository.findAllMessagesBeetwenUsers(userId1, userId2);
    userChatDto.setUser(userConverter.toUserSimpleDto(userId2));

    if (allMessages.size() != 0) {
      userChatDto.setMessages(convertToMessagesDto(allMessages));
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
    messageDto.setSendBy(AuthenticationFacade.getUserId().equals(x.getRecipientId()) ? 0 : 1);
    messageDto.setSendDate(x.getSentDate());
    return messageDto;
  }

  public void sendMessageToUser(Long senderId, Long receiverId, String messageContext) {
    Message message = new Message();
    message.setContext(messageContext);
    message.setRecipientId(receiverId);
    message.setSenderId(senderId);
    message.setSentDate(new Date());
    messageRepository.save(message);
  }
}