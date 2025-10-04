package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.exception.UserNotFoundException;
import mx.dvdchr.invitation_management.mapper.UserMapper;
import mx.dvdchr.invitation_management.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAll() {
        var users = this.userRepository.findAll();

        return users.stream().map(UserMapper::toDto).toList();
    }

    public UserResponseDTO get(UUID id) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return UserMapper.toDto(user);
    }

    public UserResponseDTO update(UUID id, UserRequestDTO userRequestDTO) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(userRequestDTO.getName());
        user.setMiddleName(userRequestDTO.getMiddleName());
        user.setLastName(userRequestDTO.getLastName());
        user.setRole(userRequestDTO.getRole());
        user.setUpdatedAt(Instant.now());

        return UserMapper.toDto(user);
    }

    public void delete(UUID id) {
        var user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        this.userRepository.delete(user);
    }

}
