package mx.dvdchr.invitation_management.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.exception.EmailAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.RoleNotFoundException;
import mx.dvdchr.invitation_management.mapper.UserMapper;
import mx.dvdchr.invitation_management.repository.RoleRepository;
import mx.dvdchr.invitation_management.repository.UserRepository;

@Service
public class AuthService {

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        if (this.userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A user with this email already exists: "
                    + userRequestDTO.getEmail());
        }

        var role = this.roleRepository.findById(UUID.fromString(userRequestDTO.getRoleId()))
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));

        var newPass = this.passwordEncoder.encode(userRequestDTO.getPassword());
        userRequestDTO.setPassword(newPass);

        var user = UserMapper.toEntity(userRequestDTO, role);
        var newUser = this.userRepository.save(user);
        return UserMapper.toDto(newUser);
    }

}
