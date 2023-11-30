package com.mass.media.repository;

import com.mass.media.entity.CountryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {

  List<CountryEntity> findByName(String name);
}
