package mx.dvdchr.invitation_management.model.compositePrimaryKey;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class InvitationGuestId implements Serializable {

    @Column(name = "invitation_id")
    private UUID invitationId;

    @Column(name = "guest_id")
    private UUID guestId;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof InvitationGuestId)) {
            return false;
        }

        InvitationGuestId that = (InvitationGuestId) obj;
        return Objects.equals(this.guestId, that.guestId) &&
                Objects.equals(this.invitationId, that.invitationId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.guestId, this.invitationId);
    }

}
