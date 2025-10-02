package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationResponseDTO;
import mx.dvdchr.invitation_management.enums.InvitationGuestStatus;
import mx.dvdchr.invitation_management.enums.InvitationStatus;
import mx.dvdchr.invitation_management.exception.EventNotFoundException;
import mx.dvdchr.invitation_management.exception.GuestNotFoundException;
import mx.dvdchr.invitation_management.exception.InvitationNotFoundException;
import mx.dvdchr.invitation_management.exception.NotValidEnumStringException;
import mx.dvdchr.invitation_management.mapper.GuestMapper;
import mx.dvdchr.invitation_management.mapper.InvitationMapper;
import mx.dvdchr.invitation_management.repository.EventRepository;
import mx.dvdchr.invitation_management.repository.GuestRepository;
import mx.dvdchr.invitation_management.repository.InvitationGuestRepository;
import mx.dvdchr.invitation_management.repository.InvitationRepository;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    private final EventRepository eventRepository;

    private final GuestRepository guestRepository;

    private final InvitationGuestRepository invitationGuestRepository;

    public InvitationService(
            InvitationRepository invitationRepository,
            EventRepository eventRepository,
            GuestRepository guestRepository,
            InvitationGuestRepository invitationGuestRepository) {
        this.invitationRepository = invitationRepository;
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
        this.invitationGuestRepository = invitationGuestRepository;
    }

    public InvitationResponseDTO create(UUID eventId, InvitationRequestDTO invitationRequestDTO) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var invitation = InvitationMapper.toEntity(invitationRequestDTO, event);

        var newInvitation = this.invitationRepository.save(invitation);

        return InvitationMapper.toDto(newInvitation);
    }

    public List<InvitationResponseDTO> findAllByEventId(UUID eventId) {
        var event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        var invitations = this.invitationRepository.findByEvent(event);

        return invitations.stream().map(InvitationMapper::toDto).toList();
    }

    public InvitationResponseDTO findById(UUID id) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        return InvitationMapper.toDto(invitation);
    }

    //TODO add eventId as a parameter
    public InvitationResponseDTO update(UUID id, InvitationRequestDTO invitationRequestDTO) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var event = this.eventRepository.findById(UUID.fromString(invitationRequestDTO.getEventId()))
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        // TODO refactor validations
        invitation.setEvent(event);
        invitation.setStatus(InvitationStatus.valueOf(invitationRequestDTO.getStatus()));
        invitation.setSentAt(invitationRequestDTO.getSentAt() != null && invitationRequestDTO.getSentAt().length() > 0
                ? Instant.parse(invitationRequestDTO.getSentAt())
                : null);
        invitation.setRespondedAt(
                invitationRequestDTO.getRespondedAt() != null && invitationRequestDTO.getSentAt().length() > 0
                        ? Instant.parse(invitationRequestDTO.getRespondedAt())
                        : null);
        invitation.setUpdatedAt(Instant.now());

        var updatedInvitation = this.invitationRepository.save(invitation);

        return InvitationMapper.toDto(updatedInvitation);
    }

    // TODO validate duplicate guests, handle with an exception
    public InvitationResponseDTO addGuests(UUID id, List<InvitationGuestRequestDTO> guests) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guestList = guests.stream()
                .map(g -> this.guestRepository.findById(UUID.fromString(g.getGuestsId()))
                        .orElseThrow(() -> new GuestNotFoundException("Guest not found")))
                .toList();
        var invitationGuests = guestList.stream().map(g -> GuestMapper.toEntityInvitationGuest(invitation, g)).toList();

        invitationGuests.forEach(ig -> this.invitationGuestRepository.save(ig));

        var guestListDTO = guestList.stream().map(GuestMapper::toDto).toList();

        return InvitationMapper.toDtoWithGuests(invitation, guestListDTO);
    }

    public List<GuestResponseDTO> getGuestsPerInvitation(UUID id) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guests = invitation
                .getGuests()
                .stream()
                .map(ig -> this.guestRepository
                        .findById(ig.getGuest().getId())
                        .orElseThrow(() -> new GuestNotFoundException("Guest not found")))
                .map(GuestMapper::toDto).toList();

        return guests;
    }

    public void updateRsvp(UUID id, UUID invitationGuestId, String status) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        invitation.getGuests().stream().filter(g -> g.getGuest().getId().equals(invitationGuestId))
                .forEach(ig -> {
                    ig.setStatus(InvitationGuestStatus.valueOf(status));
                    this.invitationGuestRepository.save(ig);
                });
    }

}
