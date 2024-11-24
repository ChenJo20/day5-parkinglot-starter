package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SuperParkingBoyTest {
    @Test
    void should_park_in_lot2_when_park_given_a_car_and_lot1_20_capacity_10_available_and_lot2_10_capacity_10_available() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        IntStream.range(0, 10).forEach(i -> parkingLot1.park(new Car()));
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingLot2.fetch(ticket);
        // Then
        assertNotNull(ticket);
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_park_in_lot1_when_park_given_a_car_and_lot1_20_capacity_10_available_and_lot2_10_capacity_5_available() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        IntStream.range(0, 10).forEach(i -> parkingLot1.park(new Car()));
        ParkingLot parkingLot2 = new ParkingLot();
        IntStream.range(0, 5).forEach(i -> parkingLot2.park(new Car()));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingLot1.fetch(ticket);
        // Then
        assertNotNull(ticket);
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_fetch_right_car_when_park_given_two_cars_and_lot1_20_capacity_10_available_and_lot2_10_capacity_5_available() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        IntStream.range(0, 10).forEach(i -> parkingLot1.park(new Car()));
        ParkingLot parkingLot2 = new ParkingLot();
        IntStream.range(0, 5).forEach(i -> parkingLot2.park(new Car()));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SuperParkingBoy parkingBoy = new SuperParkingBoy(parkingLots);
        Car carInLot1 = new Car();
        Car carInLot2 = new Car();
        // When
        Ticket ticketForCar1 = parkingBoy.park(carInLot1);
        Ticket ticketForCar2 = parkingBoy.park(carInLot2);
        Car fetchedCar1 = parkingLot1.fetch(ticketForCar1);
        Car fetchedCar2 = parkingLot2.fetch(ticketForCar2);
        // Then
        assertNotNull(ticketForCar1);
        assertNotNull(ticketForCar2);
        assertEquals(fetchedCar1, carInLot1);
        assertEquals(fetchedCar2, carInLot2);
    }
}
