package com.thalytadiniz.eclipse_hotel_system.service.Reservation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationInputDTO;
import com.thalytadiniz.eclipse_hotel_system.dto.Reservation.ReservationOutputDTO;
import com.thalytadiniz.eclipse_hotel_system.entity.Reservation;
import com.thalytadiniz.eclipse_hotel_system.enums.ReservationStatus;
import com.thalytadiniz.eclipse_hotel_system.exception.EntityNotFoundException;
import com.thalytadiniz.eclipse_hotel_system.exception.IllegalReservationStatusException;
import com.thalytadiniz.eclipse_hotel_system.repository.ReservationRepository;
import com.thalytadiniz.eclipse_hotel_system.repository.CustomerRepository;
import com.thalytadiniz.eclipse_hotel_system.repository.RoomRepository;

@Service
public class ReservationService implements IReservation {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;
    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    public ReservationService(
            ReservationRepository reservationRepository,
            CustomerRepository customerRepository,
            RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public ReservationOutputDTO createReservation(ReservationInputDTO reservationInputDTO) {
        logger.info("Criando nova reserva para o cliente ID: {}", reservationInputDTO.getCustomerId());

        Reservation reservation = new Reservation();
        reservation.setCustomer(customerRepository.findById(reservationInputDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente não encontrado com ID: " + reservationInputDTO.getCustomerId())));
        reservation.setRoom(roomRepository.findById(reservationInputDTO.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Quarto não encontrado com ID: " + reservationInputDTO.getRoomId())));
        reservation.setCheckin(reservationInputDTO.getCheckin());
        reservation.setCheckout(reservationInputDTO.getCheckout());
        reservation.setStatus(ReservationStatus.SCHEDULED);

        Reservation savedReservation = reservationRepository.save(reservation);

        return convertToOutputDTO(savedReservation);
    }

    @Override
    public List<ReservationOutputDTO> getAllReservations() {
        logger.info("Buscando todas as reservas");
        return reservationRepository.findAll().stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationOutputDTO getReservationById(Long id) {
        logger.info("Buscando reserva pelo ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com ID: " + id));
        return convertToOutputDTO(reservation);
    }

    @Override
    public ReservationOutputDTO updateReservation(Long id, ReservationInputDTO reservationInputDTO) {
        logger.info("Atualizando reserva com ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com ID: " + id));

        if (isFinalStatus(reservation.getStatus())) {
            throw new IllegalReservationStatusException("Reserva já está em um status final e não pode ser alterada.");
        }

        reservation.setCustomer(customerRepository.findById(reservationInputDTO.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente não encontrado com ID: " + reservationInputDTO.getCustomerId())));
        reservation.setRoom(roomRepository.findById(reservationInputDTO.getRoomId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Quarto não encontrado com ID: " + reservationInputDTO.getRoomId())));
        reservation.setCheckin(reservationInputDTO.getCheckin());
        reservation.setCheckout(reservationInputDTO.getCheckout());

        if (reservationInputDTO.getStatus() != null) {
            reservation.setStatus(reservationInputDTO.getStatus());
        }

        Reservation updatedReservation = reservationRepository.save(reservation);

        return convertToOutputDTO(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        logger.info("Deletando reserva com ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com ID: " + id));

        if (isFinalStatus(reservation.getStatus())) {
            throw new IllegalReservationStatusException("Reserva já está em um status final e não pode ser excluída.");
        }

        reservationRepository.delete(reservation);
    }

    @Override
    public ReservationOutputDTO closeReservation(Long id, ReservationStatus newStatus) {
        logger.warn("Encerrando reserva ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com ID: " + id));

        if (isFinalStatus(reservation.getStatus())) {
            throw new IllegalReservationStatusException("Reserva já está em um status final e não pode ser alterada.");
        }

        if (newStatus != ReservationStatus.FINISHED && newStatus != ReservationStatus.ABSENCE) {
            throw new IllegalArgumentException("Status inválido para encerramento da reserva.");
        }

        reservation.setStatus(newStatus);
        Reservation updatedReservation = reservationRepository.save(reservation);

        return convertToOutputDTO(updatedReservation);
    }

    @Override
    public List<ReservationOutputDTO> getReservationsByDateRange(LocalDate startDate, LocalDate endDate) {
        logger.info("Buscando reservas entre {} e {}", startDate, endDate);
        return reservationRepository.findByCheckinBetween(startDate, endDate).stream()
                .map(this::convertToOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationOutputDTO cancelReservation(Long id) {
        logger.warn("Cancelando reserva ID: {}", id);
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada com ID: " + id));

        if (isFinalStatus(reservation.getStatus())) {
            throw new IllegalReservationStatusException("Reserva já está em um status final e não pode ser cancelada.");
        }

        reservation.setStatus(ReservationStatus.CANCELED);
        Reservation updatedReservation = reservationRepository.save(reservation);

        return convertToOutputDTO(updatedReservation);
    }

    private boolean isFinalStatus(ReservationStatus status) {
        return status == ReservationStatus.FINISHED ||
                status == ReservationStatus.ABSENCE ||
                status == ReservationStatus.CANCELED;
    }

    private ReservationOutputDTO convertToOutputDTO(Reservation reservation) {
        ReservationOutputDTO outputDTO = new ReservationOutputDTO();
        outputDTO.setId(reservation.getId());
        outputDTO.setCustomerId(reservation.getCustomer().getId());
        outputDTO.setRoomId(reservation.getRoom().getId());
        outputDTO.setCheckin(reservation.getCheckin());
        outputDTO.setCheckout(reservation.getCheckout());
        outputDTO.setStatus(reservation.getStatus());
        outputDTO.setTotalPrice(reservation.getTotalPrice());
        return outputDTO;
    }
}