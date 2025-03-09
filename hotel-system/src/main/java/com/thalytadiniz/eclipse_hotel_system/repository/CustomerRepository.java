package com.thalytadiniz.eclipse_hotel_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thalytadiniz.eclipse_hotel_system.entity.CustomerHotel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerHotel, Long> {

    boolean existsByEmail(String email);
}
