package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.EventRequestDTO;
import mx.dvdchr.invitation_management.dto.EventResponseDTO;
import mx.dvdchr.invitation_management.dto.EventSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.EventSeatResponseDTO;
import mx.dvdchr.invitation_management.enums.EventStatus;
import mx.dvdchr.invitation_management.exception.EventNotFoundException;
import mx.dvdchr.invitation_management.exception.NotValidEnumStringException;
import mx.dvdchr.invitation_management.exception.UserNotFoundException;
import mx.dvdchr.invitation_management.mapper.EventMapper;
import mx.dvdchr.invitation_management.mapper.EventSeatMapper;
import mx.dvdchr.invitation_management.model.EventSeat;
import mx.dvdchr.invitation_management.repository.EventRepository;
import mx.dvdchr.invitation_management.repository.EventSeatRepository;
import mx.dvdchr.invitation_management.repository.UserRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventSeatRepository eventSeatRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository,
            EventSeatRepository eventSeatRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventSeatRepository = eventSeatRepository;
    }

    public EventResponseDTO create(EventRequestDTO eventRequestDTO) {

        var organizer = this.userRepository.findById(UUID.fromString(eventRequestDTO.getOrganizerId()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        var event = EventMapper.toEntity(eventRequestDTO, organizer);

        var newEvent = this.eventRepository.save(event);

        return EventMapper.toDto(newEvent);
    }

    public List<EventResponseDTO> getAll() {
        var events = this.eventRepository.findAll();

        return events.stream().map(EventMapper::toDto).toList();
    }

    public EventResponseDTO getById(UUID id) {
        var event = this.eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        return EventMapper.toDto(event);
    }

    public EventResponseDTO update(UUID id, EventRequestDTO eventRequestDTO) {
        var event = this.eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var organizer = this.userRepository.findById(UUID.fromString(eventRequestDTO.getOrganizerId()))
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        EventStatus validStatus = null;

        // TODO find a better way to do this
        try {
            validStatus = EventStatus.valueOf(eventRequestDTO.getStatus());
        } catch (IllegalArgumentException ex) {
            throw new NotValidEnumStringException("EventStatus not valid");
        }

        event.setOrganizer(organizer);
        event.setTitle(eventRequestDTO.getTitle());
        event.setDescription(eventRequestDTO.getDescription());
        event.setStartDatetime(Instant.parse(eventRequestDTO.getStartDatetime()));
        event.setEndDatetime(Instant.parse(eventRequestDTO.getEndDatetime()));
        event.setLocation(eventRequestDTO.getLocation());
        event.setStatus(validStatus);
        event.setUpdatedAt(Instant.now());

        var updatedEvent = this.eventRepository.save(event);
        return EventMapper.toDto(updatedEvent);
    }

    public void delete(UUID id) {
        var event = this.eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        this.eventRepository.delete(event);
    }

    public EventSeatResponseDTO addSeat(UUID eventId, EventSeatRequestDTO eventSeatRequestDTO) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var eventSeat = EventSeatMapper.toEntity(eventSeatRequestDTO, event);

        var newEventSeat = this.eventSeatRepository.save(eventSeat);

        return EventSeatMapper.toDto(newEventSeat);
    }

    public List<EventSeatResponseDTO> addSeats(UUID eventId, int amount) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        List<EventSeat> eventSeats = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            var eventSeat = new EventSeat();
            eventSeat.setEvent(event);
            var newEventSeat = this.eventSeatRepository.save(eventSeat);
            eventSeats.add(newEventSeat);
        }

        return eventSeats.stream().map(EventSeatMapper::toDto).toList();
    }

    public List<EventSeatResponseDTO> getSeats(UUID eventId) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var seats = this.eventSeatRepository.findByEvent(event);

        return seats.stream().map(EventSeatMapper::toDto).toList();
    }

    public List<EventSeatResponseDTO> getSeatsAvailable(UUID eventId) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var seats = this.eventSeatRepository.findByEventAndIsAvailable(event, true);

        return seats.stream().map(EventSeatMapper::toDto).toList();
    }

}
