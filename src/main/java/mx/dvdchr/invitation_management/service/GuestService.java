package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.GuestRequestDTO;
import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.exception.EmailAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.GuestNotFoundException;
import mx.dvdchr.invitation_management.mapper.GuestMapper;
import mx.dvdchr.invitation_management.repository.GuestRepository;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public GuestResponseDTO create(GuestRequestDTO guestRequestDTO) {

        if (this.guestRepository.existsByEmail(guestRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A user with this email already exists: "
                    + guestRequestDTO.getEmail());
        }

        var guest = GuestMapper.toEntity(guestRequestDTO);
        var newGuest = this.guestRepository.save(guest);

        return GuestMapper.toDto(newGuest);
    }

    public List<GuestResponseDTO> getAll() {
        var guests = this.guestRepository.findAll();

        return guests.stream().map(GuestMapper::toDto).toList();
    }

    public GuestResponseDTO getById(UUID id) {
        var guest = this.guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest not found"));

        return GuestMapper.toDto(guest);
    }

    public GuestResponseDTO update(UUID id, GuestRequestDTO guestRequestDTO) {
        var guest = this.guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest not found"));

        if (!guest.getEmail().equals(guestRequestDTO.getEmail()) && this.guestRepository.existsByEmail(guestRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A user with this email already exists: "
                    + guestRequestDTO.getEmail());
        }

        guest.setName(guestRequestDTO.getName());
        guest.setEmail(guestRequestDTO.getEmail());
        guest.setPhone(guestRequestDTO.getPhone());
        guest.setCompany(guestRequestDTO.getCompany());
        guest.setIsActive(guestRequestDTO.getIsActive());
        guest.setUpdatedAt(Instant.now());
        var updatedGuest = this.guestRepository.save(guest);

        return GuestMapper.toDto(updatedGuest);
    }

    public void delete(UUID id) {
        var guest = this.guestRepository.findById(id).orElseThrow(() -> new GuestNotFoundException("Guest not found"));
        this.guestRepository.delete(guest);
    }

}
