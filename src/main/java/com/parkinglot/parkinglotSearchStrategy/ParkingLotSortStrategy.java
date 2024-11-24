package com.parkinglot.parkinglotSearchStrategy;

import com.parkinglot.ParkingLot;

import java.util.Comparator;

public interface ParkingLotSortStrategy {
    Comparator<ParkingLot> sortParkingLot();
}
