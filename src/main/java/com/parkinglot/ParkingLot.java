package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketToCar.put(ticket, car);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = ticketToCar.getOrDefault(ticket, null);
        if (Objects.nonNull(fetchedCar)) {
            ticketToCar.remove(ticket);
        }
        return fetchedCar;
    }
}
