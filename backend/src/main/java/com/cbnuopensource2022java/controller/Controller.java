package com.cbnuopensource2022java.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbnuopensource2022java.entity.Location;
import com.cbnuopensource2022java.service.LocationService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class Controller {

    private final LocationService testService;

    @GetMapping("api/location")
    public String getLocationByName(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "page", defaultValue = "1") String page) throws IOException {

        if (name == null) {
            return testService.getLocation(page);
        }

        return testService.getLocationByName(name);
    }

    @GetMapping("api/util/{id}")
    public String getUtilById(@PathVariable("id") String id) throws IOException {
        return testService.getUtilById(id);
    }

    // ---------test------------

    @GetMapping("/test")
    public String test() {
        return "Test Api";
    }

    @GetMapping("api/test")
    public List<Location> getLocationListInDB() throws IOException {
        return testService.getLocationListInDB();
    }

    @GetMapping("api/test/{id}")
    public Optional<Location> getLocationByIdInDB(@PathVariable("id") int id) {
        return testService.getLocationByIdInDB(id);
    }
}
