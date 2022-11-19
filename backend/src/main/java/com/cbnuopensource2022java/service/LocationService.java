package com.cbnuopensource2022java.service;

import org.springframework.stereotype.Service;

import com.cbnuopensource2022java.api.ApiExplorer;
import com.cbnuopensource2022java.entity.Location;
import com.cbnuopensource2022java.repository.LocationRepository;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {
    private LocationRepository locationRepository;

    public String getLocations() throws IOException {
        String LocationList = ApiExplorer.getLocations();
        return LocationList;
    }

    public List<Location> getLocationListInDB() throws IOException {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(int id) {
        return locationRepository.findById(id);
    }

}
