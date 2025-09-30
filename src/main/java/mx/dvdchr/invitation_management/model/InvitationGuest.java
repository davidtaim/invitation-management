package mx.dvdchr.invitation_management.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.enums.InvitationGuestStatus;
import mx.dvdchr.invitation_management.model.compositePrimaryKey.InvitationGuestId;

@Entity
@Table(name = "invitation_guest")
public class InvitationGuest {

    @EmbeddedId
    private InvitationGuestId id = new InvitationGuestId();

    @ManyToOne
    @MapsId("invitationId")
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;

    @ManyToOne
    @MapsId("guestId")
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "seat_id", unique = true)
    private EventSeat seat;

    @NotNull
    private InvitationGuestStatus status = InvitationGuestStatus.PENDING;

    public InvitationGuestId getId() {
        return id;
    }

    public void setId(InvitationGuestId id) {
        this.id = id;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public EventSeat getSeat() {
        return seat;
    }

    public void setSeat(EventSeat seat) {
        this.seat = seat;
    }

    public InvitationGuestStatus getStatus() {
        return status;
    }

    public void setStatus(InvitationGuestStatus status) {
        this.status = status;
    }
}
