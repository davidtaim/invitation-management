package mx.dvdchr.invitation_management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.dvdchr.invitation_management.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
    boolean existsByName(String name);
}
