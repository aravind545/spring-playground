package com.example;

import org.springframework.web.bind.annotation.RestController;
import com.example.model.MovieData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer9 on 4/25/17.
 */
@RestController
public class PatchController {

    @PatchMapping(value = "/movies/{id}/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public MovieData moviePatch(@PathVariable int id){
        MovieData movie = new MovieData();
        movie.setId(id);
        movie.setTitle("Title");
        return movie;
    }

}
