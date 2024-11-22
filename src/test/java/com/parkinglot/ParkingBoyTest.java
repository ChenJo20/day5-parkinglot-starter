package com.parkinglot;

import org.junit.jupiter.api.Test;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_given_a_car_and_a_parking_lot(){
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_a_ticket(){
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        // When
        Car fetchedCar = parkingBoy.fetch(ticket);
        // Then
        assertNotNull(fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_tickets(){
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car1 = new Car();
        Car car2 = new Car();
        Ticket ticket1 = parkingBoy.park(car1);
        Ticket ticket2 = parkingBoy.park(car2);
        // When
        Car fetchedCar1 = parkingBoy.fetch(ticket1);
        Car fetchedCar2 = parkingBoy.fetch(ticket2);
        // Then
        assertEquals(fetchedCar1, car1);
        assertEquals(fetchedCar2, car2);
    }

    @Test
    void should_throw_exception_with_message_when_fetch_given_an_used_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(ticket), UNRECOGNIZED_PARKING_TICKET);
    }

    @Test
    void should_throw_exception_with_message_when_fetch_given_a_wrong_ticket() {
        // Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        parkingBoy.park(car);
        Ticket wrongTicket = new Ticket();
        assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(wrongTicket), UNRECOGNIZED_PARKING_TICKET);

    }

    @Test
    void should_throw_exception_with_message_when_park_given_a_full_parking_lot() {
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertThrows(NoAvailableException.class,
                () -> parkingBoy.park(new Car()), NO_AVAILABLE_POSITION);
    }
}
