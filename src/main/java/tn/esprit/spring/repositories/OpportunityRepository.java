package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity,Integer> {
}
