package mx.dvdchr.invitation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

}
