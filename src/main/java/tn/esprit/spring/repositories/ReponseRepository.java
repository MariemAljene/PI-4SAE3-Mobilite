package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Reponse;

public interface ReponseRepository extends JpaRepository<Reponse,Integer> {
}
