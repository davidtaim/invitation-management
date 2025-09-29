package mx.dvdchr.invitation_management.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.enums.InvitationStatus;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @NotNull
    @Column(name = "unique_code")
    private UUID uniqueCode;

    @NotNull
    private InvitationStatus status = InvitationStatus.PENDING;

    @Column(name = "sent_at")
    private Instant sentAt = null;

    @Column(name = "responded_at")
    private Instant respondedAt = null;

    @NotNull
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @NotNull
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    @OneToMany(mappedBy = "invitation")
    private List<InvitationGuest> guests;

}
