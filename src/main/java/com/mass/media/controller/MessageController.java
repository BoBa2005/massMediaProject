package com.mass.media.controller;


import com.mass.media.dto.Message;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MessageEntity;
import com.mass.media.service.IMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class MessageController {

  private final IMessageService messageService;

  public MessageController(IMessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/messages")
  public ResponseEntity<List<MessageEntity>> findAllMessages() {
    return new ResponseEntity<>(messageService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/message/save")
  public ResponseEntity<OperationResponse> saveMessage(@RequestBody Message message) {
    OperationResponse operationResponse = messageService.saveMessage(message);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/message/delete")
  public ResponseEntity<OperationResponse> deleteMessage(@RequestBody Long messageId) {
    OperationResponse operationResponse = messageService.deleteMessage(messageId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/findMessagesByMediaName/{mediaName}")
  public ResponseEntity<List<MessageEntity>> findMessagesByMediaName(@PathVariable("mediaName") String mediaName) {
    List<MessageEntity> result = messageService.findMessagesByMediaName(mediaName);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findMessagesByAuthorFirstName/{firstName}")
  public ResponseEntity<List<MessageEntity>> findMessagesByAuthorFirstName(@PathVariable("firstName") String firstName) {
    List<MessageEntity> result = messageService.findMessagesByAuthorFirstName(firstName);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findMessagesByAuthorLastName/{lastName}")
  public ResponseEntity<List<MessageEntity>> findMessagesByAuthorLastName(@PathVariable("lastName") String lastName) {
    List<MessageEntity> result = messageService.findMessagesByAuthorLastName(lastName);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findByMessageLike/{message}")
  public ResponseEntity<List<MessageEntity>> findByMessageLike(@PathVariable("message") String message) {
    List<MessageEntity> result = messageService.findByMessageLike(message);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
