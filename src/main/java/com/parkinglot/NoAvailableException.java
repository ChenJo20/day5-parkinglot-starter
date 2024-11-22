package com.parkinglot;

public class NoAvailableException extends RuntimeException {
    public NoAvailableException(String message) {
        super(message);
    }
}
