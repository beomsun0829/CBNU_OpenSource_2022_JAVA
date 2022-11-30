package com.cbnuopensource2022java.service;

import org.json.JSONException;
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

    public String initDB() throws IOException, JSONException {
        return ApiExplorer.initDB(locationRepository);
    }

    public List<Location> getLocationListInDB() throws IOException {
        return locationRepository.findAll();
    }

    public List<Location> getLocationByNameInDB(String name) throws IOException {
        return locationRepository.findByfaclNmContaining(name);
    }

    // ---------test------------

    public Optional<Location> getLocationByIdInDB(int id) {
        return locationRepository.findById(id);
    }

    public List<Location> sortByDistance(List<Location> locationList, String lat, String lng) {
        List<Location> sortedList = locationList;
        System.out.println("sorting" + lat + lng);
        sortedList.sort((a, b) -> {
            double distA = getDistance(a.getFaclLat(), a.getFaclLng(), lat, lng);
            double distB = getDistance(b.getFaclLat(), b.getFaclLng(), lat, lng);
            return Double.compare(distA, distB);
        });

        return sortedList;
    }

    private double getDistance(String faclLat, String faclLng, String lat, String lng) {
        double lat1 = Double.parseDouble(faclLat);
        double lng1 = Double.parseDouble(faclLng);
        double lat2 = Double.parseDouble(lat);
        double lng2 = Double.parseDouble(lng);

        return Math.pow(lat1 - lat2, 2) + Math.pow(lng1 - lng2, 2);
    }

}

// double distance = (Math.pow(lat1-lat2, 2) + Math.pow(lng1-lng2, 2));