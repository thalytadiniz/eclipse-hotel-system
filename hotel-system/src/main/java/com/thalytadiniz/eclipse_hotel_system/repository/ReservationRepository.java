package com.thalytadiniz.eclipse_hotel_system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalytadiniz.eclipse_hotel_system.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCheckinBetween(LocalDate startDate, LocalDate endDate);
}