package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }


    public Ticket park(Car car) {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .findFirst().orElse(null);
        return firstAvailableParkingLot.park(car);
    }
}
