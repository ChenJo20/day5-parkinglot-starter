package com.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {
    public static final String NO_AVAILABLE_POSITION = "No available position";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    public static final int DEFAULT_CAPABILITY = 10;
    private int capacity;
    private int restSlotNum;
    private final Map<Ticket, Car> ticketToCar = new HashMap<>();

    public ParkingLot() {
        capacity = DEFAULT_CAPABILITY;
        restSlotNum = DEFAULT_CAPABILITY;
    }

    public Ticket park(Car car) {
        if (!isAvailable()) {
            throw new NoAvailableException(NO_AVAILABLE_POSITION);
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
        } else {
            throw new UnrecognizedTicketException(UNRECOGNIZED_PARKING_TICKET);
        }
        return fetchedCar;
    }

    public boolean isAvailable() {
        return restSlotNum != 0;
    }
}
