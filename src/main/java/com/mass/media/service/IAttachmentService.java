package com.mass.media.service;

import com.mass.media.dto.Attachment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AttachmentEntity;

import java.util.List;

public interface IAttachmentService {

  List<AttachmentEntity> findAll();

  OperationResponse saveAttachment(Attachment attachment);

  OperationResponse deleteAttachment(Long attachmentId);

  List<AttachmentEntity> findByAttachmentType(String type);

  List<AttachmentEntity> findByUrl(String url);
}
