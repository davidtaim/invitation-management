package mx.dvdchr.invitation_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.UserResponseDTO;
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

}
