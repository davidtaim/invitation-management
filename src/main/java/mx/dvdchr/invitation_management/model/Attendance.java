package mx.dvdchr.invitation_management.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.enums.AttendanceStatus;

@Entity
@Table(name = "attendances")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "seat_id", unique = true)
    private EventSeat seat;

    @NotNull
    @Column(name = "checkin_time")
    private Instant checkinTime;

    @NotNull
    @Column(name = "checkout_time")
    private Instant checkoutTime;

    @NotNull
    private AttendanceStatus status = AttendanceStatus.NOT_CHECKED_IN;

    @NotNull
    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @NotNull
    @Column(name = "updated_at")
    private Instant updatedAt = Instant.now();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EventSeat getSeat() {
        return seat;
    }

    public void setSeat(EventSeat seat) {
        this.seat = seat;
    }

    public Instant getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Instant checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Instant getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Instant checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
