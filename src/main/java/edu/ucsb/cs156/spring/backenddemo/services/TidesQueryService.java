package edu.ucsb.cs156.spring.backenddemo.services;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;

@Slf4j
@Service
public class TidesQueryService {

    ObjectMapper mapper = new ObjectMapper();

    private final RestTemplate restTemplate;

    public TidesQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://public.opendatasoft.com/api/records/1.0/search/?dataset=countries-codes&q={country}";

    // Documentation for endpoint is at: https://api.tidesandcurrents.noaa.gov/api/prod/
    //public static final String ENDPOINT = "";

    public String getJSON(String beginDate, String endDate, String station) throws HttpClientErrorException {
        log.info("beginDate={}", "endDate={}", "Station={}", beginDate, endDate, station);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> uriVariables = Map.of("beginDate", beginDate, "endDate", endDate, "station", station);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> re = restTemplate.exchange(ENDPOINT, HttpMethod.GET, entity, String.class,
                uriVariables);
        return re.getBody();
    }
}