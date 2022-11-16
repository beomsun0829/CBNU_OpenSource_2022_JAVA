package com.cbnuopensource2022java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cbnuopensource2022java.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}