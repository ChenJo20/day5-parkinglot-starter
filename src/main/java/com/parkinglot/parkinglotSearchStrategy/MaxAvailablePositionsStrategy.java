package com.parkinglot.parkinglotSearchStrategy;

import com.parkinglot.ParkingLot;

import java.util.Comparator;

public class MaxAvailablePositionsStrategy implements ParkingLotSortStrategy {

    @Override
    public Comparator<ParkingLot> sortParkingLot() {
        return Comparator.comparing(ParkingLot::getRestPositionNum).reversed();
    }
}
