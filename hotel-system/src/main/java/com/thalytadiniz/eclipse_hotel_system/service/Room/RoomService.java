package com.thalytadiniz.eclipse_hotel_system.service.Room;

import org.springframework.stereotype.Service;

import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Room.RoomOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.entity.RoomHotel;
import com.thalytadiniz.eclipse_hotel_system.enums.RoomStatus;
import com.thalytadiniz.eclipse_hotel_system.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RoomService implements IRoomHotel {

    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoomService.class);

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomOutputDTO createRoom(RoomInputDTO roomInputDTO) {
        logger.info("Criando quarto: {}", roomInputDTO.getRoomNumber());

        RoomHotel room = new RoomHotel();
        room.setRoomNumber(roomInputDTO.getRoomNumber());
        room.setType(roomInputDTO.getType());
        room.setPrice(roomInputDTO.getPrice());
        room.setStatus(roomInputDTO.getStatus());

        RoomHotel savedRoom = roomRepository.save(room);

        return convertToOutputDTO(savedRoom);
    }

    @Override
    public List<RoomOutputDTO> getAllRooms() {
        logger.info("Buscando todos os quartos");
        return roomRepository.findAll().stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomOutputDTO getRoomById(Long id) {
        logger.info("Buscando quarto pelo ID: {}", id);
        RoomHotel room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));
        return convertToOutputDTO(room);
    }

    @Override
    public RoomOutputDTO updateRoom(Long id, RoomInputDTO roomInputDTO) {
        logger.info("Atualizando quarto com ID: {}", id);
        RoomHotel room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quarto não encontrado"));

        room.setRoomNumber(roomInputDTO.getRoomNumber());
        room.setType(roomInputDTO.getType());
        room.setPrice(roomInputDTO.getPrice());
        room.setStatus(roomInputDTO.getStatus());

        RoomHotel updatedRoom = roomRepository.save(room);

        return convertToOutputDTO(updatedRoom);
    }

    @Override
    public void deleteRoom(Long id) {
        logger.info("Deletando quarto com ID: {}", id);
        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomOutputDTO> getOccupiedRooms() {
        logger.info("Buscando quartos ocupados.");
        return roomRepository.findAll().stream()
                .filter(roomHotel -> roomHotel.getStatus() == RoomStatus.OCCUPIED)
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    private RoomOutputDTO convertToOutputDTO(RoomHotel room) {
        RoomOutputDTO outputDTO = new RoomOutputDTO();
        outputDTO.setId(room.getId());
        outputDTO.setRoomNumber(room.getRoomNumber());
        outputDTO.setType(room.getType());
        outputDTO.setPrice(room.getPrice());
        outputDTO.setStatus(room.getStatus());
        return outputDTO;
    }
}