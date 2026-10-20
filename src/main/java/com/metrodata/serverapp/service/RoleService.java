package com.metrodata.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metrodata.serverapp.model.Role;
import com.metrodata.serverapp.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
  private final RoleRepository roleRepository;

  // getAll
  public List<Role> getAll() {
    return roleRepository.findAll();
  }

  // getById
  public Role getById(Integer id) {
    return roleRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Role not found!!!"));
  }

  // create
  public Role create(Role role) {

    role.setId(null);
    return roleRepository.save(role);
  }

  // update
  public Role update(Integer id, Role role) {
    getById(id);
    role.setId(id);
    return roleRepository.save(role);
  }

  // delete
  public Role delete(Integer id) {
    Role role = getById(id);
    roleRepository.delete(role);
    return role;
  }

}
