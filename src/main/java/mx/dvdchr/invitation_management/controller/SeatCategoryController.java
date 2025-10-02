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

import mx.dvdchr.invitation_management.dto.SeatCategoryRequestDTO;
import mx.dvdchr.invitation_management.dto.SeatCategoryResponseDTO;
import mx.dvdchr.invitation_management.service.SeatCategoryService;

@RestController
@RequestMapping(path = "api/seats/category")
public class SeatCategoryController {

    private final SeatCategoryService seatCategoryService;

    public SeatCategoryController(SeatCategoryService seatCategoryService) {
        this.seatCategoryService = seatCategoryService;
    }

    @PostMapping
    public ResponseEntity<SeatCategoryResponseDTO> create(
            @Validated() @RequestBody SeatCategoryRequestDTO seatCategoryRequestDTO) {
        var newSeatCategory = this.seatCategoryService.create(seatCategoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(newSeatCategory);
    }

    @GetMapping
    public ResponseEntity<List<SeatCategoryResponseDTO>> getAll() {
        return ResponseEntity.ok().body(this.seatCategoryService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<SeatCategoryResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.seatCategoryService.getById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<SeatCategoryResponseDTO> update(@PathVariable UUID id,
            @Validated() @RequestBody SeatCategoryRequestDTO seatCategoryRequestDTO) {
        return ResponseEntity.ok()
                .body(this.seatCategoryService.update(id, seatCategoryRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.seatCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
