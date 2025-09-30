package mx.dvdchr.invitation_management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateInvitationValidationGroup;
import mx.dvdchr.invitation_management.service.InvitationService;

@RestController
@RequestMapping(path = "api/invitations")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<InvitationResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.invitationService.findById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<InvitationResponseDTO> update(@PathVariable UUID id,
            @Validated({ Default.class,
                    UpdateInvitationValidationGroup.class }) @RequestBody InvitationRequestDTO invitationRequestDTO) {
        return ResponseEntity.ok()
                .body(this.invitationService.update(id, invitationRequestDTO));
    }

    @PostMapping(path = "{id}/guests")
    public ResponseEntity<InvitationResponseDTO> addGuests(@PathVariable UUID id,
            @NotEmpty(message = "Add a not empty guest list") @RequestBody() List<@Valid InvitationGuestRequestDTO> guests) {
        return ResponseEntity.ok()
                .body(this.invitationService.addGuests(id, guests));
    }

    @GetMapping(path = "{id}/guests")
    public ResponseEntity<List<GuestResponseDTO>> getGuestsPerInvitation(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.invitationService.getGuestsPerInvitation(id));
    }

    @PutMapping(path = "{id}/guests/{invitationGuestId}/{status}")
    public ResponseEntity<Void> updateRsvp(@PathVariable UUID id, @PathVariable UUID invitationGuestId,
            @PathVariable String status) {
        this.invitationService.updateRsvp(id, invitationGuestId, status);
        return ResponseEntity.noContent().build();
    }

    // TODO primero termina lo de seats
    @PostMapping(path = "{id}/attendance/{seatId}")
    public ResponseEntity<String> assignSeat(@PathVariable UUID id, @PathVariable UUID seatId) {
        return ResponseEntity.ok().body("assigned seat");
    }

    @PutMapping(path = "{id}/attendance/checkin")
    public ResponseEntity<String> checkIn(@PathVariable UUID id) {
        return ResponseEntity.ok().body("guest checked in");
    }

    @PutMapping(path = "{id}/attendance/checkout")
    public ResponseEntity<String> checkOut(@PathVariable UUID id) {
        return ResponseEntity.ok().body("guest checked out");
    }

}
