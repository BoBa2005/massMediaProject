package com.mass.media.service.impl;

import com.mass.media.dto.Comment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CommentEntity;
import com.mass.media.repository.CommentRepository;
import com.mass.media.service.ICommentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

  private final CommentRepository commentRepository;

  public CommentServiceImpl(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  @Override
  public List<CommentEntity> findAll() {
    return (List<CommentEntity>) commentRepository.findAll();
  }

  @Override
  public OperationResponse saveComment(Comment comment) {
    OperationResponse operationResponse = this.validateComment(comment);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    CommentEntity commentEntity = new CommentEntity();
    commentEntity.setMessageId(comment.getMessageId());
    commentEntity.setComment(comment.getComment());
    commentEntity.setCreateDate(comment.getCreateDate() != null ? comment.getCreateDate() : new Date());
    commentRepository.save(commentEntity);
    return new OperationResponse("SUCCESS", "Comment saved");
  }

  @Override
  public OperationResponse deleteComment(Long commentId) {
    if (commentRepository.existsById(commentId)) {
      commentRepository.deleteById(commentId);
      return new OperationResponse("SUCCESS", "Comment deleted");
    }
    return new OperationResponse("ERROR", "Comment doesn't exists. Comment id - " + commentId);
  }

  @Override
  public List<CommentEntity> findByMessageId(Long messageId) {
    return commentRepository.findByMessageId(messageId);
  }

  private OperationResponse validateComment(Comment comment) {
    if (comment == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (comment.getMessageId() == null) {
      return new OperationResponse("ERROR", "Message id is required");
    }
    if (StringUtils.isEmpty(comment.getComment())) {
      return new OperationResponse("ERROR", "Comment is required");
    }
    return new OperationResponse("SUCCESS", "Comment valid");
  }
}
