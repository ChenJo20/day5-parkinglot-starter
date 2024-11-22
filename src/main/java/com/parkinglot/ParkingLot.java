package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketToCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        return ticketToCar.get(ticket);
    }
}
