package com.thalytadiniz.eclipse_hotel_system.dto.Reservation;

import java.time.LocalDate;
import com.thalytadiniz.eclipse_hotel_system.enums.ReservationStatus;

public class ReservationInputDTO {

    private Long customerId; 
    private Long roomId; 
    private LocalDate checkin; 
    private LocalDate checkout; 
    private ReservationStatus status;  // Adicione o campo status

    // Getters e Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}