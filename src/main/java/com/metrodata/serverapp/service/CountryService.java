package com.metrodata.serverapp.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metrodata.serverapp.dto.request.CountryRequest;
import com.metrodata.serverapp.mapper.CountryMapper;
import com.metrodata.serverapp.model.Country;
import com.metrodata.serverapp.model.Region;
import com.metrodata.serverapp.repository.CountryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CountryService {
  private final CountryRepository countryRepository;
  private final RegionService regionService;
  private final CountryMapper countryMapper;

  // getAll
  public List<Country> getAll() {
    return countryRepository.findAll();
  }

  // getById
  public Country getById(Integer id) {
    return countryRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Country not found!!!"));
  }

  // ? create without dto
  public Country createWithoutDTO(Country country) {
    country.setId(null);

    country.setRegion(findRegionId(country.getRegion().getId()));
    return countryRepository.save(country);
  }

  // ? create with manual dto
  public Country createManualDTO(CountryRequest request) {

    Country country = new Country();
    country.setCode(request.getCode());
    country.setName(request.getName());
    country.setRegion(findRegionId(request.getRegionId()));

    return countryRepository.save(country);
  }

  // ? create DTO with BeanUtils
  public Country creaatDTOBeanUtils(CountryRequest request) {
    Country country = new Country();
    BeanUtils.copyProperties(request, country);

    country.setRegion(findRegionId(request.getRegionId()));
    return countryRepository.save(country);
  }

  // ? create DTO with Mapstruct
  public Country createDTOMapstruct(CountryRequest request) {
    Country country = countryMapper.toEntity(request);

    country.setRegion(findRegionId(request.getRegionId()));
    return countryRepository.save(country);
  }

  // update
  public Country update(Integer id, Country country) {
    getById(id);
    country.setId(id);
    return countryRepository.save(country);
  }

  // delete
  public Country delete(Integer id) {
    Country country = getById(id);
    countryRepository.delete(country);
    return country;
  }

  private Region findRegionId(Integer regionId) {
    return regionService.getById(regionId);
  }
}
