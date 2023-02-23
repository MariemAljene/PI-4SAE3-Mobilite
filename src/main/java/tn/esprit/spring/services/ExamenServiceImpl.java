package tn.esprit.spring.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Apprenant;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.interfaces.IExamenService;
import tn.esprit.spring.repositories.*;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ExamenServiceImpl implements IExamenService {

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

    @Override
    public void ajouterApprenant(Apprenant apprenant) {
        apprenantRepository.save(apprenant);
    }


    //Crud les Questions
    @Override
    public void AddQuestion(Question question){
        questionRepository.save(question);

    }

    @Override
    public List<Question>findAllQuestions(){
        return questionRepository.findAll();
    }
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


    @Override
    public List<Question> findQuestionByType(Type type){
        List<Question> Q = questionRepository.findQuestionByType(type);
        return Q;
    }





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













}
