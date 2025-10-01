package mx.dvdchr.invitation_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.Guest;
import mx.dvdchr.invitation_management.model.Invitation;
import mx.dvdchr.invitation_management.model.InvitationGuest;
import mx.dvdchr.invitation_management.model.compositePrimaryKey.InvitationGuestId;

@Repository
public interface InvitationGuestRepository extends JpaRepository<InvitationGuest, InvitationGuestId> {
    List<InvitationGuest> findByInvitation(Invitation invitation);
    InvitationGuest findByGuest(Guest guest);
}
