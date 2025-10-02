package mx.dvdchr.invitation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.dvdchr.invitation_management.model.Attendance;
import mx.dvdchr.invitation_management.model.EventSeat;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    Attendance findBySeat(EventSeat seat);
}
