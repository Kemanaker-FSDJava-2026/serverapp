package com.metrodata.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrodata.serverapp.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
