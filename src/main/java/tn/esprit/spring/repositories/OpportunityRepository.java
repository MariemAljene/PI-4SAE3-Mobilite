package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Opportunity;
@Repository

public interface OpportunityRepository extends JpaRepository<Opportunity,Integer> {
}
