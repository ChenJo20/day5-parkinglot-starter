package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLotTest {

    @Test
    void should_return_ticket_when_park_given_a_car() {
        // Given
        ParkingLot parkinglot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkinglot.park(car);
        // Then
        assertNotNull(ticket);
    }
}
