package com.parkinglot;

import com.parkinglot.parkinglotSearchStrategy.KeepInitialOrderStrategy;
import com.parkinglot.parkinglotSearchStrategy.MaxAvailablePositionsStrategy;
import com.parkinglot.parkinglotSearchStrategy.MaxOccupationRateStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static com.parkinglot.ParkingLot.NO_AVAILABLE_POSITION;
import static com.parkinglot.ParkingLot.UNRECOGNIZED_PARKING_TICKET;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
    @Test
    void should_return_ticket_when_park_given_a_car_and_two_empty_parking_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingLot1.fetch(ticket);
        // Then
        assertNotNull(ticket);
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_return_ticket_and_park_in_second_when_park_given_a_car_and_first_lot_full_second_lot_empty() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        IntStream.rangeClosed(0, 9).forEach(i -> parkingLot1.park(new Car()));
        Car car = new Car();
        // When
        Ticket ticket = parkingBoy.park(car);
        Car fetchedCar = parkingLot2.fetch(ticket);
        // Then
        assertNotNull(ticket);
        assertEquals(fetchedCar, car);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_cars_and_two_lots_and_each_lot_has_one_car_using_keep_initial_order() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new KeepInitialOrderStrategy());
        Car carInLot1 = new Car();
        Ticket ticketForFirstCarInLot1 = parkingBoy.park(carInLot1);
        IntStream.rangeClosed(0, 8).forEach(i -> parkingLot1.park(new Car()));
        Car carInLot2 = new Car();
        Ticket ticketForFirstCarInLot2 = parkingBoy.park(carInLot2);
        // When
        Car fetchedCarInLot2 = parkingBoy.fetch(ticketForFirstCarInLot2);
        Car fetchedCarInLot1 = parkingBoy.fetch(ticketForFirstCarInLot1);
        // Then
        assertEquals(fetchedCarInLot2, carInLot2);
        assertEquals(fetchedCarInLot1, carInLot1);
    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_two_empty_lots_a_wrong_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();

        // When
        // Then
        assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(wrongTicket), UNRECOGNIZED_PARKING_TICKET);

    }

    @Test
    void should_throw_unrecognized_ticket_exception_when_fetch_given_two_empty_lots_and_an_used_ticket() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket ticket = parkingBoy.park(new Car());
        parkingBoy.fetch(ticket);

        // When
        // Then
        assertThrows(UnrecognizedTicketException.class,
                () -> parkingBoy.fetch(ticket), UNRECOGNIZED_PARKING_TICKET);

    }

    @Test
    void should_throw_no_available_position_exception_when_fetch_given_two_full_lots() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        IntStream.rangeClosed(0, 9).forEach(i -> parkingLot1.park(new Car()));
        IntStream.rangeClosed(0, 9).forEach(i -> parkingLot2.park(new Car()));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        // When
        // Then
        assertThrows(NoAvailableException.class,
                () -> parkingBoy.park(new Car()), NO_AVAILABLE_POSITION);

    }

    @Test
    void should_return_right_car_when_fetch_given_two_cars_and_two_lots_and_each_lot_has_one_car_using_max_sort_strategy() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(10);
        ParkingLot parkingLot2 = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new MaxAvailablePositionsStrategy());
        Car carInLot1 = new Car();
        Ticket ticketForFirstCarInLot1 = parkingBoy.park(carInLot1);
        IntStream.rangeClosed(0, 8).forEach(i -> parkingLot1.park(new Car()));
        Car carInLot2 = new Car();
        Ticket ticketForFirstCarInLot2 = parkingBoy.park(carInLot2);
        // When
        Car fetchedCarInLot2 = parkingLot2.fetch(ticketForFirstCarInLot2);
        Car fetchedCarInLot1 = parkingLot1.fetch(ticketForFirstCarInLot1);
        // Then
        assertEquals(fetchedCarInLot2, carInLot2);
        assertEquals(fetchedCarInLot1, carInLot1);
    }

    @Test
    void should_return_right_car_when_fetch_given_two_cars_and_lot1_20_capacity_10_available_and_lot2_10_capacity_5_available_using_min_occupation_rate_sort_strategy() {
        // Given
        ParkingLot parkingLot1 = new ParkingLot(20);
        IntStream.range(0, 10).forEach(i -> parkingLot1.park(new Car()));
        ParkingLot parkingLot2 = new ParkingLot();
        IntStream.range(0, 5).forEach(i -> parkingLot2.park(new Car()));
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots, new MaxOccupationRateStrategy());
        Car carInLot1 = new Car();
        Ticket ticketForFirstCarInLot1 = parkingBoy.park(carInLot1);
        Car carInLot2 = new Car();
        Ticket ticketForFirstCarInLot2 = parkingBoy.park(carInLot2);
        // When
        Car fetchedCarInLot2 = parkingLot2.fetch(ticketForFirstCarInLot2);
        Car fetchedCarInLot1 = parkingLot1.fetch(ticketForFirstCarInLot1);
        // Then
        assertEquals(fetchedCarInLot2, carInLot2);
        assertEquals(fetchedCarInLot1, carInLot1);
    }
}
