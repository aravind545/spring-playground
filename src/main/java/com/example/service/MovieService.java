package com.example.service;

import com.example.MovieConfig;
import com.example.model.MovieResponse;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

/**
 * Created by trainer9 on 5/15/17.
 */
@Service
public class MovieService {


    RestTemplate restTemplate = new RestTemplate();

    MovieConfig movieConfig;

    public MovieService(MovieConfig movieConfig) {
        this.movieConfig = movieConfig;
    }





    public List<HashMap<String,Object>> queryOMDB(String queryName) {

        // Construct a URI from a template
        URI uri = UriComponentsBuilder
                .fromUriString(movieConfig.getUrl())
                .queryParam("s",queryName)
                .build()
                .toUri();

        // Create the request object
        RequestEntity<?> request = RequestEntity.get(uri).build();

        // Execute the request
        ResponseEntity<MovieResponse> response = restTemplate.exchange(
                request, MovieResponse.class);


        return response.getBody().getSearch();


    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
