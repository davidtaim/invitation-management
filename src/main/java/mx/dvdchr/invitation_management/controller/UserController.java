package mx.dvdchr.invitation_management.controller;

import java.util.List;
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

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserValidationGroup;
import mx.dvdchr.invitation_management.service.UserService;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok()
                .body(this.userService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponseDTO> get(@PathVariable UUID id) {
        return ResponseEntity.ok()
                .body(this.userService.get(id));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id,
            @Validated({ UpdateUserValidationGroup.class }) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok()
                .body(this.userService.update(id, userRequestDTO));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
