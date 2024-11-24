package com.parkinglot.parkinglotSearchStrategy;

import com.parkinglot.ParkingLot;

import java.util.Comparator;

public class KeepInitialOrderStrategy implements ParkingLotSortStrategy {

    @Override
    public Comparator<ParkingLot> sortParkingLot() {
        return new Comparator<ParkingLot>() {
            @Override
            public int compare(ParkingLot currentParkingLot, ParkingLot nextParkingLot) {
                return 0; // 始终返回0，保持原顺序
            }
        };
    }


}
