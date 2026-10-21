package com.metrodata.serverapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrodata.serverapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  @EntityGraph(attributePaths = { "roles", "roles.privileges" })
  Optional<User> findByUsernameOrEmployeeEmail(String username, String email);
}
