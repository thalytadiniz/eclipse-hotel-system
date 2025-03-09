package com.thalytadiniz.eclipse_hotel_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalytadiniz.eclipse_hotel_system.entity.RoomHotel;

@Repository
public interface RoomRepository extends JpaRepository<RoomHotel, Long> {
}
