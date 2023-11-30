package com.mass.media.repository;

import com.mass.media.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

  List<AuthorEntity> findByFirstName(String firstName);

  List<AuthorEntity> findByLastName(String lastName);
}
