package com.thalytadiniz.eclipse_hotel_system.service.Room;

import java.util.List;

import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomOutputDTO;

public interface IRoomHotel {
    RoomOutputDTO createRoom(RoomInputDTO roomInputDTO); 
    List<RoomOutputDTO> getAllRooms(); 
    RoomOutputDTO getRoomById(Long id); 
    RoomOutputDTO updateRoom(Long id, RoomInputDTO roomInputDTO); 
    void deleteRoom(Long id);
    List<RoomOutputDTO> getOccupiedRooms();
}