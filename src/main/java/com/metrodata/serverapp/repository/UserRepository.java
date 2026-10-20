package com.metrodata.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrodata.serverapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
