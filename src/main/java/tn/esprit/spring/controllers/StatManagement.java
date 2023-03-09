package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.QuizAttempt;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.interfaces.QrCodeInterface;
import tn.esprit.spring.interfaces.StatInterface;
import tn.esprit.spring.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/StatManagement")
public class StatManagement {
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
    @Autowired
    StatInterface statInterface;
    @GetMapping("/specialityPercentage")
    public Map<String, Double> getOpportunitiesPercentageBySpeciality() {
        return statInterface.getOpportunitiesPercentageBySpeciality();
    }
    @GetMapping("/averageQuizCompletionTime")
    public Map<String, Double> getAverageQuizCompletionTime() {
        List<QuizAttempt> quizAttempts = quizAttemptRepository.findAll();
        Map<String, Double> avgCompletionTimeByQuiz = new HashMap<>();

        for (QuizAttempt quizAttempt : quizAttempts) {
            Integer quizId = quizAttempt.getQuiz().getId_Quiz();
            LocalDateTime endTime = quizAttempt.getEndTime();
            LocalDate startDate = quizAttempt.getStartTime();
            long quizCompletionTimeSeconds = ChronoUnit.SECONDS.between(startDate.atStartOfDay(), endTime);
            double quizCompletionTimeMinutes = quizCompletionTimeSeconds / 60.0;

            if (avgCompletionTimeByQuiz.containsKey(quizId.toString())) {
                double currentAvgCompletionTime = avgCompletionTimeByQuiz.get(quizId.toString());
                double newAvgCompletionTime = (currentAvgCompletionTime + quizCompletionTimeMinutes) / 2.0;
                avgCompletionTimeByQuiz.put(quizId.toString(), newAvgCompletionTime);
            } else {
                avgCompletionTimeByQuiz.put(quizId.toString(), quizCompletionTimeMinutes);
            }
        }

        return avgCompletionTimeByQuiz;
    }


}
