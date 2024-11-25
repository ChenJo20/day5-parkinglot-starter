package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final String NO_AVAILABLE_POSITION = "No available position";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    public static final int DEFAULT_CAPABILITY = 10;
    private int capacity;
    private int restPositionNum;
    private final Map<Ticket, Car> ticketToCar = new HashMap<>();

    public ParkingLot() {
        capacity = DEFAULT_CAPABILITY;
        restPositionNum = DEFAULT_CAPABILITY;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        restPositionNum = capacity;
    }

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        ticketToCar.put(ticket, car);
        restPositionNum--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = ticketToCar.remove(ticket);
        restPositionNum++;
        return fetchedCar;
    }

    public boolean isAvailable() {
        return restPositionNum != 0;
    }

    public int getRestPositionNum() {
        return restPositionNum;
    }

    public double getAvailableRate() {
        return (double) restPositionNum / capacity;
    }
}
