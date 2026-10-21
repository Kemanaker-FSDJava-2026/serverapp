package com.metrodata.serverapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.dto.request.CountryRequest;
import com.metrodata.serverapp.model.Country;
import com.metrodata.serverapp.service.CountryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
@PreAuthorize("hasRole('ADMIN')")
public class CountryController {

  private final CountryService countryService;

  // getAll
  @GetMapping
  public List<Country> getAll() {
    return countryService.getAll();
  }

  // getById
  @GetMapping("/{id}")
  public Country getById(@PathVariable Integer id) {
    return countryService.getById(id);
  }

  // ? create without dto
  @PostMapping("/without-dto")
  @ResponseStatus(HttpStatus.CREATED)
  public Country createWithoutDTO(@RequestBody Country country) {
    return countryService.createWithoutDTO(country);
  }

  // ? create with manual dto
  @PostMapping("/manual-dto")
  @ResponseStatus(HttpStatus.CREATED)
  public Country createManualDTO(@RequestBody CountryRequest request) {
    return countryService.createManualDTO(request);
  }

  // ? create DTO with BeanUtils
  @PostMapping("/bean-dto")
  @ResponseStatus(HttpStatus.CREATED)
  public Country creaatDTOBeanUtils(@RequestBody CountryRequest request) {
    return countryService.creaatDTOBeanUtils(request);
  }

  // ? create DTO with Mapstruct
  @PostMapping("/mapstruct-dto")
  @ResponseStatus(HttpStatus.CREATED)
  public Country createDTOMapstruct(@RequestBody CountryRequest request) {
    return countryService.createDTOMapstruct(request);
  }

  // update
  @PutMapping("/{id}")
  public Country update(@PathVariable Integer id, @RequestBody Country country) {
    return countryService.update(id, country);
  }

  // delete
  @DeleteMapping("/{id}")
  public Country delete(@PathVariable Integer id) {
    return countryService.delete(id);
  }
}
