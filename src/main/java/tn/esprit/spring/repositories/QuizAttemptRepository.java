package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.QuizAttempt;
@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt,Integer> {
}
