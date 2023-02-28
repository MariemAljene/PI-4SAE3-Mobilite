package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.*;

import java.util.*;

@RestController
@RequestMapping("/Pi_Mobility")
public class ExamenRestController {
    @Autowired
    Pi_Mobility pi_mobility;
    @Autowired
    QuizAttemptRepository quizAttemptRepository;
    @Autowired
    AnswerAttemptRepository answerAttemptRepository;

    @GetMapping("/Opportunity/GetAll")
    public List<Opportunity> getAllOpportunities() {
        return pi_mobility.findAllOpportunities();
    }

    @GetMapping("/Opportunity/GetById/{id}")
    public ResponseEntity<Opportunity> getOpportunityById(@PathVariable Integer id) {
        Optional<Opportunity> opportunity = pi_mobility.findOpportunityById(id);
        if (opportunity.isPresent()) {
            return ResponseEntity.ok(opportunity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/Opportunity/CreateNewOpportunity/{id_Partner}")
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity, @PathVariable String id_Partner) {

        return pi_mobility.createOpportunity(opportunity, id_Partner);
    }

    @PutMapping("/Opportunity/Update/{id}")
    public ResponseEntity<Opportunity> updateOpportunity(@PathVariable Integer id, @RequestBody Opportunity opportunity) {
        Optional<Opportunity> existingOpportunity = pi_mobility.findOpportunityById(id);
        if (existingOpportunity.isPresent()) {
            opportunity.setId_Opportunity(id);
            return ResponseEntity.ok(pi_mobility.updateOpportunity(opportunity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Opportunity/Delete/{id}")
    public ResponseEntity<Void> deleteOpportunity(@PathVariable Integer id) {
        Optional<Opportunity> existingOpportunity = pi_mobility.findOpportunityById(id);
        if (existingOpportunity.isPresent()) {
            pi_mobility.deleteOpportunityById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ///////////////////   Condidacy
    @GetMapping("/Condidacy/GetAllCondidacies")
    public List<Condidacy> getAllCandidates() {
        return pi_mobility.findAllCandidates();
    }

    @GetMapping("/Condidacy/GetCondidcayById/{id}")
    public ResponseEntity<Condidacy> getCandidateById(@PathVariable Integer id) {
        Optional<Condidacy> candidate = pi_mobility.findCandidateById(id);
        if (candidate.isPresent()) {
            return ResponseEntity.ok(candidate.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/Condidacy/GetOpportunitySpecialite/{id}")
    public List<Opportunity> GetOpportunitySpecialite(@PathVariable String id) {
        return pi_mobility.RetreiveOpportunitiesForStudentbySpecialite(id);

    }

    @PostMapping("/Condidacy/CreateNew/{id_Student}/{id_Opportunity}")
    public Condidacy createCandidate(@RequestBody Condidacy candidate, @PathVariable String id_Student, @PathVariable int id_Opportunity) {
        return pi_mobility.createCandidateAndAssignEtudiant(candidate, id_Student, id_Opportunity);
    }

    @PutMapping("/Condidacy/UpdateCondidacy/{id}")
    public ResponseEntity<Condidacy> updateCandidate(@PathVariable Integer id, @RequestBody Condidacy candidate) {
        Optional<Condidacy> existingCandidate = pi_mobility.findCandidateById(id);
        if (existingCandidate.isPresent()) {
            candidate.setId_Condidacy(id);
            return ResponseEntity.ok(pi_mobility.updateCandidate(candidate));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/Condidacy/DeleteCondidacy/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Integer id) {
        Optional<Condidacy> existingCandidate = pi_mobility.findCandidateById(id);
        if (existingCandidate.isPresent()) {
            pi_mobility.deleteCandidateById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/Condidacy/GetCondidaciesOfStudent/{id}")
    public List<Condidacy> GetCondidaciesOfStudent(@PathVariable String id) {
        return pi_mobility.RtreiveStudentCondidacies(id);

    }

    @ResponseBody
    @GetMapping("/Condidacy/UpdateStatus/{id}")
    public Condidacy UpdateStatus(@PathVariable Integer id) {
        return pi_mobility.UpdateCondidacy(id);

    }

    @ResponseBody
    @GetMapping("/CalculScore/{opportuniteId}")
    public List<Condidacy> CalculScore(@PathVariable Integer opportuniteId) {
        return pi_mobility.CalculScore(opportuniteId);

    }

    @ResponseBody
    @GetMapping("/Trie/{opportuniteId}")
    public List<Condidacy> Trie(@PathVariable Integer opportuniteId) {
        return pi_mobility.trierEtudiantsParScore(opportuniteId);

    }


    @GetMapping("candidatures/top/{opportunityId}")
    public List<Condidacy> getTopNCandidatures(@PathVariable Integer opportunityId) {


        return pi_mobility.getTopNCandidatures(opportunityId);
    }

    @Autowired
    CondidacyRepository condidacyRepository;

    /*@PostMapping("Condidacy/sendEmailToTopNCandidates/{id_Opportunity}")
    public ResponseEntity<String> SENDMail(@PathVariable int id_Opportunity) {
        int n = 0;
        List<Condidacy> condidacy = condidacyRepository.findAll();
        for (Condidacy condidacy1 : condidacy) {
            if (condidacy1.getOpportunity().getId_Opportunity() == id_Opportunity) {
                n++;
            }
        }
        try {
            pi_mobility.sendSelectedCandidatesEmails(id_Opportunity);
            return ResponseEntity.ok("Emails sent successfully to the top " +opportunityRepository.findById(id_Opportunity).get().getCapacity()  + " candidates.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending emails: " + e.getMessage());
        }
    }*/
    @Autowired
    OpportunityRepository opportunityRepository;

    @PostMapping("Condidacy/sendSelectedCandidatesEmailsTest/{id_Opportunity}")
    public ResponseEntity<String> sendSelectedCandidatesEmailsTest(@PathVariable int id_Opportunity) {
        int n = 0;
        List<Condidacy> condidacy = condidacyRepository.findAll();
        for (Condidacy condidacy1 : condidacy) {
            if (condidacy1.getOpportunity().getId_Opportunity() == id_Opportunity) {
                n++;
            }
        }
        try {
            pi_mobility.sendSelectedCandidatesEmailsTest(id_Opportunity);
            return ResponseEntity.ok("Emails sent successfully to the top " + n + " candidates.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending emails: " + e.getMessage());
        }
    }

 /*   @PostMapping("Quiz/addQuizWith")
    public ResponseEntity<String> addQuizWithQuestionsAndAnswers(@RequestBody Quiz quiz) {
      pi_mobility.ajouterQuizAvecQuestionsEtReponses(quiz);
        return ResponseEntity.ok("addedQuiz");
    }*/


    @PostMapping("/Quiz/AddQuizQuestionAndResponse/{questionIds}/{Id_Opportunity}")
    public ResponseEntity<?> ajouterQuiz(@RequestBody Quiz quiz, @PathVariable List<Integer> questionIds, @PathVariable Integer Id_Opportunity) {
        HashSet<Question> questions = new HashSet<>();
        for (Integer questionId : questionIds) {

           {
               Question question=questionRepository.findById(questionId).orElse(null);
               questionRepository.save(question);

               questions.add(question);
           }
        }
        quiz.setQuestions((Set<Question>) questions);
quiz.setQuestions(questions);
        pi_mobility.ajouterQuiz(quiz, Id_Opportunity);
        quizRepository.save(quiz);

        return ResponseEntity.ok().build();
    }
@Autowired
QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;

    @PostMapping("/{idQuiz}/question")
    public ResponseEntity<?> ajouterQuestionAuQuiz( @RequestBody Question question) {
        for(Answer answer: question.getAnswers())
        {
            answer.setQuestion(question);
        }
       questionRepository.save(question);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/question/{idQuestion}/reponse")
    public ResponseEntity<?> ajouterReponseALaQuestion(@PathVariable Integer idQuestion, @RequestBody Answer reponse) {
        pi_mobility.ajouterReponse( idQuestion,reponse);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/startQuiz    ")
    public ResponseEntity<String> startQuizAttempt(@RequestBody QuizAttempt quizAttempt) {
        QuizAttempt savedQuizAttempt = quizAttemptRepository.save(quizAttempt);
        return ResponseEntity.ok("QuizAttempt started with id: " + savedQuizAttempt.getIdQuizAttempt());
    }

    @PostMapping("/{quizAttemptId}/answer")
    public ResponseEntity<String> answerQuestion(@PathVariable Integer quizAttemptId,
                                                 @RequestBody AnswerAttempt answerAttempt) {
        QuizAttempt quizAttempt = quizAttemptRepository.findById(quizAttemptId).orElse(null);

        answerAttempt.setQuizAttempt(quizAttempt);
        answerAttemptRepository.save(answerAttempt);
        return ResponseEntity.ok("Answer saved successfully for QuizAttempt with id: " + quizAttemptId);
    }

    @GetMapping("/{quizAttemptId}/submit")
    public ResponseEntity<String> submitQuizAttempt(@PathVariable Integer quizAttemptId) {
        QuizAttempt quizAttempt = quizAttemptRepository.getById(quizAttemptId);
        quizAttempt.setScore(calculateScore(quizAttempt));
        quizAttemptRepository.save(quizAttempt);
        return ResponseEntity.ok("QuizAttempt with id: " + quizAttemptId + " submitted successfully with score: " + quizAttempt.getScore());
    }

    private int calculateScore(QuizAttempt quizAttempt) {
        int score = 0;
        for (AnswerAttempt answerAttempt : quizAttempt.getAnswerAttempts()) {
            if (answerAttempt.getAnswer().isCorrect()) {
                score++;
            }
        }
        return score;
    }
}

