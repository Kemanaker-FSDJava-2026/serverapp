package com.metrodata.serverapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metrodata.serverapp.model.AppUserDetail;
import com.metrodata.serverapp.model.User;
import com.metrodata.serverapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsernameOrEmployeeEmail(username, username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!!!"));

    return new AppUserDetail(user);
  }

}
