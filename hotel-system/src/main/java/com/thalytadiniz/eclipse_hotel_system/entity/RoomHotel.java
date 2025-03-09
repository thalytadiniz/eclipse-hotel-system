package com.thalytadiniz.eclipse_hotel_system.entity;
import com.thalytadiniz.eclipse_hotel_system.enums.RoomStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_number", unique = true, nullable = false)
    private String roomNumber;

    private String type;
    private Double price;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}


