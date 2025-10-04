package mx.dvdchr.invitation_management.service;

import java.util.UUID;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.AuthLoginRequestDTO;
import mx.dvdchr.invitation_management.dto.AuthLoginResponseDTO;
import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.exception.EmailAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.UserNotFoundException;
import mx.dvdchr.invitation_management.mapper.UserMapper;
import mx.dvdchr.invitation_management.repository.UserRepository;
import mx.dvdchr.invitation_management.util.JwtUtil;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        if (this.userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A user with this email already exists: "
                    + userRequestDTO.getEmail());
        }

        var newPass = this.passwordEncoder.encode(userRequestDTO.getPassword());
        userRequestDTO.setPassword(newPass);

        var user = UserMapper.toEntity(userRequestDTO);
        var newUser = this.userRepository.save(user);
        return UserMapper.toDto(newUser);
    }

    public AuthLoginResponseDTO login(AuthLoginRequestDTO authLoginRequestDTO) {
        var user = this.userRepository.findByEmail(authLoginRequestDTO.getEmail());

        if (!this.passwordEncoder.matches(authLoginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Bad credentials login");
        }

        String token = this.jwtUtil.generateToken(authLoginRequestDTO.getEmail(), user.getRole());

        return new AuthLoginResponseDTO(token);
    }

    public UserResponseDTO changeEmail(UUID id, UserRequestDTO userRequestDTO) {
        var user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setEmail(userRequestDTO.getEmail());

        var updatedUser = this.userRepository.save(user);

        return UserMapper.toDto(updatedUser);
    }

    public UserResponseDTO changePassword(UUID id, UserRequestDTO userRequestDTO) {
        var user = this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setPassword(userRequestDTO.getPassword());

        var updatedUser = this.userRepository.save(user);

        return UserMapper.toDto(updatedUser);
    }

}
