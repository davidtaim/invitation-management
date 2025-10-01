package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.EventSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.EventSeatResponseDTO;
import mx.dvdchr.invitation_management.model.EventSeat;
import mx.dvdchr.invitation_management.model.Event;

public class EventSeatMapper {

    public static EventSeatResponseDTO toDto(EventSeat eventSeat) {
        var eventSeatResponseDTO = new EventSeatResponseDTO();

        eventSeatResponseDTO.setId(eventSeat.getId().toString());
        eventSeatResponseDTO.setEventId(eventSeat.getEvent().getId().toString());
        eventSeatResponseDTO.setEventTitle(eventSeat.getEvent().getTitle());
        eventSeatResponseDTO.setSeatNumber(eventSeat.getSeatNumber() == null ? ""
                : eventSeat.getSeatNumber());
        eventSeatResponseDTO.setTableNumber(eventSeat.getTableNumber() == null ? ""
                : eventSeat.getTableNumber());
        eventSeatResponseDTO.setSeatCategory(eventSeat.getSeatCategory() == null ? ""
                : eventSeat.getSeatCategory().getCategory());
        eventSeatResponseDTO.setIsAvailable(eventSeat.getIsAvailable());
        eventSeatResponseDTO.setCreatedAt(eventSeat.getCreatedAt().toString());
        eventSeatResponseDTO.setUpdatedAt(eventSeat.getUpdatedAt().toString());

        return eventSeatResponseDTO;
    }

    public static EventSeat toEntity(EventSeatRequestDTO eventSeatRequestDTO, Event event) {
        var eventSeat = new EventSeat();

        eventSeat.setEvent(event);
        eventSeat.setSeatNumber(eventSeatRequestDTO.getSeatNumber());
        eventSeat.setTableNumber(eventSeatRequestDTO.getTableNumber());
        eventSeat.setIsAvailable(eventSeatRequestDTO.getIsAvailable());

        return eventSeat;
    }

}
