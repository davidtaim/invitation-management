package mx.dvdchr.invitation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.Guest;


@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {
    boolean existsByEmail(String email);
}
