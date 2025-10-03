package mx.dvdchr.invitation_management.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.AuthLoginRequestDTO;
import mx.dvdchr.invitation_management.dto.AuthLoginResponseDTO;
import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserEmailValidationGroup;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserPasswordValidationGroup;
import mx.dvdchr.invitation_management.service.AuthService;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "register")
    public ResponseEntity<UserResponseDTO> register(
            @Validated({ Default.class }) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.authService.register(userRequestDTO));
    }

    @PutMapping(path = "change/email/{id}")
    public ResponseEntity<UserResponseDTO> changeEmail(@PathVariable UUID id,
            @Validated({ UpdateUserEmailValidationGroup.class }) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok().body(this.authService.changeEmail(id, userRequestDTO));
    }

    @PutMapping(path = "change/password/{id}")
    public ResponseEntity<UserResponseDTO> changePassword(@PathVariable UUID id,
            @Validated({ UpdateUserPasswordValidationGroup.class }) @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok().body(this.authService.changePassword(id, userRequestDTO));
    }

    @PostMapping("login")
    public ResponseEntity<AuthLoginResponseDTO> login(
            @Validated() @RequestBody AuthLoginRequestDTO authLoginRequestDTO) {
        return ResponseEntity.ok().body(this.authService.login(authLoginRequestDTO));
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

}
