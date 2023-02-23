package com.example.appoitement.repositories;

import com.example.appoitement.entities.Appointement;
import com.example.appoitement.entities.Historique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHistoriqueRepository extends JpaRepository<Historique, Integer> {
}
