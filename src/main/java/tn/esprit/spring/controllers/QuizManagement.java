package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.interfaces.QrCodeInterface;
import tn.esprit.spring.repositories.*;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/QuizManagement")
public class QuizManagement {
    @Autowired
    QrCodeInterface qrCodeInterface;
    @Autowired
    Pi_Mobility pi_mobility;
    @Autowired
    QuizAttemptRepository quizAttemptRepository;
    @Autowired
    AnswerAttemptRepository answerAttemptRepository;
    @Autowired
    CondidacyRepository condidacyRepository;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepositoy userRepositoy;
    @PostMapping("/Quiz/AddQuizQuestionAndResponse/{questionIds}/{Id_Opportunity}")
    public ResponseEntity<?> ajouterQuiz(@RequestBody Quiz quiz, @PathVariable List<Integer> questionIds, @PathVariable Integer Id_Opportunity) {
        HashSet<Question> questions = new HashSet<>();
        for (Integer questionId : questionIds) {
            {
                Question question = questionRepository.findById(questionId).orElse(null);
                questionRepository.save(question);

                questions.add(question);
            }
        }
        quiz.setQuestions((Set<Question>) questions);
        quiz.setQuestions(questions);
        int nb = questions.size();
        quiz.setNbQuestion(nb);
        Opportunity opportunity = opportunityRepository.findById(Id_Opportunity).orElse(null);
        quiz.setOpportunity(opportunity);
        pi_mobility.ajouterQuiz(quiz, Id_Opportunity);
        quizRepository.save(quiz);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{idQuiz}/question/{Id_user}")
    public ResponseEntity<?> ajouterQuestionAuQuiz(@RequestBody Question question, String Id_user) {
        User user = userRepositoy.findById(Id_user).orElse(null);
        question.setUserQ(user);
        for (Answer answer : question.getAnswers()) {
            answer.setQuestion(question);
        }

        questionRepository.save(question);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/question/{idQuestion}/reponse")
    public ResponseEntity<?> ajouterReponseALaQuestion(@PathVariable Integer idQuestion, @RequestBody Answer reponse) {

        pi_mobility.ajouterReponse(idQuestion, reponse);
        return ResponseEntity.ok().build();
    }
    @Autowired ScheduelRepository scheduelRepository;


    @PostMapping("/{quizAttemptId}/answer/{id_answer}")
    public ResponseEntity<String> answerQuestion(@PathVariable Integer quizAttemptId, @RequestBody AnswerAttempt answerAttempt, @PathVariable Integer id_answer) {
        QuizAttempt quizAttempt = quizAttemptRepository.findById(quizAttemptId).orElse(null);
        Answer answer = answerRepository.findById(id_answer).orElse(null);
        answerAttempt.setAnswer(answer);
        answerAttempt.setQuizAttempt(quizAttempt);
        answerAttemptRepository.save(answerAttempt);
        return ResponseEntity.ok("Answer saved successfully for QuizAttempt with id: " + quizAttemptId);
    }

    @GetMapping("/{quizAttemptId}/submit/{Id_condidacy}")
    public ResponseEntity<String> submitQuizAttempt(@PathVariable Integer quizAttemptId, @PathVariable Integer Id_condidacy) {
        QuizAttempt quizAttempt = quizAttemptRepository.findById(quizAttemptId).orElse(null);
        //float i = calculateScore(quizAttempt, Id_condidacy);
        quizAttempt.setScore( calculateScore(quizAttempt, Id_condidacy));
        quizAttempt.getCondidacy().setAttempted(true);
        System.out.println(quizAttempt.getScore());
        quizAttemptRepository.save(quizAttempt);
        return ResponseEntity.ok("QuizAttempt with id: " + quizAttemptId + " submitted successfully with score: " + quizAttempt.getScore());
    }

    private float calculateScore(QuizAttempt quizAttempt, int id_condidacy) {
        // List<Quiz> quizzes = quizRepository.findAll();
        Quiz quiz = quizAttempt.getQuiz();
        Condidacy condidacy = condidacyRepository.findById(id_condidacy).orElse(null);
        if (condidacy.getId_Condidacy() == quizAttempt.getCondidacy().getId_Condidacy()) {
            for (AnswerAttempt answerAttempt : quizAttempt.getAnswerAttempts()) {
                if (answerAttempt.getAnswer().isCorrect()) {
                    float i = answerAttempt.getQuizAttempt().getScore();
                    i += answerAttempt.getAnswer().getQuestion().getPoint();
                    answerAttempt.getQuizAttempt().setScore(i);
                    answerAttemptRepository.save(answerAttempt);
                    System.out.println(i);
                }
            }
        }

        System.out.println(quizAttempt.getScore() / quiz.getNbQuestion());
        return quizAttempt.getScore() / quiz.getNbQuestion();

    }
    @Autowired
    EntityManagerFactory createEntityManager;
    @Transactional

    //  @Scheduled(fixedRate = 1000)
    public void checkQuizTimes() {
        List<QuizAttempt> quizAttempts = quizAttemptRepository.findAll();
        LocalDateTime currentTime = LocalDateTime.now();

        for (QuizAttempt quizAttempt : quizAttempts) {
            if (quizAttempt.getCondidacy() == null) {
                continue;
            }

            if (quizAttempt.getEndTime() != null && currentTime.isAfter(quizAttempt.getEndTime())) {
                float score = calculateScore(quizAttempt, quizAttempt.getCondidacy().getId_Condidacy().intValue());
                quizAttempt.setScore(score);
                quizAttempt.getCondidacy().setAttempted(true);
                quizAttemptRepository.save(quizAttempt);
            }
        }
    }



    @PostMapping("Condidacy/sendSelectedCandidatesEmailQuiz/{id_Opportunity}")
    public ResponseEntity<String> sendSelectedCandidatesEmailsQuiz(@PathVariable int id_Opportunity) {
        int n = 0;
        List<Condidacy> condidacy = condidacyRepository.findAll();
        for (Condidacy condidacy1 : condidacy) {
            if (condidacy1.getOpportunity().getId_Opportunity() == id_Opportunity) {
                n++;
            }
        }
        try {
            pi_mobility.sendSelectedCandidatesEmailsQuiz(id_Opportunity);
            return ResponseEntity.ok("Emails sent successfully to the top " + n + " candidates.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending emails: " + e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/QuizParSpecialy/Quiz/{UserName}/{Id_Quiz}")
    public List<Question> GetQuizSpecialite(@PathVariable String id, @PathVariable Integer Id_Quiz) {
        return pi_mobility.RtreiveQuestionOfQuizBySpeciality(id, Id_Quiz);

    }

    public String saveImage(MultipartFile imageFile) throws IOException {
        String imageName = imageFile.getOriginalFilename();
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get("/uploadsFiles/" + imageName);
        Files.write(path, bytes);
        return imageName;
    }

}
