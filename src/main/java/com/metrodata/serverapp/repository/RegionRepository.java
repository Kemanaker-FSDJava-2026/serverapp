package com.metrodata.serverapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.metrodata.serverapp.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {

  // ? Native
  @Query(value = "SELECT * FROM db_region WHERE region_name LIKE :name", nativeQuery = true)
  List<Region> findByNameWithNative(String name);

  // ? JPQL
  @Query("SELECT r FROM Region r WHERE r.name LIKE ?1")
  List<Region> findByNameWithJPQL(String name);

  // ? query method
  List<Region> searchByName(String name);

  boolean existsByName(String name);
}
