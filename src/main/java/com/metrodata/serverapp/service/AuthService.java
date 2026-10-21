package com.metrodata.serverapp.service;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metrodata.serverapp.dto.request.LoginRequest;
import com.metrodata.serverapp.dto.request.RegistrationRequest;
import com.metrodata.serverapp.dto.response.LoginResponse;
import com.metrodata.serverapp.dto.response.RegistrationResponse;
import com.metrodata.serverapp.mapper.RegistrationMapper;
import com.metrodata.serverapp.model.Employee;
import com.metrodata.serverapp.model.User;
import com.metrodata.serverapp.repository.EmployeeRepository;
import com.metrodata.serverapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

  private final RegistrationMapper registrationMapper;
  private final RoleService roleService;
  private final EmployeeRepository employeeRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;

  // ? registration
  public RegistrationResponse registration(RegistrationRequest request) {
    // mapping dto
    Employee employee = registrationMapper.toEmployee(request);
    User user = registrationMapper.toUser(request);

    // set default role
    user.setRoles(Set.of(roleService.getById(2)));

    // set password
    user.setPassword(passwordEncoder.encode(request.getPassword()));

    // relasi one to one
    user.setEmployee(employee);
    employee.setUser(user);

    // save -> add data
    employeeRepository.save(employee);

    log.info("email : {}", employee.getEmail());

    // set response
    return registrationMapper.toResponse(user);
  }

  // ? login
  public LoginResponse login(LoginRequest loginRequest) {
    // 1. bungkus => username & password
    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
        loginRequest.getPassword());

    // 2. check database
    Authentication auth = authenticationManager.authenticate(authReq);

    // 3. set session
    SecurityContextHolder.getContext().setAuthentication(auth);

    // 4. ambil data user -> setup response
    User user = userRepository.findByUsernameOrEmployeeEmail(loginRequest.getUsername(), loginRequest.getUsername())
        .get();

    // 5. setup return untuk response -> user
    return LoginResponse.builder()
        .username(auth.getName())
        .email(user.getEmployee().getEmail())
        .authorities(auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()).build();
  }
}
