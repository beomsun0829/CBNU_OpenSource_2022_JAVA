package com.cbnuopensource2022java.service;

import org.springframework.stereotype.Service;
import com.cbnuopensource2022java.entity.Location;
import com.cbnuopensource2022java.repository.LocationRepository;

import lombok.AllArgsConstructor;
import java.util.List;

@Service
@AllArgsConstructor
public class TestService {

    private LocationRepository locationRepository;

    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    public String getLocation(int Id) {
        return locationRepository.findById(Id).toString();
    }

    public List<Location> getLocationById(int id) {
        return null;
    }

}
