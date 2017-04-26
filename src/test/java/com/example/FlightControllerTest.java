package com.example;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.util.Arrays.asList;
import static javafx.scene.input.KeyCode.T;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by trainer9 on 4/26/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void testGetFlightDetails() throws Exception {
        this.mvc.perform(
                get("/flights/flight")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Departs", is("2017-04-21 20:04")))
                .andExpect(jsonPath("$.Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$.Tickets[0].Passenger.Firstname", is("Some name")));


    }

    @Test
    public void testGetAllFlightDetails() throws Exception {
        this.mvc.perform(
                get("/flights")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].Departs", is("2017-04-21 20:04")))
                .andExpect(jsonPath("$[1].Departs", is("2017-04-21 20:04")))

                .andExpect(jsonPath("$[0].Tickets[0].Price", is(200)))
                .andExpect(jsonPath("$[1].Tickets[0].Price", is(400)))
                .andExpect(jsonPath("$[0].Tickets[0].Passenger.Firstname", is("Some name")))
                .andExpect(jsonPath("$[1].Tickets[0].Passenger.Firstname", is("Some other name")));


    }




}