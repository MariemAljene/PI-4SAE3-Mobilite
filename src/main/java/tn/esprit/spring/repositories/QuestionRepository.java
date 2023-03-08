package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.QuestRep;
import tn.esprit.spring.entities.Type;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestRep,Integer> {


 //   List<QuestRep> findQuestRepByType(Type type);


}
