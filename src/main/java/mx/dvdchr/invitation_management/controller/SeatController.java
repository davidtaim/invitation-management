package mx.dvdchr.invitation_management.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/seats")
public class SeatController {

    @GetMapping(path = "{id}")
    public ResponseEntity<String> get(@PathVariable UUID id) {
        return ResponseEntity.ok().body("ID: " + id.toString());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<String> update(@PathVariable UUID id) {
        return ResponseEntity.ok().body("update");
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }

}
