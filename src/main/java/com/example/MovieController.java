package com.example;

import com.example.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by trainer9 on 5/15/17.
 */

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/movies")
    public List<HashMap<String, Object>> getMovies(@RequestParam(value="q") String queryName) {
        return movieService.queryOMDB(queryName);
    }

}
