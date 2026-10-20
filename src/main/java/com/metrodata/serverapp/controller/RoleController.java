package com.metrodata.serverapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.metrodata.serverapp.model.Role;
import com.metrodata.serverapp.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

  private final RoleService roleService;

  // getAll
  @GetMapping
  public List<Role> getAll() {
    return roleService.getAll();
  }

  // getById
  @GetMapping("/{id}")
  public Role getById(@PathVariable Integer id) {
    return roleService.getById(id);
  }

  // create
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Role create(@RequestBody Role role) {
    return roleService.create(role);
  }

  // update
  @PutMapping("/{id}")
  public Role update(@PathVariable Integer id, @RequestBody Role role) {
    return roleService.update(id, role);
  }

  // delete
  @DeleteMapping("/{id}")
  public Role delete(@PathVariable Integer id) {
    return roleService.delete(id);
  }

}
