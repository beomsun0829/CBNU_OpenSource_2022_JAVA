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

    public String getLocation(String page) throws IOException {
        return ApiExplorer.getLocation(page);
    }

    public String getLocationByName(String name) throws IOException {
        return ApiExplorer.getLocationByName(name);
    }

    public String getUtilById(String id) throws IOException {
        return ApiExplorer.getUtilById(id);
    }

    public String InitDB() throws IOException {
        return "a";
    }

    // ---------test------------
    public List<Location> getLocationListInDB() throws IOException {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationByIdInDB(int id) {
        return locationRepository.findById(id);
    }

}
