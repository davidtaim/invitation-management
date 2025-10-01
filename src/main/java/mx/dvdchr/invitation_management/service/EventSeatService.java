package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.EventSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.EventSeatResponseDTO;
import mx.dvdchr.invitation_management.exception.EventSeatNotFoundException;
import mx.dvdchr.invitation_management.mapper.EventSeatMapper;
import mx.dvdchr.invitation_management.repository.EventSeatRepository;

@Service
public class EventSeatService {

    private final EventSeatRepository eventSeatRepository;

    public EventSeatService(EventSeatRepository eventSeatRepository) {
        this.eventSeatRepository = eventSeatRepository;
    }

    public EventSeatResponseDTO get(UUID id) {
        var eventSeat = this.eventSeatRepository.findById(id)
        .orElseThrow(() -> new EventSeatNotFoundException("Event seat not found"));

        return EventSeatMapper.toDto(eventSeat);
    }

    public EventSeatResponseDTO update(UUID id, EventSeatRequestDTO eventSeatRequestDTO) {
        var eventSeat = this.eventSeatRepository.findById(id)
        .orElseThrow(() -> new EventSeatNotFoundException("Event seat not found"));

        eventSeat.setSeatNumber(eventSeatRequestDTO.getSeatNumber());
        eventSeat.setTableNumber(eventSeatRequestDTO.getTableNumber());
        eventSeat.setIsAvailable(eventSeatRequestDTO.getIsAvailable());
        eventSeat.setUpdatedAt(Instant.now());

        var updatedEventSeat = this.eventSeatRepository.save(eventSeat);

        return EventSeatMapper.toDto(updatedEventSeat);
    }

    public void delete(UUID id) {
        var eventSeat = this.eventSeatRepository.findById(id)
        .orElseThrow(() -> new EventSeatNotFoundException("Event seat not found"));

        this.eventSeatRepository.delete(eventSeat);
    }

}
