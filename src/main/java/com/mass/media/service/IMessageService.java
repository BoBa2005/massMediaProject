package com.mass.media.service;

import com.mass.media.dto.Message;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MessageEntity;

import java.util.List;

public interface IMessageService {

  List<MessageEntity> findAll();

  OperationResponse saveMessage(Message message);

  OperationResponse deleteMessage(Long messageId);

  List<MessageEntity> findMessagesByMediaName(String mediaName);

  List<MessageEntity> findMessagesByAuthorFirstName(String firstName);

  List<MessageEntity> findMessagesByAuthorLastName(String lastName);

  List<MessageEntity> findMessagesByAttachmentType(String attachmentType);

  List<MessageEntity> findByMessageLike(String message);
}
