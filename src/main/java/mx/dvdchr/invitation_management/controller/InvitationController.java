package mx.dvdchr.invitation_management.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/invitations")
public class InvitationController {

    @GetMapping(path = "{id}")
    public ResponseEntity<String> get(@PathVariable UUID id) {
        return ResponseEntity.ok().body("ID: " + id.toString());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> update(@PathVariable UUID id) {
        return ResponseEntity.ok().body("updated");
    }

    @PostMapping(path = "{id}/guests")
    public ResponseEntity<String> addGuests(@PathVariable UUID id) {
        return ResponseEntity.ok().body("added guests");
    }

    @GetMapping(path = "{id}/guests")
    public ResponseEntity<String> getGuestPerInvitation(@PathVariable UUID id) {
        return ResponseEntity.ok().body("list of guest per invitation");
    }

    @PutMapping(path = "{id}/guests/{invitationGuestId}")
    public ResponseEntity<String> updateRsvp(@PathVariable UUID id, @PathVariable UUID invitationGuestID) {
        return ResponseEntity.ok().body("updated rsvp");
    }

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
