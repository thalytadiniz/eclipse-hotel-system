package com.thalytadiniz.eclipse_hotel_system.dto.Room;

import com.thalytadiniz.eclipse_hotel_system.enums.RoomStatus;

public class RoomInputDTO {
    private String roomNumber;
    private String type;
    private Double price;
    private RoomStatus status;

    // Getters e Setters
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }
}