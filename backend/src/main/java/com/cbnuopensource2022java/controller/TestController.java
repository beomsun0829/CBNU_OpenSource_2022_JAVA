package com.cbnuopensource2022java.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cbnuopensource2022java.entity.Location;
import com.cbnuopensource2022java.service.TestService;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/test")
    public String test() {
        return "Test Api";
    }

    @GetMapping("api/test")
    public List<Location> getLocations() {
        return testService.getLocations();
    }

    @GetMapping("api/test/{id}")
    public Optional<Location> getLocationById(@PathVariable("id") int id) {
        return testService.getLocationById(id);
    }
}
