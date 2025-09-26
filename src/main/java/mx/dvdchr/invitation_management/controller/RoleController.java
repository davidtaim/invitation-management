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
import mx.dvdchr.invitation_management.dto.RoleRequestDTO;
import mx.dvdchr.invitation_management.dto.RoleResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateRoleValidationGroup;
import mx.dvdchr.invitation_management.service.RoleService;

@RestController
@RequestMapping(path = "api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(
            @Validated({ Default.class }) @RequestBody RoleRequestDTO roleRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.roleService.create(roleRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        return ResponseEntity.ok().body(this.roleService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(this.roleService.getById(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<RoleResponseDTO> update(@PathVariable UUID id,
            @Validated({ Default.class, UpdateRoleValidationGroup.class }) @RequestBody RoleRequestDTO roleRequestDTO) {
        return ResponseEntity.ok().body(this.roleService.update(id, roleRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.roleService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
