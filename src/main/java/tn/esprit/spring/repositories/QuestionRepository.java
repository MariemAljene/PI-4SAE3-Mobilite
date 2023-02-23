package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Type;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

    public List<Question> findQuestionByType(Type type);


}
