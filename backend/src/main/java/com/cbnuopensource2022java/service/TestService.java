package com.cbnuopensource2022java.service;

import org.springframework.stereotype.Service;
import com.cbnuopensource2022java.entity.Location;
import com.cbnuopensource2022java.repository.LocationRepository;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TestService {

    private LocationRepository locationRepository;

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(int id) {
        return locationRepository.findById(id);
    }

}
