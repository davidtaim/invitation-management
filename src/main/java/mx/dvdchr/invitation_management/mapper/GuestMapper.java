package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.GuestRequestDTO;
import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.model.Guest;
import mx.dvdchr.invitation_management.model.Invitation;
import mx.dvdchr.invitation_management.model.InvitationGuest;

public class GuestMapper {

    public static Guest toEntity(GuestRequestDTO guestRequestDTO) {
        var guest = new Guest();

        guest.setName(guestRequestDTO.getName());
        guest.setEmail(guestRequestDTO.getEmail());
        guest.setPhone(guestRequestDTO.getPhone());
        guest.setCompany(guestRequestDTO.getCompany());

        return guest;
    }

    public static GuestResponseDTO toDto(Guest guest) {
        var guestResponseDTO = new GuestResponseDTO();

        guestResponseDTO.setId(guest.getId().toString());
        guestResponseDTO.setName(guest.getName());
        guestResponseDTO.setEmail(guest.getEmail());
        guestResponseDTO.setPhone(guest.getPhone());
        guestResponseDTO.setCompany(guest.getCompany());
        guestResponseDTO.setCreatedAt(guest.getCreatedAt().toString());
        guestResponseDTO.setUpdatedAt(guest.getUpdatedAt().toString());
        guestResponseDTO.setIsActive(guest.getIsActive());

        return guestResponseDTO;
    }

    public static InvitationGuest toEntityInvitationGuest(Invitation invitation, Guest guest) {
        var invitationGuest = new InvitationGuest();

        invitationGuest.setGuest(guest);
        invitationGuest.setInvitation(invitation);

        return invitationGuest;
    }

}
