package mx.dvdchr.invitation_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.RoleRequestDTO;
import mx.dvdchr.invitation_management.dto.RoleResponseDTO;
import mx.dvdchr.invitation_management.mapper.RoleMapper;
import mx.dvdchr.invitation_management.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDTO create(RoleRequestDTO roleRequestDTO) {
        var role = RoleMapper.toEntity(roleRequestDTO);

        var newRole = this.roleRepository.save(role);

        return RoleMapper.toDto(newRole);
    }

    public List<RoleResponseDTO> getAll() {
        var roles = this.roleRepository.findAll();

        return roles.stream().map(RoleMapper::toDto).toList();
    }

}
