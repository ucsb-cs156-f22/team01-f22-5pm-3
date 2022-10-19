package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import edu.ucsb.cs156.spring.backenddemo.services.UniversityQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Api(description="Information about Universities")
@Slf4j
@RestController
@RequestMapping("/api/university")
public class UniversityController {
    @Autowired
    UniversityQueryService universityQueryService;

    @ApiOperation(value="Get list of universities that match a given name")
    @GetMapping("/get")
    public ResponseEntity<String> getUniversities(
        @ApiParam("name to search, e.g. 'Harvard' or 'Stanford'") @RequestParam String name
    ) throws JsonProcessingException {
        log.info("getUniversities: name={}", name);
        String result = universityQueryService.getJSON(name);
        return ResponseEntity.ok().body(result);
    }
}
