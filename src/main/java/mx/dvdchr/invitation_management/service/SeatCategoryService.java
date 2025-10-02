package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.SeatCategoryRequestDTO;
import mx.dvdchr.invitation_management.dto.SeatCategoryResponseDTO;
import mx.dvdchr.invitation_management.exception.SeatCategoryNameAlreadyExistsException;
import mx.dvdchr.invitation_management.exception.SeatCategoryNotFoundException;
import mx.dvdchr.invitation_management.mapper.SeatCategoryMapper;
import mx.dvdchr.invitation_management.repository.SeatCategoryRepository;

@Service
public class SeatCategoryService {

    private final SeatCategoryRepository seatCategoryRepository;

    public SeatCategoryService(SeatCategoryRepository seatCategoryRepository) {
        this.seatCategoryRepository = seatCategoryRepository;
    }

    public SeatCategoryResponseDTO create(SeatCategoryRequestDTO seatCategoryRequestDTO) {

        if (this.seatCategoryRepository.existsByName(seatCategoryRequestDTO.getName())) {
            throw new SeatCategoryNameAlreadyExistsException("Can not be a seat category with the same name");
        }

        var seatCategory = SeatCategoryMapper.toEntity(seatCategoryRequestDTO);

        var newSeatCategory = this.seatCategoryRepository.save(seatCategory);

        return SeatCategoryMapper.toDto(newSeatCategory);
    }

    public List<SeatCategoryResponseDTO> getAll() {
        var seatCategories = this.seatCategoryRepository.findAll();

        return seatCategories.stream().map(SeatCategoryMapper::toDto).toList();
    }

    public SeatCategoryResponseDTO getById(UUID id) {
        var seatCategory = this.seatCategoryRepository.findById(id)
                .orElseThrow(() -> new SeatCategoryNotFoundException("Seat category not found"));

        return SeatCategoryMapper.toDto(seatCategory);
    }

    public SeatCategoryResponseDTO update(UUID id, SeatCategoryRequestDTO seatCategoryRequestDTO) {
        var seatCategory = this.seatCategoryRepository.findById(id)
                .orElseThrow(() -> new SeatCategoryNotFoundException("Seat category not found"));

        seatCategory.setName(seatCategoryRequestDTO.getName());
        seatCategory.setUpdatedAt(Instant.now());

        var updatedSeatCategory = this.seatCategoryRepository.save(seatCategory);

        return SeatCategoryMapper.toDto(updatedSeatCategory);
    }

    public void delete(UUID id) {
        var seatCategory = this.seatCategoryRepository.findById(id)
                .orElseThrow(() -> new SeatCategoryNotFoundException("Seat category not found"));

        this.seatCategoryRepository.delete(seatCategory);
    }

}
