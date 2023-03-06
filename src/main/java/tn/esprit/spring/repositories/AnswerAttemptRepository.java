package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.entities.AnswerAttempt;
@Repository
public interface AnswerAttemptRepository extends JpaRepository<AnswerAttempt,Integer> {
}
