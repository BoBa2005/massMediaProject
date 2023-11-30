package com.mass.media.service.impl;

import com.mass.media.dto.Author;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AuthorEntity;
import com.mass.media.repository.AuthorRepository;
import com.mass.media.service.IAuthorService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AuthorServiceImpl implements IAuthorService {

  private final AuthorRepository authorRepository;

  public AuthorServiceImpl(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public List<AuthorEntity> findAll() {
    return (List<AuthorEntity>) authorRepository.findAll();
  }

  @Override
  public OperationResponse saveAuthor(Author author) {
    OperationResponse operationResponse = this.validateAuthor(author);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    AuthorEntity authorEntity = new AuthorEntity();
    authorEntity.setFirstName(author.getFirstName());
    authorEntity.setLastName(author.getLastName());
    authorRepository.save(authorEntity);
    return new OperationResponse("SUCCESS", "Author saved");
  }

  @Override
  public OperationResponse deleteAuthor(Long authorId) {
    if (authorRepository.existsById(authorId)) {
      authorRepository.deleteById(authorId);
      return new OperationResponse("SUCCESS", "Author deleted");
    }
    return new OperationResponse("ERROR", "Author doesn't exists. Author id - " + authorId);
  }

  @Override
  public List<AuthorEntity> findByFirstName(String name) {
    return authorRepository.findByFirstName(name);
  }

  @Override
  public List<AuthorEntity> findByLastName(String name) {
    return authorRepository.findByLastName(name);
  }

  private OperationResponse validateAuthor(Author author) {
    if (author == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (StringUtils.isEmpty(author.getFirstName())) {
      return new OperationResponse("ERROR", "First name is required");
    }
    if (StringUtils.isEmpty(author.getLastName())) {
      return new OperationResponse("ERROR", "Last name is required");
    }
    return new OperationResponse("SUCCESS", "Author valid");
  }
}
