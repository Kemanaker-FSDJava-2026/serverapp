package com.metrodata.serverapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrodata.serverapp.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
