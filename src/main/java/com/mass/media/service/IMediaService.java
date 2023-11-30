package com.mass.media.service;

import com.mass.media.dto.Media;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MediaEntity;

import java.util.List;

public interface IMediaService {

  List<MediaEntity> findAll();

  OperationResponse saveMedia(Media media);

  OperationResponse deleteMedia(Long mediaId);

  List<MediaEntity> findByName(String name);

  List<MediaEntity> findMediaByCountryName(String countryName);
}
