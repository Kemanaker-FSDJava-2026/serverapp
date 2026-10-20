package com.metrodata.serverapp.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.metrodata.serverapp.model.Employee;
import com.metrodata.serverapp.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;

  // getAll
  public List<Employee> getAll() {
    return employeeRepository.findAll();
  }

  // getById
  public Employee getById(Integer id) {
    return employeeRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id Employee not found!!!"));
  }

  // update
  public Employee update(Integer id, Employee employee) {
    getById(id);
    employee.setId(id);
    return employeeRepository.save(employee);
  }

  // delete
  public Employee delete(Integer id) {
    Employee employee = getById(id);
    employeeRepository.delete(employee);
    return employee;
  }

}
