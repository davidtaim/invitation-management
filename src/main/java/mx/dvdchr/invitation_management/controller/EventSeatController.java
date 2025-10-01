package mx.dvdchr.invitation_management.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.EventSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.EventSeatResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateEventSeatValidationGroup;
import mx.dvdchr.invitation_management.service.EventSeatService;

@RestController
@RequestMapping(path = "api/seats")
public class EventSeatController {

    public final EventSeatService eventSeatService;

    public EventSeatController(EventSeatService eventSeatService) {
        this.eventSeatService = eventSeatService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EventSeatResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.eventSeatService.get(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EventSeatResponseDTO> update(@PathVariable UUID id,
            @Validated({ Default.class,
                    UpdateEventSeatValidationGroup.class }) @RequestBody EventSeatRequestDTO eventSeatRequestDTO) {
        return ResponseEntity.ok()
                .body(this.eventSeatService.update(id, eventSeatRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.eventSeatService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
