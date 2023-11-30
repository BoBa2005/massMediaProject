package com.mass.media.controller;

import com.mass.media.dto.Comment;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CommentEntity;
import com.mass.media.service.ICommentService;
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
public class CommentController {

  private final ICommentService commentService;

  public CommentController(ICommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/comments")
  public ResponseEntity<List<CommentEntity>> findAll() {
    return new ResponseEntity<>(commentService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/comment/save")
  public ResponseEntity<OperationResponse> saveComment(@RequestBody Comment comment) {
    OperationResponse operationResponse = commentService.saveComment(comment);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/comment/delete")
  public ResponseEntity<OperationResponse> deleteComment(@RequestBody Long commentId) {
    OperationResponse operationResponse = commentService.deleteComment(commentId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/findCommentByMessageId/{messageId}")
  public ResponseEntity<List<CommentEntity>> findCommentByMessageId(@PathVariable("messageId") Long messageId) {
    List<CommentEntity> result = commentService.findByMessageId(messageId);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
