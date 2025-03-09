package com.thalytadiniz.eclipse_hotel_system.exception;

public class IllegalReservationStatusException extends RuntimeException {
    public IllegalReservationStatusException(String message) {
        super(message);
    }
}