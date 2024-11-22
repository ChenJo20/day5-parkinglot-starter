package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticketForCar1 = parkingLot.park(car1);
        Ticket ticketForCar2 = parkingLot.park(car2);
        // When

        Car fetchedCar1 = parkingLot.fetch(ticketForCar1);
        Car fetchedCar2 = parkingLot.fetch(ticketForCar2);
        // Then
        assertEquals(car1, fetchedCar1);
        assertEquals(car2, fetchedCar2);
    }

    @Test
    void should_return_nothing__when_fetch_given_wrong_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        // When
        Car fetchedCar = parkingLot.fetch(wrongTicket);
        // Then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetch_given_an_used_ticket(){
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        // When
        Car fetchedCar = parkingLot.fetch(ticket);
        // Then
        assertNull(fetchedCar);
    }
}
