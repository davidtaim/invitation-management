package mx.dvdchr.invitation_management.mapper;

import java.time.Instant;

import mx.dvdchr.invitation_management.dto.EventRequestDTO;
import mx.dvdchr.invitation_management.dto.EventResponseDTO;
import mx.dvdchr.invitation_management.model.Event;
import mx.dvdchr.invitation_management.model.User;

public class EventMapper {

    public static EventResponseDTO toDto(Event event) {
        var eventResponseDTO = new EventResponseDTO();

        eventResponseDTO.setId(event.getId().toString());
        eventResponseDTO.setOrganizerName(event.getOrganizer().getName());
        eventResponseDTO.setTitle(event.getTitle());
        eventResponseDTO.setDescription(event.getDescription());
        eventResponseDTO.setStartDatetime(event.getStartDatetime().toString());
        eventResponseDTO.setEndDatetime(event.getEndDatetime().toString());
        eventResponseDTO.setLocation(event.getLocation());
        eventResponseDTO.setStatus(event.getStatus().toString());
        eventResponseDTO.setCreatedAt(event.getCreatedAt().toString());
        eventResponseDTO.setUpdatedAt(event.getUpdatedAt().toString());

        return eventResponseDTO;
    }

    public static Event toEntity(EventRequestDTO eventRequestDTO, User organizer) {
        var event = new Event();

        event.setOrganizer(organizer);
        event.setTitle(eventRequestDTO.getTitle());
        event.setDescription(eventRequestDTO.getDescription());
        event.setStartDatetime(Instant.parse(eventRequestDTO.getStartDatetime()));
        event.setEndDatetime(Instant.parse(eventRequestDTO.getEndDatetime()));
        event.setLocation(eventRequestDTO.getLocation());

        return event;
    }

}
