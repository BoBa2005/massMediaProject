package com.mass.media.controller;

import com.mass.media.dto.Country;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CountryEntity;
import com.mass.media.service.ICountryService;
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
public class CountryController {

  private final ICountryService countryService;

  public CountryController(ICountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping("/countries")
  public ResponseEntity<List<CountryEntity>> findAllCountries() {
    return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
  }

  @PostMapping("/country/save")
  public ResponseEntity<OperationResponse> saveCountry(@RequestBody Country country) {
    OperationResponse operationResponse = countryService.saveCountry(country);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @PostMapping("/country/delete")
  public ResponseEntity<OperationResponse> deleteCountry(@RequestBody Long countryId) {
    OperationResponse operationResponse = countryService.deleteCountry(countryId);
    return operationResponse.getReturnType().equals("SUCCESS") ?
        new ResponseEntity<>(operationResponse, HttpStatus.OK) :
        new ResponseEntity<>(operationResponse, HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/country/{name}")
  public ResponseEntity<List<CountryEntity>> findByName(@PathVariable("name") String name) {
    List<CountryEntity> result = countryService.findByName(name);
    return CollectionUtils.isEmpty(result) ?
        new ResponseEntity<>(HttpStatus.NO_CONTENT) :
        new ResponseEntity<>(result, HttpStatus.OK);
  }
}
