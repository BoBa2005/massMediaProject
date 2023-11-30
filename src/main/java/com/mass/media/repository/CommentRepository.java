package com.mass.media.repository;

import com.mass.media.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

  List<CommentEntity> findByMessageId(Long messageId);
}
