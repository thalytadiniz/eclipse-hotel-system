package com.thalytadiniz.eclipse_hotel_system.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.service.Room.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomHotelController {

    private final RoomService roomService;

    public RoomHotelController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public RoomOutputDTO createRoom(@RequestBody RoomInputDTO roomInputDTO) {
        return roomService.createRoom(roomInputDTO);
    }

    @GetMapping
    public List<RoomOutputDTO> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public RoomOutputDTO getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PutMapping("/{id}")
    public RoomOutputDTO updateRoom(@PathVariable Long id, @RequestBody RoomInputDTO roomInputDTO) {
        return roomService.updateRoom(id, roomInputDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @GetMapping("/occupied")
    public List<RoomOutputDTO> getOccupiedRooms() {
        return roomService.getOccupiedRooms();
    }
}