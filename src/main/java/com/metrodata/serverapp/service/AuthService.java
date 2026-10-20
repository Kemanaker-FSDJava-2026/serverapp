package com.metrodata.serverapp.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metrodata.serverapp.dto.request.RegistrationRequest;
import com.metrodata.serverapp.dto.response.RegistrationResponse;
import com.metrodata.serverapp.mapper.RegistrationMapper;
import com.metrodata.serverapp.model.Employee;
import com.metrodata.serverapp.model.User;
import com.metrodata.serverapp.repository.EmployeeRepository;

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

  // registration
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
}
