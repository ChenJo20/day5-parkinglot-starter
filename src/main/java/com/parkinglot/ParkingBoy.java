package com.parkinglot;

import com.parkinglot.parkinglotSearchStrategy.KeepInitialOrderStrategy;
import com.parkinglot.parkinglotSearchStrategy.ParkingLotSortStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots = new ArrayList<>();
    protected Map<Ticket, ParkingLot> ticketToParkingLot = new HashMap<>();
    private ParkingLotSortStrategy sortStrategy;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
        sortStrategy = new KeepInitialOrderStrategy();
    }

    public ParkingBoy(List<ParkingLot> parkingLots, ParkingLotSortStrategy sortStrategy) {
        this.parkingLots = parkingLots;
        this.sortStrategy = sortStrategy;
    }

    public Ticket park(Car car) {
        ParkingLot firstAvailableParkingLot = parkingLots.stream()
                .filter(ParkingLot::isAvailable)
                .sorted(sortStrategy.sortParkingLot())
                .findFirst().orElse(null);
        if (firstAvailableParkingLot == null) {
            throw new NoAvailableException(NO_AVAILABLE_POSITION);
        }

        Ticket ticket = firstAvailableParkingLot.park(car);
        ticketToParkingLot.put(ticket, firstAvailableParkingLot);
        return ticket;
    }

    public Car fetch(Ticket ticket) {
        ParkingLot parkingLot = ticketToParkingLot.remove(ticket);
        if (parkingLot == null) {
            throw new UnrecognizedTicketException(UNRECOGNIZED_PARKING_TICKET);
        }
        return parkingLot.fetch(ticket);
    }
}
