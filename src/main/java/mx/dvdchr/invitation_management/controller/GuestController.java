package mx.dvdchr.invitation_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.GuestRequestDTO;
import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateGuestValidationGroup;
import mx.dvdchr.invitation_management.service.GuestService;

@RestController
@RequestMapping(path = "api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<GuestResponseDTO> create(
            @Validated({ Default.class }) @RequestBody GuestRequestDTO guestRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.guestService.create(guestRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<GuestResponseDTO>> getAll() {
        return ResponseEntity.ok().body(this.guestService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GuestResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.guestService.getById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<GuestResponseDTO> update(@PathVariable UUID id,
            @Validated({ Default.class,
                    UpdateGuestValidationGroup.class }) @RequestBody GuestRequestDTO guestRequestDTO) {
        return ResponseEntity.ok().body(this.guestService.update(id, guestRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.guestService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
