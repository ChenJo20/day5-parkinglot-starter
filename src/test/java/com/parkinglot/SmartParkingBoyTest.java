package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartParkingBoyTest {
    @Test
    void should_park_in_lot1_when_park_given_a_car_and_lot1_10_available_positions_and_lot2_10_available_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = parkingLot1.fetch(ticket);
        // Then
        assertNotNull(ticket);
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_park_in_lot2_when_park_given_a_car_and_lot1_9_available_positions_and_lot2_10_available_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        parkingLot1.park(new Car());
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // When
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = parkingLot2.fetch(ticket);
        // Then
        assertEquals(fetchedCar, car);
    }
}
