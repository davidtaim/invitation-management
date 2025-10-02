package mx.dvdchr.invitation_management.mapper;

import mx.dvdchr.invitation_management.dto.InvitationGuestResponseDTO;
import mx.dvdchr.invitation_management.model.InvitationGuest;

public class InvitationGuestMapper {

    public static InvitationGuestResponseDTO toDto(InvitationGuest invitationGuest) {
        var invitationGuestResponseDTO = new InvitationGuestResponseDTO();

        invitationGuestResponseDTO.setGuestId(invitationGuest.getGuest().getId().toString());
        invitationGuestResponseDTO.setInvitationId(invitationGuest.getInvitation().getId().toString());
        invitationGuestResponseDTO.setStatus(invitationGuest.getStatus().name());
        invitationGuestResponseDTO.setSeatNumber(invitationGuest.getSeat().getSeatNumber());
        invitationGuestResponseDTO.setTableNumber(invitationGuest.getSeat().getTableNumber());

        return invitationGuestResponseDTO;
    }

}
