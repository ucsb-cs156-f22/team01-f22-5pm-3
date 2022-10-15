package edu.ucsb.cs156.spring.backenddemo.services;

import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


@Service
@Slf4j
public class RedditQueryService {

    private final RestTemplate restTemplate;

    public RedditQueryService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }

    public static final String ENDPOINT = "https://www.reddit.com/r/{subreddit}.json";

    public String getJSON(String subreddit) throws HttpClientErrorException {
        log.info("subreddit={}", subreddit);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent","spring-boot:cs156-team01:f22 (by /u/Acceptable_Market298)");
        String url = "https://www.reddit.com/r/" + subreddit + ".json";
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}