package com.metrodata.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metrodata.serverapp.model.User;
import com.metrodata.serverapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  // getAll
  public List<User> getAll() {
    return userRepository.findAll();
  }

  // getById
  public User getById(Integer id) {
    return userRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id User not found!!!"));
  }

  // update
  public User update(Integer id, User user) {
    getById(id);
    user.setId(id);
    return userRepository.save(user);
  }

  // delete
  public User delete(Integer id) {
    User user = getById(id);
    userRepository.delete(user);
    return user;
  }

}
