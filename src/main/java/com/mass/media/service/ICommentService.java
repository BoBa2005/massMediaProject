package com.mass.media.service;

import com.mass.media.dto.Comment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CommentEntity;

import java.util.List;

public interface ICommentService {

  List<CommentEntity> findAll();

  OperationResponse saveComment(Comment comment);

  OperationResponse deleteComment(Long commentId);

  List<CommentEntity> findByMessageId(Long messageId);
}
