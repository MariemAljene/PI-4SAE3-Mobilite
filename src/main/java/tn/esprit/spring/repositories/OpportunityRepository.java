package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.Grade;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.entities.Speciality;
import tn.esprit.spring.entities.TypeOpp;

import java.util.List;

@Repository

public interface OpportunityRepository extends JpaRepository<Opportunity,Integer> {
    List<Opportunity> findBySpecialite(Speciality speciality);
    @Query("SELECT DISTINCT o.specialite FROM Opportunity o")
    List<String> findDistinctSpecialite();

}
