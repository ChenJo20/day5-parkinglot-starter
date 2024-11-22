package com.parkinglot;

import java.util.Comparator;
import java.util.List;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .sorted(Comparator.comparingInt(ParkingLot::getRestSlotNum).reversed())
                .findFirst().orElse(null);
        if (firstAvailableParkingLot == null) {
            throw new NoAvailableException(NO_AVAILABLE_POSITION);
        }
        Ticket ticket = firstAvailableParkingLot.park(car);
        ticketToParkingLot.put(ticket, firstAvailableParkingLot);
        return ticket;
    }
}
