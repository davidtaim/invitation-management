package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.SeatCategoryRequestDTO;
import mx.dvdchr.invitation_management.dto.SeatCategoryResponseDTO;
import mx.dvdchr.invitation_management.model.SeatCategory;

public class SeatCategoryMapper {

    public static SeatCategoryResponseDTO toDto(SeatCategory seatCategory) {
        var seatCategoryResponseDTO = new SeatCategoryResponseDTO();

        seatCategoryResponseDTO.setId(seatCategory.getId().toString());
        seatCategoryResponseDTO.setName(seatCategory.getName());
        seatCategoryResponseDTO.setCreatedAt(seatCategory.getCreatedAt().toString());
        seatCategoryResponseDTO.setUpdatedAt(seatCategory.getUpdatedAt().toString());

        return seatCategoryResponseDTO;
    }

    public static SeatCategory toEntity(SeatCategoryRequestDTO seatCategoryRequestDTO) {
        var seatCategory = new SeatCategory();

        seatCategory.setName(seatCategoryRequestDTO.getName());

        return seatCategory;
    }

}
