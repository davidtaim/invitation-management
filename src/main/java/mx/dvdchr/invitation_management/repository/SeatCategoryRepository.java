package mx.dvdchr.invitation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.SeatCategory;

@Repository
public interface SeatCategoryRepository extends JpaRepository<SeatCategory, UUID> {
    boolean existsByName(String name);
}
