package mx.dvdchr.invitation_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.AttendanceResponseDTO;
import mx.dvdchr.invitation_management.dto.EventRequestDTO;
import mx.dvdchr.invitation_management.dto.EventResponseDTO;
import mx.dvdchr.invitation_management.dto.EventSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.EventSeatResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateEventValidationGroup;
import mx.dvdchr.invitation_management.service.EventService;
import mx.dvdchr.invitation_management.service.InvitationService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/events")
public class EventController {

    private final EventService eventService;
    private final InvitationService invitationService;

    public EventController(EventService eventService, InvitationService invitationService) {
        this.eventService = eventService;
        this.invitationService = invitationService;
    }

    @PostMapping
    public ResponseEntity<EventResponseDTO> create(
            @Validated({ Default.class }) @RequestBody EventRequestDTO eventRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.eventService.create(eventRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getAll() {
        return ResponseEntity.ok()
                .body(this.eventService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EventResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.eventService.getById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EventResponseDTO> update(@PathVariable UUID id,
            @Validated({ Default.class,
                    UpdateEventValidationGroup.class }) @RequestBody EventRequestDTO eventRequestDTO) {
        return ResponseEntity.ok()
                .body(this.eventService.update(id, eventRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.eventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "{id}/invitations")
    public ResponseEntity<InvitationResponseDTO> createInvitation(
            @PathVariable UUID id,
            @Validated({ Default.class }) @RequestBody InvitationRequestDTO invitationRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.invitationService.create(id, invitationRequestDTO));
    }

    @GetMapping(path = "{id}/invitations")
    public ResponseEntity<List<InvitationResponseDTO>> getInvitations(
            @PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.invitationService.findAllByEventId(id));
    }

    @PostMapping(path = "{eventId}/seats")
    public ResponseEntity<EventSeatResponseDTO> createSeat(@PathVariable UUID eventId,
            @Validated() @RequestBody EventSeatRequestDTO eventSeatRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.eventService.addSeat(eventId, eventSeatRequestDTO));
    }

    @PostMapping(path = "{eventId}/seats/{amount}")
    public ResponseEntity<List<EventSeatResponseDTO>> createSeats(@PathVariable UUID eventId,
            @PathVariable int amount) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.eventService.addSeats(eventId, amount));
    }

    @GetMapping(path = "{eventId}/seats")
    public ResponseEntity<List<EventSeatResponseDTO>> getSeats(@PathVariable UUID eventId) {
        return ResponseEntity.ok()
                .body(this.eventService.getSeats(eventId));
    }

    @GetMapping(path = "{eventId}/seats/available")
    public ResponseEntity<List<EventSeatResponseDTO>> getAvailableSeats(@PathVariable UUID eventId) {
        return ResponseEntity.ok()
                .body(this.eventService.getSeatsAvailable(eventId));
    }

    //TODO add a dto to add eventId if needed
    @GetMapping(path = "{eventId}/attendance")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendance(@PathVariable UUID eventId) {
        return ResponseEntity.ok().body(this.eventService.getAttendancesPerEvent(eventId));
    }
}
