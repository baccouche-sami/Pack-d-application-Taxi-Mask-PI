package fr.et.intechinfo.mousqinfos.taximask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.et.intechinfo.mousqinfos.taximask.models.ERole;
import fr.et.intechinfo.mousqinfos.taximask.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);

}
