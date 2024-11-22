package com.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private Map<Ticket, ParkingLot> ticketToParkingLot = new HashMap<>();

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public Ticket park(Car car) {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst().orElse(null);
        if (firstAvailableParkingLot == null) {
            throw new NoAvailableException(NO_AVAILABLE_POSITION);
        }

        Ticket ticket = firstAvailableParkingLot.park(car);
        ticketToParkingLot.put(ticket, firstAvailableParkingLot);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkingLot = ticketToParkingLot.getOrDefault(ticket, null);
        if (parkingLot == null) {
            throw new UnrecognizedTicketException(UNRECOGNIZED_PARKING_TICKET);
        }

        Car car = parkingLot.fetch(ticket);
        ticketToParkingLot.remove(ticket);
        return car;
    }
}
