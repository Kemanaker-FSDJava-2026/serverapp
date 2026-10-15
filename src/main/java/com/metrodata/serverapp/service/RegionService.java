package com.metrodata.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metrodata.serverapp.model.Region;
import com.metrodata.serverapp.repository.RegionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionService {
  private final RegionRepository regionRepository;

  // getAll
  public List<Region> getAll() {
    return regionRepository.findAll();
  }

  // getById
  public Region getById(Integer id) {
    return regionRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Region not found!!!"));
  }

  // create
  public Region create(Region region) {
    if (regionRepository.existsByName(region.getName())) {
      throw new ResponseStatusException(
          HttpStatus.CONFLICT,
          "Region name already exists!!!");
    }

    region.setId(null);
    return regionRepository.save(region);
  }

  // update
  public Region update(Integer id, Region region) {
    getById(id);
    region.setId(id);
    return regionRepository.save(region);
  }

  // delete
  public Region delete(Integer id) {
    Region region = getById(id);
    regionRepository.delete(region);
    return region;
  }

  public List<Region> searchByNameNative(String name) {
    String fullName = "%" + name + "%";
    return regionRepository.findByNameWithNative(fullName);
  }

  public List<Region> findByNameWithJPQL(String name) {
    String fullName = "%" + name + "%";
    return regionRepository.findByNameWithJPQL(fullName);
  }
}
