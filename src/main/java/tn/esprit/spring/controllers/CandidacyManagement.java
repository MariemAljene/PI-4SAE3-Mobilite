package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.*;


@RestController
@RequestMapping("/CandidacyManagement")
public class CandidacyManagement {
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







    ///////////////////   Condidacy
    @GetMapping("/Condidacy/GetAllCondidacies")
    public List<Condidacy> getAllCandidates() {
        return pi_mobility.findAllCandidates();
    }
    @GetMapping("/get-candidacy-history/{id-user}")
    public List<HistoryCandidaciesDTO> getCandidacyHistory(@PathVariable("id-user") String idUser) {
        return pi_mobility.getCandidacyHistoryForStudent(idUser);
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
    public Condidacy createCandidate(@RequestBody Condidacy candidate, @PathVariable String id_Student, @PathVariable int id_Opportunity) throws MessagingException, IOException {
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








}