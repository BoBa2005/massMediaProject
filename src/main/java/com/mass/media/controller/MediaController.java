package com.mass.media.controller;

import com.mass.media.dto.Media;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.MediaEntity;
import com.mass.media.service.IMediaService;
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
public class MediaController {

  private final IMediaService mediaService;

  public MediaController(IMediaService mediaService) {
    this.mediaService = mediaService;
  }

  @GetMapping("/medias")
  public ResponseEntity<List<MediaEntity>> findAll() {
    return new ResponseEntity<>(mediaService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/media/save")
  public ResponseEntity<OperationResponse> saveMedia(@RequestBody Media media) {
    OperationResponse operationResponse = mediaService.saveMedia(media);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/media/delete")
  public ResponseEntity<OperationResponse> deleteMedia(@RequestBody Long mediaId) {
    OperationResponse operationResponse = mediaService.deleteMedia(mediaId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/media/{name}")
  public ResponseEntity<List<MediaEntity>> findByName(@PathVariable("name") String name) {
    List<MediaEntity> result = mediaService.findByName(name);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/findMediaByCountryName/{name}")
  public ResponseEntity<List<MediaEntity>> findMediaByCountryName(@PathVariable("name") String countryName) {
    List<MediaEntity> result = mediaService.findMediaByCountryName(countryName);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
