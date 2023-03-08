package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.*;
import tn.esprit.spring.services.Email.EmailService;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class Pi_MobilityImplementation implements Pi_Mobility {

@Autowired
    ApprenantRepository apprenantRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    ReponseRepository reponseRepository;
    @Autowired

    UserRepositoy userRepositoy;
    @Autowired
    RoleRepository repository;

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    ReclamationRepository reclamationRepository;

    @Autowired
    EmailService emailService;

    @Override
    public void ajouterApprenant(Apprenant apprenant) {
        apprenantRepository.save(apprenant);
    }


    //Crud les Questions
    @Override
    public void AddQuestion(Question question ){
        questionRepository.save(question);




    }

    @Override
    public List<Question>findAllQuestions(){
        return questionRepository.findAll();
    }
    @Transactional
    @Override
    public Question UpdateQuestion(Question question) {



        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> findQuestionById(Integer id) {
        return questionRepository.findById( id);
    }

    @Override
    public void deleteQuestionById(Integer id ){questionRepository.deleteById(id);}







    //crud les reponses

    @Override
    public void AddReponse(Reponse reponse){
    reponseRepository.save(reponse);

}


   @Override
    public void AddReponseAndAssignTOQuestion(Reponse rep,Integer IdQuestion)
    {

        Question question=questionRepository.findById(IdQuestion).orElse(null);

        rep.setQuestion(question);
        reponseRepository.save(rep);



    }


    @Override
    public List<Reponse> findAllReponse(){return reponseRepository.findAll();}


    @Override
    public Optional<Reponse> findReponseById(Integer id){return reponseRepository.findById(id);}



    @Override
    public Reponse UpdateReponse(Reponse reponse){
        return reponseRepository.save(reponse);
    }


    @Override
    public void deleteReponseById(Integer id){reponseRepository.deleteById(id);}


    @Override
  /*  public List<Reponse> getReponsesByHighestAverageRAting(){
        List<Reponse> reponses = reponseRepository.findAll();
        reponses.sort(Comparator.comparingDouble((Reponse r)-> r.getRatings().stream().mapToInt(Rating::getValue).average().orElse(0.0)).reversed());
        return reponses;
    }*/
    public List<Reponse> getReponsesByHighestAverageRAting(){
        List<Reponse> reponses = reponseRepository.findAll();
        Map<Reponse,Double> averageRatingsMap = new HashMap<>();
        for (Reponse reponse :reponses ){
            OptionalDouble averageRating = reponse.getRatings().stream().mapToInt(Rating::getValue).average();
            double averageRatingValue = averageRating.isPresent() ? averageRating.getAsDouble():0.0;
            averageRatingsMap.put(reponse,averageRatingValue);
        }
        return averageRatingsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }




    //////// el rating .

    @Override
    public Rating CreateRating(Rating rating,Integer idReponse){
        Reponse reponse = reponseRepository.findById(idReponse).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Rating Not found"));
        rating.setReponse(reponse);
        return ratingRepository.save(rating);


    }

    @Override
    public Optional<Rating> findRatingById(Long idRating){return ratingRepository.findById(idRating);}


    @Override
    public Rating UpdateRating(Rating rating){
        return ratingRepository.save(rating);
    }


    public List<Rating> GetAllRAtingsByValue(int value){
        return ratingRepository.findRatingByValue(value);
    }


    ////////notifications
    public List<Notification> getNotificationsFordUser(String username){
        User user =userRepositoy.findByUserName(username);
        if(user == null){
            throw new IllegalArgumentException("User not found");
        }

        List<Question> questions= user.getQuestions();
        List<Notification> notifications=new ArrayList<>();
        for (Question question:questions){
            notifications.addAll(question.getNotifications());
        }
        return notifications;
    }



    ////////////tebaa el reclamationn


    @Override
    public   void AddReclamation(Reclamation reclamation){


        reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation UpdateREclamation(Reclamation reclamation,Long idReclam) {
        if (reclamationRepository.findById(idReclam).isPresent()){

            Reclamation reclam=reclamationRepository.findById(idReclam).get();
            reclam.setComment(reclamation.getComment());
          /*  reclam.setStatu(reclamation.getStatu());
            reclam.setTraiteurUser(reclamation.getTraiteurUser());
            reclam.setDelegue(reclamation.isDelegue());*/

            System.out.println(reclam.toString());
           if(reclam.getStatu().name().equals("VERIFIE")){
                System.out.println(reclam.getOwnerUser());
                emailService.sendMail(reclam.getOwnerUser().getEmail(),"A propos Votre Reclamation ","Votre Reclamation a ete Verifiée");

            }
            reclamationRepository.save(reclam);
            return reclam;

        }
        return null;
    }



    @Override
    public Optional<Reclamation> GetReclamationById(Long idReclam){
        return reclamationRepository.findById(idReclam);
    }


    public void checkAndAddReclamation() {
        List<Reponse> reponses = reponseRepository.findAll();
        for (Reponse reponse : reponses) {
            OptionalDouble averageRating = reponse.getRatings().stream().mapToInt(Rating::getValue).average();
            double averageRatingValue = averageRating.isPresent() ? averageRating.getAsDouble() : 0.0;
            if (averageRatingValue < 2.0) {

                String comment = "This reponse is not good";
                String objet = "Rep/Quest";
                Reclamation reclamation = new Reclamation(comment,objet);
                reclamation.setObjet(objet);
                reclamationRepository.save(reclamation);
            }
        }
    }


    @Override
    public void AddUser(User user){
        userRepositoy.save(user);
    }































}
