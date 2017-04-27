package com.example;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.util.Date;
import java.util.List;

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
    private Gson gson = new GsonBuilder().create();


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

    @Test
    public void testCalculateTicketPrice_version1() throws Exception {

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("  {\n    \"tickets\": [\n      {\n        \"Passenger\": {\n          \"Firstname\": \"Some name\",\n          \"Lastname\": \"Some other name\"\n        },\n        \"Price\": 200\n      },\n      {\n        \"Passenger\": {\n          \"Firstname\": \"Name B\",\n          \"Lastname\": \"Name C\"\n        },\n        \"Price\": 150\n      }\n    ]\n  }");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n  \"result\": 350\n}"));
    }

    @Test
    public void testCalculateTicketPrice_version2() throws Exception {

        String json = getJSON("/tickets.json");


        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n  \"result\": 350\n}"));
    }


    @Test
    public void testCalculateTicketPrice_version3() throws Exception {

        Flight airPlane = new Flight();
        Ticket ticket = new Ticket();
        Person passenger = new Person();
        passenger.setFirstname("sunil");
        passenger.setLastname("anil");
        ticket.setPassenger(passenger);
        ticket.setPrice(10);

        Ticket tickets2 = new Ticket();

        Person passenger2 = new Person();
        passenger2.setFirstname("rop");
        passenger2.setLastname("swa");
        tickets2.setPassenger(passenger2);
        tickets2.setPrice(60);

        airPlane.setTickets(asList(ticket, tickets2));

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(gson.toJson(airPlane));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{\n  \"result\": 70\n}"));
    }

    public static class Flight {

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        private List<Ticket> tickets;




    }


    public static class Ticket {

        public Person getPassenger() {
            return Passenger;
        }

        public void setPassenger(Person passenger) {
            Passenger = passenger;
        }

        public int getPrice() {
            return Price;
        }

        public void setPrice(int price) {
            Price = price;
        }

        Person Passenger;
        int Price;


    }


    public static class Person
    {
        String Firstname;

        public String getFirstname() {
            return Firstname;
        }

        public void setFirstname(String firstname) {
            Firstname = firstname;
        }

        public String getLastname() {
            return Lastname;
        }

        public void setLastname(String lastname) {
            Lastname = lastname;
        }

        String Lastname;


    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }


}