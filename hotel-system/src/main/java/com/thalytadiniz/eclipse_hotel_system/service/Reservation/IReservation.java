package com.thalytadiniz.eclipse_hotel_system.service.Reservation;

import java.time.LocalDate;
import java.util.List;

import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.enums.ReservationStatus;

public interface IReservation {
    ReservationOutputDTO createReservation(ReservationInputDTO reservationInputDTO); 
    List<ReservationOutputDTO> getAllReservations(); 
    ReservationOutputDTO getReservationById(Long id);
    ReservationOutputDTO updateReservation(Long id, ReservationInputDTO reservationInputDTO); 
    void deleteReservation(Long id);
    ReservationOutputDTO closeReservation(Long id, ReservationStatus newStatus); 
    List<ReservationOutputDTO> getReservationsByDateRange(LocalDate startDate, LocalDate endDate); 
    ReservationOutputDTO cancelReservation(Long id); 
}