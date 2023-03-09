package tn.esprit.spring.controllers;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.Exceptions.*;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.interfaces.QrCodeInterface;
import tn.esprit.spring.repositories.*;

import java.io.IOException;
import java.nio.file.Paths;

@RestController
@RequestMapping("/OpportunityManagementExceptions")
public class OpportunityManagmentExceptions {
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
    @PostMapping("/Opportunity/CreateNewOpportunity/{id_Partner}")
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity, @PathVariable String id_Partner) throws IOException, WriterException {
        try {
            System.out.println(Paths.get("qr_code_template.html").toAbsolutePath().toString());
            return pi_mobility.createOpportunity(opportunity, id_Partner);
        } catch (InvalidDateRangeException | InvalidGradeException | InvalidSpecialityException | InvalidTypeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (CapacityOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
