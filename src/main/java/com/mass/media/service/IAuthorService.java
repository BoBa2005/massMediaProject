package com.mass.media.service;

import com.mass.media.dto.Author;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.AuthorEntity;

import java.util.List;

public interface IAuthorService {

  List<AuthorEntity> findAll();

  OperationResponse saveAuthor(Author author);

  OperationResponse deleteAuthor(Long authorId);

  List<AuthorEntity> findByFirstName(String name);

  List<AuthorEntity> findByLastName(String name);
}
