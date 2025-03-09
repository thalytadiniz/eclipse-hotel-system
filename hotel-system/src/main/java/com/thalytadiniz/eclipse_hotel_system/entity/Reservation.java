package com.thalytadiniz.eclipse_hotel_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.thalytadiniz.eclipse_hotel_system.enums.ReservationStatus;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerHotel customer;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomHotel room;

    @Column(nullable = false)
    private LocalDate checkin;

    @Column(nullable = false)
    private LocalDate checkout;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status = ReservationStatus.SCHEDULED; 

    private Double totalPrice;

    @PrePersist
    protected void calculateTotalPrice() {
        if (checkin != null && checkout != null && room != null) {
            long days = ChronoUnit.DAYS.between(checkin, checkout);
            totalPrice = days * room.getPrice();
        }
    }
}