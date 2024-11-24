package com.parkinglot.parkinglotSearchStrategy;

import com.parkinglot.ParkingLot;

import java.util.Comparator;

public class MaxOccupationRateStrategy implements ParkingLotSortStrategy {

    @Override
    public Comparator<ParkingLot> sortParkingLot() {
        return Comparator.comparingDouble(ParkingLot::getOccupationRate).reversed();
    }
}
