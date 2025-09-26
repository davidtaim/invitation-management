package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.UserRequestDTO;
import mx.dvdchr.invitation_management.dto.UserResponseDTO;
import mx.dvdchr.invitation_management.model.Role;
import mx.dvdchr.invitation_management.model.User;

public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        var userResponseDTO = new UserResponseDTO();

        userResponseDTO.setId(user.getId().toString());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setRole(user.getRole().getName());
        userResponseDTO.setIsActive(user.getIsActive());
        userResponseDTO.setCreatedAt(user.getCreatedAt().toString());
        userResponseDTO.setUpdatedAt(user.getUpdatedAt().toString());

        return userResponseDTO;
    }

    public static User toEntity(UserRequestDTO userRequestDTO, Role role) {
        var user = new User();

        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(role);

        return user;
    }

}
