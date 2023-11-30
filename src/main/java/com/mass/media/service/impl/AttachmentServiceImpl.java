package com.mass.media.service.impl;

import com.mass.media.dto.Attachment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AttachmentEntity;
import com.mass.media.repository.AttachmentRepository;
import com.mass.media.service.IAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AttachmentServiceImpl implements IAttachmentService {

  private final AttachmentRepository attachmentRepository;

  public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
    this.attachmentRepository = attachmentRepository;
  }

  @Override
  public List<AttachmentEntity> findAll() {
    return (List<AttachmentEntity>) attachmentRepository.findAll();
  }

  @Override
  public OperationResponse saveAttachment(Attachment attachment) {
    OperationResponse operationResponse = this.validateAttachment(attachment);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    AttachmentEntity attachmentEntity = new AttachmentEntity();
    attachmentEntity.setAttachmentType(attachment.getAttachmentType());
    attachmentEntity.setUrl(attachment.getUrl());
    attachmentRepository.save(attachmentEntity);
    return new OperationResponse("SUCCESS", "Attachment saved");
  }

  @Override
  public OperationResponse deleteAttachment(Long attachmentId) {
    if (attachmentRepository.existsById(attachmentId)) {
      attachmentRepository.deleteById(attachmentId);
      return new OperationResponse("SUCCESS", "Attachment deleted");
    }
    return new OperationResponse("ERROR", "Attachment doesn't exists. Attachment id - " + attachmentId);
  }

  @Override
  public List<AttachmentEntity> findByAttachmentType(String type) {
    return attachmentRepository.findByAttachmentType(type);
  }

  @Override
  public List<AttachmentEntity> findByUrl(String url) {
    return attachmentRepository.findByUrl(url);
  }

  private OperationResponse validateAttachment(Attachment attachment) {
    if (attachment == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (StringUtils.isEmpty(attachment.getAttachmentType())) {
      return new OperationResponse("ERROR", "Attachment type is required");
    }
    if (StringUtils.isEmpty(attachment.getUrl())) {
      return new OperationResponse("ERROR", "Url is required");
    }
    return new OperationResponse("SUCCESS", "Attachment valid");
  }
}
