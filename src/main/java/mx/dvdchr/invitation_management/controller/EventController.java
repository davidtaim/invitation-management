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
import mx.dvdchr.invitation_management.dto.EventRequestDTO;
import mx.dvdchr.invitation_management.dto.EventResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateEventValidationGroup;
import mx.dvdchr.invitation_management.service.EventService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
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
    public ResponseEntity<Void> createInvitation() {
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "{id}/invitations")
    public ResponseEntity<String> getInvitations() {
        return ResponseEntity.ok().body("list of invitations for this event");
    }

    @PostMapping(path = "{id}/seats")
    public ResponseEntity<String> createSeats() {
        return ResponseEntity.status(HttpStatus.CREATED).body("seats created");
    }

    @GetMapping(path = "{id}/seats")
    public ResponseEntity<String> getSeats() {
        return ResponseEntity.ok().body("list of seats");
    }

    @GetMapping(path = "{id}/seats/available")
    public ResponseEntity<String> getAvailableSeats() {
        return ResponseEntity.ok().body("list of available seats");
    }

    @GetMapping(path = "{id}/attendance")
    public ResponseEntity<String> getAttendance(@PathVariable UUID id) {
        return ResponseEntity.ok().body("list of attendance");
    }
}
