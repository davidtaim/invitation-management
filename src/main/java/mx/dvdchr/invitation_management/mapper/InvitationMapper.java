package mx.dvdchr.invitation_management.mapper;

import java.util.List;

import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationResponseDTO;
import mx.dvdchr.invitation_management.model.Event;
import mx.dvdchr.invitation_management.model.Invitation;

public class InvitationMapper {

    public static InvitationResponseDTO toDto(Invitation invitation) {
        var invitationReponseDTO = new InvitationResponseDTO();

        invitationReponseDTO.setId(invitation.getId().toString());
        invitationReponseDTO.setEventId(invitation.getEvent().getId().toString());
        invitationReponseDTO.setEventTitle(invitation.getEvent().getTitle());
        invitationReponseDTO.setUniqueCode(invitation.getUniqueCode().toString());
        invitationReponseDTO.setStatus(invitation.getStatus().name());
        invitationReponseDTO.setSentAt(invitation.getSentAt() == null ? "" : invitation.getSentAt().toString() );
        invitationReponseDTO.setRespondedAt(invitation.getRespondedAt() == null ? "" : invitation.getRespondedAt().toString());
        invitationReponseDTO.setCreatedAt(invitation.getCreatedAt().toString());
        invitationReponseDTO.setUpdatedAt(invitation.getUpdatedAt().toString());

        return invitationReponseDTO;
    }

    public static InvitationResponseDTO toDtoWithGuests(Invitation invitation, List<GuestResponseDTO> guests) {
        var invitationReponseDTO = new InvitationResponseDTO();

        invitationReponseDTO.setId(invitation.getId().toString());
        invitationReponseDTO.setEventId(invitation.getEvent().getId().toString());
        invitationReponseDTO.setEventTitle(invitation.getEvent().getTitle());
        invitationReponseDTO.setUniqueCode(invitation.getUniqueCode().toString());
        invitationReponseDTO.setStatus(invitation.getStatus().name());
        invitationReponseDTO.setSentAt(invitation.getSentAt() == null ? "" : invitation.getSentAt().toString() );
        invitationReponseDTO.setRespondedAt(invitation.getRespondedAt() == null ? "" : invitation.getRespondedAt().toString());
        invitationReponseDTO.setCreatedAt(invitation.getCreatedAt().toString());
        invitationReponseDTO.setUpdatedAt(invitation.getUpdatedAt().toString());
        invitationReponseDTO.setGuests(guests);

        return invitationReponseDTO;
    }

    public static Invitation toEntity(InvitationRequestDTO invitationRequestDTO, Event event) {
        var invitation = new Invitation();

        invitation.setEvent(event);

        return invitation;
    }

}
