package com.parkinglot;

public class UnrecognizedTicketException extends RuntimeException {
    public UnrecognizedTicketException(String message) {
        super(message);
    }
}
