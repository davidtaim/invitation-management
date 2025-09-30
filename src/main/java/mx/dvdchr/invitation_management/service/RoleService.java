package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.RoleRequestDTO;
import mx.dvdchr.invitation_management.dto.RoleResponseDTO;
import mx.dvdchr.invitation_management.exception.RoleNameAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.RoleNotFoundException;
import mx.dvdchr.invitation_management.mapper.RoleMapper;
import mx.dvdchr.invitation_management.repository.RoleRepository;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleResponseDTO create(RoleRequestDTO roleRequestDTO) {

        if (this.roleRepository.existsByName(roleRequestDTO.getName())) {
            throw new RoleNameAlreadyExistsException("Cant be a role with the same name");
        }

        var role = RoleMapper.toEntity(roleRequestDTO);

        var newRole = this.roleRepository.save(role);

        return RoleMapper.toDto(newRole);
    }

    public List<RoleResponseDTO> getAll() {
        var roles = this.roleRepository.findAll();

        return roles.stream().map(RoleMapper::toDto).toList();
    }

    public RoleResponseDTO getById(UUID id) {
        var role = this.roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));
        return RoleMapper.toDto(role);
    }

    public RoleResponseDTO update(UUID id, RoleRequestDTO roleRequestDTO) {
        var role = this.roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));

        role.setName(roleRequestDTO.getName());
        role.setIsActive(roleRequestDTO.getIsActive());
        role.setUpdatedAt(Instant.now());
        var updatedRole = this.roleRepository.save(role);

        return RoleMapper.toDto(updatedRole);
    }

    public void delete(UUID id) {
        var role = this.roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found"));

        this.roleRepository.delete(role);
    }

}
