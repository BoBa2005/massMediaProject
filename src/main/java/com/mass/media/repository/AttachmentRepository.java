package com.mass.media.repository;

import com.mass.media.entity.AttachmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends CrudRepository<AttachmentEntity, Long> {

  List<AttachmentEntity> findByAttachmentType(String type);

  List<AttachmentEntity> findByUrl(String url);
}
