package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    public static final int DEFAULT_CAPABILITY = 10;
    private int capacity;
    private int restSlotNum;
    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public ParkingLot() {
        capacity = DEFAULT_CAPABILITY;
        restSlotNum = DEFAULT_CAPABILITY;
    }

    public Ticket park(Car car) {
        if (restSlotNum == 0) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticketToCar.put(ticket, car);
        restSlotNum--;
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = ticketToCar.remove(ticket);
        if (Objects.nonNull(fetchedCar)) {
            restSlotNum++;
        }
        return fetchedCar;
    }
}
