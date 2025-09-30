package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.EventRequestDTO;
import mx.dvdchr.invitation_management.dto.EventResponseDTO;
import mx.dvdchr.invitation_management.enums.EventStatus;
import mx.dvdchr.invitation_management.exception.EventNotFoundException;
import mx.dvdchr.invitation_management.exception.NotValidEnumStringException;
import mx.dvdchr.invitation_management.exception.UserNotFoundException;
import mx.dvdchr.invitation_management.mapper.EventMapper;
import mx.dvdchr.invitation_management.repository.EventRepository;
import mx.dvdchr.invitation_management.repository.UserRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
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

        //TODO find a better way to do this
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

}
