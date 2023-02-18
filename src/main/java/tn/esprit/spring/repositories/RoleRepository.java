package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Role;

public interface RoleRepository  extends JpaRepository<Role,String> {
}
