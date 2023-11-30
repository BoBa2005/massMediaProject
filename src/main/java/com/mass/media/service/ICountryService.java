package com.mass.media.service;

import com.mass.media.dto.Country;
import com.mass.media.dto.OperationResponse;
import com.mass.media.entity.CountryEntity;
import java.util.List;

public interface ICountryService {

  List<CountryEntity> findAll();

  OperationResponse saveCountry(Country country);

  OperationResponse deleteCountry(Long countryId);

  List<CountryEntity> findByName(String name);
}
