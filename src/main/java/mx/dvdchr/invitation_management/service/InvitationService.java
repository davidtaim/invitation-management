package mx.dvdchr.invitation_management.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import mx.dvdchr.invitation_management.dto.AttendanceResponseDTO;
import mx.dvdchr.invitation_management.dto.GuestResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestResponseDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestSeatRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationGuestStatusRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationRequestDTO;
import mx.dvdchr.invitation_management.dto.InvitationResponseDTO;
import mx.dvdchr.invitation_management.enums.AttendanceStatus;
import mx.dvdchr.invitation_management.enums.InvitationGuestStatus;
import mx.dvdchr.invitation_management.enums.InvitationStatus;
import mx.dvdchr.invitation_management.exception.EventNotFoundException;
import mx.dvdchr.invitation_management.exception.EventSeatNotFoundException;
import mx.dvdchr.invitation_management.exception.GuestNotFoundException;
import mx.dvdchr.invitation_management.exception.InvitationNotFoundException;
import mx.dvdchr.invitation_management.mapper.AttendanceMapper;
import mx.dvdchr.invitation_management.mapper.GuestMapper;
import mx.dvdchr.invitation_management.mapper.InvitationGuestMapper;
import mx.dvdchr.invitation_management.mapper.InvitationMapper;
import mx.dvdchr.invitation_management.model.Attendance;
import mx.dvdchr.invitation_management.repository.AttendanceRepository;
import mx.dvdchr.invitation_management.repository.EventRepository;
import mx.dvdchr.invitation_management.repository.EventSeatRepository;
import mx.dvdchr.invitation_management.repository.GuestRepository;
import mx.dvdchr.invitation_management.repository.InvitationGuestRepository;
import mx.dvdchr.invitation_management.repository.InvitationRepository;

@Service
public class InvitationService {

    private final InvitationRepository invitationRepository;

    private final EventRepository eventRepository;

    private final GuestRepository guestRepository;

    private final InvitationGuestRepository invitationGuestRepository;

    private final EventSeatRepository eventSeatRepository;

    protected final AttendanceRepository attendanceRepository;

    public InvitationService(
            InvitationRepository invitationRepository,
            EventRepository eventRepository,
            GuestRepository guestRepository,
            InvitationGuestRepository invitationGuestRepository,
            EventSeatRepository eventSeatRepository,
            AttendanceRepository attendanceRepository) {
        this.invitationRepository = invitationRepository;
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
        this.invitationGuestRepository = invitationGuestRepository;
        this.eventSeatRepository = eventSeatRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public List<InvitationResponseDTO> getAll() {
        var invitations = this.invitationRepository.findAll();

        return invitations.stream().map(InvitationMapper::toDto).toList();
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

    // TODO add eventId as a parameter
    public InvitationResponseDTO update(UUID id, InvitationRequestDTO invitationRequestDTO) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var event = this.eventRepository.findById(UUID.fromString(invitationRequestDTO.getEventId()))
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        // TODO refactor validations
        invitation.setEvent(event);
        invitation.setStatus(InvitationStatus.valueOf(invitationRequestDTO.getStatus()));
        invitation.setSentAt(invitationRequestDTO.getSentAt() != null
                && invitationRequestDTO.getSentAt().length() > 0
                        ? Instant.parse(invitationRequestDTO.getSentAt())
                        : null);
        invitation.setRespondedAt(
                invitationRequestDTO.getRespondedAt() != null
                        && invitationRequestDTO.getSentAt().length() > 0
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
        var invitationGuests = guestList.stream().map(g -> GuestMapper.toEntityInvitationGuest(invitation, g))
                .toList();

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

    public InvitationGuestResponseDTO updateRsvp(UUID id, UUID guestId,
            InvitationGuestStatusRequestDTO invitationGuestStatusRequestDTO) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guest = invitation.getGuests().stream().filter(g -> g.getGuest().getId().equals(guestId))
                .toList();

        if (guest.size() <= 0) {
            throw new GuestNotFoundException("Guest not found on this invitation");
        }

        // TODO check this
        var singleGuest = guest.get(0);

        singleGuest.setStatus(InvitationGuestStatus.valueOf(invitationGuestStatusRequestDTO.getStatus()));

        var newSingleGuest = this.invitationGuestRepository.save(singleGuest);

        return InvitationGuestMapper.toDto(newSingleGuest);
    }

    public InvitationGuestResponseDTO assignSeat(UUID id, UUID guestId,
            InvitationGuestSeatRequestDTO invitationGuestSeatRequestDTO) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guest = invitation.getGuests().stream().filter(g -> g.getGuest().getId().equals(guestId))
                .toList();

        if (guest.size() <= 0) {
            throw new GuestNotFoundException("Guest not found on this invitation");
        }

        var seat = this.eventSeatRepository.findById(UUID.fromString(invitationGuestSeatRequestDTO.getSeatId()))
                .orElseThrow(() -> new EventSeatNotFoundException("Seat not found"));

        var singleGuest = guest.get(0);

        singleGuest.setSeat(seat);

        var newSingleGuest = this.invitationGuestRepository.save(singleGuest);

        return InvitationGuestMapper.toDto(newSingleGuest);
    }

    public void checkinIn(UUID id, UUID guestId) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guest = invitation.getGuests().stream().filter(g -> g.getGuest().getId().equals(guestId))
                .toList();

        if (guest.size() <= 0) {
            throw new GuestNotFoundException("Guest not found on this invitation");
        }

        // TODO rewrite this
        var singleGuest = guest.get(0);

        var seat = this.eventSeatRepository.findById(singleGuest.getSeat().getId())
                .orElseThrow(() -> new EventSeatNotFoundException("Seat not found"));

        seat.setIsAvailable(false);

        singleGuest.setSeat(seat);

        var attendance = new Attendance();

        attendance.setCheckinTime(Instant.now());
        attendance.setSeat(seat);
        attendance.setStatus(AttendanceStatus.CHECKED_IN);

        this.attendanceRepository.save(attendance);
        this.invitationGuestRepository.save(singleGuest);
    }

    public void checkinOut(UUID id, UUID guestId) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        var guest = invitation.getGuests().stream().filter(g -> g.getGuest().getId().equals(guestId))
                .toList();

        if (guest.size() <= 0) {
            throw new GuestNotFoundException("Guest not found on this invitation");
        }

        // TODO rewrite this
        var singleGuest = guest.get(0);

        var seat = this.eventSeatRepository.findById(singleGuest.getSeat().getId())
                .orElseThrow(() -> new EventSeatNotFoundException("Seat not found"));

        var attendance = this.attendanceRepository.findBySeat(seat);

        attendance.setCheckoutTime(Instant.now());
        attendance.setSeat(seat);
        attendance.setStatus(AttendanceStatus.LEFT);

        this.attendanceRepository.save(attendance);
        this.invitationGuestRepository.save(singleGuest);
    }

    public List<AttendanceResponseDTO> getAttendancePerInvitation(UUID id) {
        var invitation = this.invitationRepository.findById(id)
                .orElseThrow(() -> new InvitationNotFoundException("Invitation not found"));

        List<AttendanceResponseDTO> attendances = new ArrayList<>();

        invitation.getGuests().forEach(g -> {
            var attendance = this.attendanceRepository.findBySeat(g.getSeat());
            if (attendance != null) {
                attendances.add(AttendanceMapper.toDto(attendance, g.getGuest().getId().toString()));
            }
        });

        return attendances;

    }

}
