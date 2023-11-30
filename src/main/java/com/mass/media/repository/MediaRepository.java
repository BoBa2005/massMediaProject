package com.mass.media.repository;

import com.mass.media.entity.MediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends CrudRepository<MediaEntity, Long> {

  List<MediaEntity> findByName(String name);

  List<MediaEntity> findByCountryName(String countryName);
}
