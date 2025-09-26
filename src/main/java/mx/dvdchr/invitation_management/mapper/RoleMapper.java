package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.RoleRequestDTO;
import mx.dvdchr.invitation_management.dto.RoleResponseDTO;
import mx.dvdchr.invitation_management.model.Role;

public class RoleMapper {

    public static RoleResponseDTO toDto(Role role) {
        var roleResponseDTO = new RoleResponseDTO();

        roleResponseDTO.setId(role.getId().toString());
        roleResponseDTO.setName(role.getName());
        roleResponseDTO.setActive(role.getIsActive());
        roleResponseDTO.setCreatedAt(role.getCreatedAt().toString());
        roleResponseDTO.setUpdatedAt(role.getUpdatedAt().toString());

        return roleResponseDTO;
    }

    public static Role toEntity(RoleRequestDTO roleRequestDTO) {
        var newRole = new Role();

        newRole.setName(roleRequestDTO.getName());

        return newRole;
    }

}
