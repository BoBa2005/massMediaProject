package com.mass.media.service.impl;

import com.mass.media.dto.Country;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CountryEntity;
import com.mass.media.repository.CountryRepository;
import com.mass.media.service.ICountryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CountryServiceImpl implements ICountryService {

  private final CountryRepository countryRepository;

  public CountryServiceImpl(CountryRepository countryRepository) {
    this.countryRepository = countryRepository;
  }

  @Override
  public List<CountryEntity> findAll() {
    return (List<CountryEntity>) countryRepository.findAll();
  }

  @Override
  public OperationResponse saveCountry(Country country) {
    OperationResponse operationResponse = this.validateCountry(country);
    if ("ERROR".equals(operationResponse.getReturnType())) {
      return operationResponse;
    }
    CountryEntity countryEntity = new CountryEntity();
    countryEntity.setName(country.getName());
    countryRepository.save(countryEntity);
    return new OperationResponse("SUCCESS", "Country saved");
  }

  @Override
  public OperationResponse deleteCountry(Long countryId) {
    if (countryRepository.existsById(countryId)) {
      countryRepository.deleteById(countryId);
      return new OperationResponse("SUCCESS", "Country deleted");
    }
    return new OperationResponse("ERROR", "Country doesn't exists. Country id - " + countryId);
  }

  @Override
  public List<CountryEntity> findByName(String name) {
    return countryRepository.findByName(name);
  }

  private OperationResponse validateCountry(Country country) {
    if (country == null) {
      return new OperationResponse("ERROR", "Request is empty");
    }
    if (StringUtils.isEmpty(country.getName())) {
      return new OperationResponse("ERROR", "Country name is required");
    }
    return new OperationResponse("SUCCESS", "Country valid");
  }
}
