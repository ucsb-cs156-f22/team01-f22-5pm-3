package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.LocationQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description="Location info from nominatim.org")
@Slf4j
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    LocationQueryService LocationQueryService;

    @ApiOperation(value="Get a list of locations that match a given location name", notes="Country data uploaded to OpenDataSoft by the International Labour Organization")
    @GetMapping("/get")
    public ResponseEntity<String> getLocations(
        @ApiParam("country, e.g. United States") @RequestParam String location
    ) throws JsonProcessingException {
        log.info("getLocations: location={}", location);
        String result = LocationQueryService.getJSON(location);
        return ResponseEntity.ok().body(result);
    }

}