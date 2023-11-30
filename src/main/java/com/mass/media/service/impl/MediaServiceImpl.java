package com.mass.media.service.impl;

import com.mass.media.dto.Media;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MediaEntity;
import com.mass.media.repository.MediaRepository;
import com.mass.media.service.IMediaService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MediaServiceImpl implements IMediaService {

  private final MediaRepository mediaRepository;

  public MediaServiceImpl(MediaRepository mediaRepository) {
    this.mediaRepository = mediaRepository;
  }

  @Override
  public List<MediaEntity> findAll() {
    return (List<MediaEntity>) mediaRepository.findAll();
  }

  @Override
  public OperationResponse saveMedia(Media media) {
    OperationResponse operationResponse = this.validateMedia(media);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    MediaEntity mediaEntity = new MediaEntity();
    mediaEntity.setCountryId(media.getCountryId());
    mediaEntity.setName(media.getName());
    mediaEntity.setUrl(media.getUrl());
    mediaRepository.save(mediaEntity);
    return new OperationResponse("SUCCESS", "Media saved");
  }

  @Override
  public OperationResponse deleteMedia(Long mediaId) {
    if (mediaRepository.existsById(mediaId)) {
      mediaRepository.deleteById(mediaId);
      return new OperationResponse("SUCCESS", "Media deleted");
    }
    return new OperationResponse("ERROR", "Media doesn't exists. Media id - " + mediaId);
  }

  @Override
  public List<MediaEntity> findByName(String name) {
    return mediaRepository.findByName(name);
  }

  @Override
  public List<MediaEntity> findMediaByCountryName(String countryName) {
    return mediaRepository.findByCountryName(countryName);
  }

  private OperationResponse validateMedia(Media media) {
    if (media == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (StringUtils.isEmpty(media.getName())) {
      return new OperationResponse("ERROR", "Media name is required");
    }
    return new OperationResponse("SUCCESS", "Media valid");
  }
}
