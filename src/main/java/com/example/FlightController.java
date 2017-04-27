package com.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by trainer9 on 4/26/17.
 */
@RestController

public class FlightController {

    @GetMapping("/flights/flight")
    public Flight getFlightDetail() {
        Flight flight = new Flight();


        flight.setDeparts(new Date(2017 - 1900, 03, 21, 14, 34));

        List<Ticket> tickets = new ArrayList<Ticket>();

        Ticket ticket = new Ticket();
        ticket.setPrice(200);
        Person passenger = new Person();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        ticket.setPassenger(passenger);
        tickets.add(ticket);
        flight.setTickets(tickets);
        return flight;
    }



    @GetMapping("/flights")
    public List<Flight> getFlightDepartues() {

        List<Flight> flights = new ArrayList<Flight>();

        Flight flight1 = new Flight();


        flight1.setDeparts(new Date(2017 - 1900, 03, 21, 14, 34));

        List<Ticket> tickets = new ArrayList<Ticket>();

        Ticket ticket = new Ticket();
        ticket.setPrice(200);
        Person passenger = new Person();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");
        ticket.setPassenger(passenger);
        tickets.add(ticket);
        flight1.setTickets(tickets);

        flights.add(flight1);

        Flight flight2 = new Flight();


        flight2.setDeparts(new Date(2017 - 1900, 03, 21, 14, 34));

        List<Ticket> tickets1 = new ArrayList<Ticket>();

        Ticket ticket1 = new Ticket();
        ticket1.setPrice(400);
        Person passenger1 = new Person();
        passenger1.setFirstName("Some other name");

        ticket1.setPassenger(passenger1);
        tickets1.add(ticket1);
        flight2.setTickets(tickets1);

        flights.add(flight2);


        return flights;
    }

    @PostMapping("/flights/tickets/total")
    public FlightTotal calculateFlightTotal(@RequestBody TicketDetail airplane) {
        FlightTotal flightTotal = new FlightTotal();
        Integer total = airplane.getTickets().stream().
                mapToInt(a -> a.getPrice()).sum();

        flightTotal.setResult(total);

        return flightTotal;
    }




    public static class Flight {
        private Date departs;
        private List<Ticket> Tickets;

        @JsonFormat(pattern = "yyyy-MM-dd HH:MM")
        @JsonProperty("Departs")
        public Date getDeparts() {
            return departs;
        }

        public void setDeparts(Date departs) {
            this.departs = departs;
        }

        @JsonProperty("Tickets")
        public List<Ticket> getTickets() {
            return Tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            Tickets = tickets;
        }



    }


    public static class Ticket {

    Person passenger;
    int price;

    @JsonProperty("Passenger")
    public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

       @JsonProperty("Price")
        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Person
    {
         String firstName;
        String lastName;

       @JsonProperty("Firstname")
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

       @JsonProperty("Lastname")
        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public static class FlightTotal
    {
        int result;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }



    }


    public static class TicketDetail {

        private List<Ticket> Tickets;


        public List<Ticket> getTickets() {
            return Tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            Tickets = tickets;
        }



    }

}

