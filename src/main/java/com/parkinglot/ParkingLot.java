package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    private int capability;
    private int restSlotNum;
    private Map<Ticket, Car> ticketToCar = new HashMap<>();

    public ParkingLot() {
        capability = 10;
        restSlotNum = 10;
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
        Car fetchedCar = ticketToCar.getOrDefault(ticket, null);
        if (Objects.nonNull(fetchedCar)) {
            ticketToCar.remove(ticket);
            restSlotNum++;
        }
        return fetchedCar;
    }
}
