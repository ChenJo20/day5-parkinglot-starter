package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() throws Exception {
        return outContent.toString();
    }

    @Test
    void should_return_ticket_when_park_given_a_car() throws Exception {
        // Given
        ParkingLot parkinglot = new ParkingLot();
        Car car = new Car();
        // When
        Ticket ticket = parkinglot.park(car);
        // Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_car_when_fetch_given_a_ticket() throws Exception {
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
    void should_return_right_car_when_fetch_given_two_ticket() throws Exception {
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
    void should_return_nothing__when_fetch_given_wrong_ticket() throws Exception {
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
    void should_return_nothing_when_fetch_given_an_used_ticket() throws Exception {
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

    @Test
    void should_return_nothing_when_park_given_full_parking_lot() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        // When
        Ticket ticket = parkingLot.park(new Car());
        // Then
        assertNull(ticket);
    }

    @Test
    void should_throw_exception_with_message_when_fetch_given_an_used_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);

        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(ticket));
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());
    }

    @Test
    void should_throw_exception_with_message_when_fetch_given_a_wrong_ticket() throws Exception {
        // Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        UnrecognizedTicketException unrecognizedTicketException = assertThrows(UnrecognizedTicketException.class,
                () -> parkingLot.fetch(wrongTicket));
        assertEquals(UNRECOGNIZED_PARKING_TICKET, unrecognizedTicketException.getMessage());

    }

    @Test
    void should_throw_exception_with_message_when_park_given_a_full_parking_lot() throws Exception {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        NoAvailableException noAvailableException = assertThrows(NoAvailableException.class,
                () -> parkingLot.park(new Car()));
        assertEquals(NO_AVAILABLE_POSITION, noAvailableException.getMessage());

    }
}
