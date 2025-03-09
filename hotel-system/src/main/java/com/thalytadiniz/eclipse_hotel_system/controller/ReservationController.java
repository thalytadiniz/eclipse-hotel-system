package com.thalytadiniz.eclipse_hotel_system.controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.enums.ReservationStatus;
import com.thalytadiniz.eclipse_hotel_system.service.Reservation.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationOutputDTO createReservation(@RequestBody ReservationInputDTO reservationInputDTO) {
        return reservationService.createReservation(reservationInputDTO);
    }

    @GetMapping
    public List<ReservationOutputDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationOutputDTO getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PutMapping("/{id}")
    public ReservationOutputDTO updateReservation(@PathVariable Long id, @RequestBody ReservationInputDTO reservationInputDTO) {
        return reservationService.updateReservation(id, reservationInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    @PostMapping("/{id}/close")
    public ReservationOutputDTO closeReservation(@PathVariable Long id, @RequestParam ReservationStatus status) {
        return reservationService.closeReservation(id, status);
    }

    @GetMapping("/by-date-range")
    public List<ReservationOutputDTO> getReservationsByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return reservationService.getReservationsByDateRange(startDate, endDate);
    }

    @PostMapping("/{id}/cancel")
    public ReservationOutputDTO cancelReservation(@PathVariable Long id) {
        return reservationService.cancelReservation(id);
    }
}