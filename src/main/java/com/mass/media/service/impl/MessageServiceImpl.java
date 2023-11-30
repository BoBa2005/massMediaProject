package com.mass.media.service.impl;

import com.mass.media.dto.Message;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MessageEntity;
import com.mass.media.repository.MessageRepository;
import com.mass.media.service.IMessageService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

  private final MessageRepository messageRepository;

  public MessageServiceImpl(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public List<MessageEntity> findAll() {
    return (List<MessageEntity>) messageRepository.findAll();
  }

  @Override
  public OperationResponse saveMessage(Message message) {
    OperationResponse operationResponse = this.validateMessage(message);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    MessageEntity messageEntity = new MessageEntity();
    messageEntity.setMediaId(message.getMediaId());
    messageEntity.setAuthorId(message.getAuthorId());
    messageEntity.setAttachmentId(message.getAttachmentId());
    messageEntity.setTitle(message.getTitle());
    messageEntity.setMessage(message.getMessage());
    messageEntity.setCreateDate(message.getCreateDate() != null ? message.getCreateDate() : new Date());
    messageRepository.save(messageEntity);
    return new OperationResponse("SUCCESS", "Message saved");
  }

  @Override
  public OperationResponse deleteMessage(Long messageId) {
    if (messageRepository.existsById(messageId)) {
      messageRepository.deleteById(messageId);
      return new OperationResponse("SUCCESS", "Message deleted");
    }
    return new OperationResponse("ERROR", "Message doesn't exists. Message id - " + messageId);
  }

  @Override
  public List<MessageEntity> findMessagesByMediaName(String mediaName) {
    return messageRepository.findMessagesByMediaName(mediaName);
  }

  @Override
  public List<MessageEntity> findMessagesByAuthorFirstName(String firstName) {
    return messageRepository.findMessagesByAuthorFirstName(firstName);
  }

  @Override
  public List<MessageEntity> findMessagesByAuthorLastName(String lastName) {
    return messageRepository.findMessagesByAuthorLastName(lastName);
  }

  @Override
  public List<MessageEntity> findMessagesByAttachmentType(String attachmentType) {
    return messageRepository.findMessagesByAttachmentType(attachmentType);
  }

  @Override
  public List<MessageEntity> findByMessageLike(String message) {
    return messageRepository.findByMessageLike(message);
  }

  private OperationResponse validateMessage(Message message) {
    if (message == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (message.getMediaId() == null) {
      return new OperationResponse("ERROR", "Media Id is required");
    }
    if (StringUtils.isEmpty(message.getMessage())) {
      return new OperationResponse("ERROR", "Message is required");
    }
    return new OperationResponse("SUCCESS", "Message valid");
  }

}
