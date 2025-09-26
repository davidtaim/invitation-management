package mx.dvdchr.invitation_management.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/guests")
public class GuestController {

    @PostMapping
    public ResponseEntity<String> create(@RequestBody String body) {
        return ResponseEntity.status(HttpStatus.CREATED).body("guest created");
    }

    @GetMapping
    public ResponseEntity<String> getAll() {
        return ResponseEntity.ok().body("list of guests");
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

}
