package tn.esprit.spring.interfaces;


import tn.esprit.spring.entities.Apprenant;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.entities.Type;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IExamenService {
     void ajouterApprenant (Apprenant apprenant);

     //tebaa les question el koll.
     void AddQuestion(Question question);
     public List<Question> findAllQuestions();
     public Question UpdateQuestion(Question question);
     public Optional<Question> findQuestionById(Integer id);

     public void deleteQuestionById(Integer id) ;

     public List<Question> findQuestionByType(Type type);





     //tebaa les reponse el koul
     void AddReponse(Reponse reponse);

     public void AddReponseAndAssignTOQuestion(Reponse rep,Integer IdQuestion);

     public List<Reponse> findAllReponse();


    public Reponse UpdateReponse(Reponse reponse);

    public Optional<Reponse> findReponseById(Integer id);


     public void deleteReponseById(Integer id);










}

