package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

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
    void should_return_right_car_when_fetch_given_two_cars_and_two_lots_and_each_lot_has_one_car() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();

        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car carInLot1 = new Car();
        Ticket ticketForFirstCarInLot1 = smartParkingBoy.park(carInLot1);
        IntStream.rangeClosed(0, 8).forEach(i -> parkingLot1.park(new Car()));
        Car carInLot2 = new Car();
        Ticket ticketForFirstCarInLot2 = smartParkingBoy.park(carInLot2);

        // When
        Car fetchedCarInLot2 = smartParkingBoy.fetch(ticketForFirstCarInLot2);
        Car fetchedCarInLot1 = smartParkingBoy.fetch(ticketForFirstCarInLot1);
        // Then
        assertEquals(fetchedCarInLot2, carInLot2);
        assertEquals(fetchedCarInLot1, carInLot1);
    }

    @Test
    void should_park_in_lot1_when_park_given_a_car_and_lot1_10_available_positions_and_lot2_9_available_positions() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingLot2.park(new Car());
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        // When
        Car car = new Car();
        Ticket ticket = smartParkingBoy.park(car);
        Car fetchedCar = parkingLot1.fetch(ticket);
        // Then
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_two_empty_lots_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();

        // When
        // Then
        assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(wrongTicket), UNRECOGNIZED_PARKING_TICKET);

    }
}
