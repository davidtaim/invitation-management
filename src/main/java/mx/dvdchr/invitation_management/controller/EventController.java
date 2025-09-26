package mx.dvdchr.invitation_management.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "api/events")
public class EventController {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody String body) {
        return ResponseEntity.status(HttpStatus.CREATED).body("event created");
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok().body("list of events");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> get(@PathVariable UUID id) {
        return ResponseEntity.ok().body("ID: " + id.toString());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> update(@PathVariable UUID id) {
        return ResponseEntity.ok().body("updated");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
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
