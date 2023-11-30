package com.mass.media.controller;

import com.mass.media.dto.Author;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AuthorEntity;
import com.mass.media.service.IAuthorService;
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
public class AuthorController {

  private final IAuthorService authorService;

  public AuthorController(IAuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/authors")
  public ResponseEntity<List<AuthorEntity>> findAll() {
    return new ResponseEntity<>(authorService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/author/save")
  public ResponseEntity<OperationResponse> saveAuthor(@RequestBody Author author) {
    OperationResponse operationResponse = authorService.saveAuthor(author);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/author/delete")
  public ResponseEntity<OperationResponse> deleteAuthor(@RequestBody Long authorId) {
    OperationResponse operationResponse = authorService.deleteAuthor(authorId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/findAuthorByFirstName/{name}")
  public ResponseEntity<List<AuthorEntity>> findAuthorByFirstName(@PathVariable("name") String name) {
    List<AuthorEntity> result = authorService.findByFirstName(name);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findAuthorByLastName/{name}")
  public ResponseEntity<List<AuthorEntity>> findAuthorByLastName(@PathVariable("name") String name) {
    List<AuthorEntity> result = authorService.findByLastName(name);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
