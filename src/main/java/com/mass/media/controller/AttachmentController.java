package com.mass.media.controller;

import com.mass.media.dto.Attachment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AttachmentEntity;
import com.mass.media.service.IAttachmentService;
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
public class AttachmentController {

  private final IAttachmentService attachmentService;

  public AttachmentController(IAttachmentService attachmentService) {
    this.attachmentService = attachmentService;
  }

  @GetMapping("/attachments")
  public ResponseEntity<List<AttachmentEntity>> findAll() {
    return new ResponseEntity<>(attachmentService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/attachment/save")
  public ResponseEntity<OperationResponse> saveAttachment(@RequestBody Attachment attachment) {
    OperationResponse operationResponse = attachmentService.saveAttachment(attachment);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/attachment/delete")
  public ResponseEntity<OperationResponse> deleteAttachment(@RequestBody Long authorId) {
    OperationResponse operationResponse = attachmentService.deleteAttachment(authorId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/findAttachmentByAttachmentType/{type}")
  public ResponseEntity<List<AttachmentEntity>> findAttachmentByAttachmentType(@PathVariable("type") String type) {
    List<AttachmentEntity> result = attachmentService.findByAttachmentType(type);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findAttachmentByUrl/{url}")
  public ResponseEntity<List<AttachmentEntity>> findAttachmentByUrl(@PathVariable("url") String url) {
    List<AttachmentEntity> result = attachmentService.findByUrl(url);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
