package tn.esprit.spring.interfaces;


import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.spring.entities.*;

import java.util.List;
import java.util.Optional;

public interface Pi_Mobility {
     void ajouterApprenant (Apprenant apprenant);

     //tebaa les question el koll.
     void AddQuestion(Question question );
     public List<Question> findAllQuestions();
     public Question UpdateQuestion(Question question);
     public Optional<Question> findQuestionById(Integer id);

     public void deleteQuestionById(Integer id) ;







     //tebaa les reponse el koul
     void AddReponse(Reponse reponse);

     public void AddReponseAndAssignTOQuestion(Reponse rep,Integer IdQuestion);

     public List<Reponse> findAllReponse();


    public Reponse UpdateReponse(Reponse reponse);

    public Optional<Reponse> findReponseById(Integer id);


     public void deleteReponseById(Integer id);

     List<Reponse> getReponsesByHighestAverageRAting();


     //////////tebaa les Rating

    public Rating CreateRating(Rating rating,Integer idReponse);
    public Optional<Rating> findRatingById(Long idRating);

    public Rating UpdateRating(Rating rating);

    public List<Rating> GetAllRAtingsByValue(int value);




    ///// notifications
    public List<Notification> getNotificationsFordUser(@PathVariable String username);



    /////Reclamation

    void AddReclamation(Reclamation reclamation);
    public Reclamation UpdateREclamation(Reclamation reclamation,Long idReclam);


    public Optional<Reclamation> GetReclamationById(Long idReclam);

    public void checkAndAddReclamation() ;





    void AddUser(User user);










}

