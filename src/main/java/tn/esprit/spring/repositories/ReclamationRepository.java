package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Reclamation;
import tn.esprit.spring.services.Stat;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {



}
