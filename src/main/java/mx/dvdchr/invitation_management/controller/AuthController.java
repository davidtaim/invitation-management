package mx.dvdchr.invitation_management.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.groups.Default;
import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody String credentials) {
        return ResponseEntity.ok().body("jwt");
    }

    @PostMapping("logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.noContent().build();
    }

}
