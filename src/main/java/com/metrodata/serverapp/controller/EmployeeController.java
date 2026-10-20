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

import com.metrodata.serverapp.model.Employee;
import com.metrodata.serverapp.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  // getAll
  @GetMapping
  public List<Employee> getAll() {
    return employeeService.getAll();
  }

  // getById
  @GetMapping("/{id}")
  public Employee getById(@PathVariable Integer id) {
    return employeeService.getById(id);
  }

  // update
  @PutMapping("/{id}")
  public Employee update(@PathVariable Integer id, @RequestBody Employee employee) {
    return employeeService.update(id, employee);
  }

  // delete
  @DeleteMapping("/{id}")
  public Employee delete(@PathVariable Integer id) {
    return employeeService.delete(id);
  }

}
