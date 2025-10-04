package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.model.User;

public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        var userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setMiddleName(user.getMiddleName());
        userResponseDTO.setLastName(user.getLastName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setIsActive(user.getIsActive());
        userResponseDTO.setCreatedAt(user.getCreatedAt().toString());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt().toString());

        return userResponseDTO;
    }

    public static User toEntity(UserRequestDTO userRequestDTO) {
        var user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setMiddleName(userRequestDTO.getMiddleName());
        user.setLastName(userRequestDTO.getLastName());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        return user;
    }

}
