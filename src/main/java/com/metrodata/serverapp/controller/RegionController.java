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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.model.Region;
import com.metrodata.serverapp.service.RegionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class RegionController {

  private final RegionService regionService;

  // getAll
  @GetMapping
  @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
  public List<Region> getAll() {
    return regionService.getAll();
  }

  // getById
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('READ_ADMIN')")
  public Region getById(@PathVariable Integer id) {
    return regionService.getById(id);
  }

  // create
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Region create(@RequestBody Region region) {
    return regionService.create(region);
  }

  // update
  @PutMapping("/{id}")
  public Region update(@PathVariable Integer id, @RequestBody Region region) {
    return regionService.update(id, region);
  }

  // delete
  @DeleteMapping("/{id}")
  public Region delete(@PathVariable Integer id) {
    return regionService.delete(id);
  }

  @PostMapping("/native")
  public List<Region> searchByNameNative(@RequestParam String name) {
    return regionService.searchByNameNative(name);
  }

  @GetMapping("/jpql")
  public List<Region> findByNameWithJPQL(@RequestParam String name) {
    return regionService.findByNameWithJPQL(name);
  }
}
