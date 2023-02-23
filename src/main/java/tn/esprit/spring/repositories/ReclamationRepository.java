package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Reclamation;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
}
