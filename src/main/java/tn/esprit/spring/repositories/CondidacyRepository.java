package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Condidacy;

public interface CondidacyRepository extends JpaRepository  <Condidacy ,Integer> {
}
