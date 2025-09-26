package mx.dvdchr.invitation_management.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.exception.EmailAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.RoleNotFoundException;
import mx.dvdchr.invitation_management.exception.UUIDInvalidException;
import mx.dvdchr.invitation_management.mapper.UserMapper;
import mx.dvdchr.invitation_management.repository.RoleRepository;
import mx.dvdchr.invitation_management.repository.UserRepository;

@Service
public class AuthService {

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {

        if (this.userRepository.existsByEmail(userRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A user with this email already exists: "
                    + userRequestDTO.getEmail());
        }

        UUID validUuid;
        try {
            validUuid = UUID.fromString(userRequestDTO.getRoleId());
        } catch (IllegalArgumentException ex) {
            throw new UUIDInvalidException("UUID invalid");
        }
        var role = this.roleRepository.findById(validUuid)
                .orElseThrow(() -> new RoleNotFoundException(""));

        var user = UserMapper.toEntity(userRequestDTO, role);
        var newUser = this.userRepository.save(user);
        return UserMapper.toDto(newUser);
    }

}
