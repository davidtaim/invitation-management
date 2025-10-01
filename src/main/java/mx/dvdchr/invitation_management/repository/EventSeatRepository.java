package mx.dvdchr.invitation_management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.Event;
import mx.dvdchr.invitation_management.model.EventSeat;

@Repository
public interface EventSeatRepository extends JpaRepository<EventSeat, UUID> {
    List<EventSeat> findByEvent(Event event);
    List<EventSeat> findByEventAndIsAvailable(Event event, Boolean isAvailable);
}
