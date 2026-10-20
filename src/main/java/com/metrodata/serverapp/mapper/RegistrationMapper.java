package com.metrodata.serverapp.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.metrodata.serverapp.dto.request.RegistrationRequest;
import com.metrodata.serverapp.dto.response.RegistrationResponse;
import com.metrodata.serverapp.model.Employee;
import com.metrodata.serverapp.model.Role;
import com.metrodata.serverapp.model.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegistrationMapper {
  // dto -> employee (name, email, phone)
  Employee toEmployee(RegistrationRequest request);

  // dto -> user (username, password)
  User toUser(RegistrationRequest request);

  @Mapping(target = "name", source = "employee.name")
  @Mapping(target = "phone", source = "employee.phone")
  @Mapping(target = "email", source = "employee.email")
  RegistrationResponse toResponse(User user);

  // default role -> Set<Role> roles
  default List<String> toRoleNames(Set<Role> roles) {
    return roles.stream().map(Role::getName).toList();
  }

}
