package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Condidacy;
import tn.esprit.spring.entities.Opportunity;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.CondidacyRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Pi_Mobility")
public class ExamenRestController {
	@Autowired
	Pi_Mobility pi_mobility;
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
	public Opportunity createOpportunity(@RequestBody Opportunity opportunity, @PathVariable  String id_Partner) {

		return pi_mobility.createOpportunity(opportunity,id_Partner);
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
	public Condidacy createCandidate(@RequestBody Condidacy candidate,@PathVariable String id_Student,@PathVariable int id_Opportunity) {
		return pi_mobility.createCandidateAndAssignEtudiant(candidate,id_Student,id_Opportunity);
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
	return  pi_mobility.CalculScore(opportuniteId);

	}
	@ResponseBody
	@GetMapping("/Trie/{opportuniteId}")
	public List<Condidacy> Trie(@PathVariable Integer opportuniteId) {
		return  pi_mobility.trierEtudiantsParScore(opportuniteId);

	}


	@GetMapping("candidatures/top/{opportunityId}")
	public List<Condidacy> getTopNCandidatures(@PathVariable Integer opportunityId) {


		return pi_mobility.getTopNCandidatures(opportunityId);
	}
	@Autowired
	CondidacyRepository condidacyRepository;
	@PostMapping("Condidacy/sendEmailToTopNCandidates/{id_Opportunity}")
	public ResponseEntity<String>SENDMail(@PathVariable int id_Opportunity) {
		 int n =0;
			List<Condidacy> condidacy=condidacyRepository.findAll();
			for(Condidacy condidacy1: condidacy)
			{
				if(condidacy1.getOpportunity() .getId_Opportunity()==id_Opportunity)
				{
					n++;
				}
			}
		try {
			pi_mobility.sendSelectedCandidatesEmails( id_Opportunity);
			return ResponseEntity.ok("Emails sent successfully to the top " + n + " candidates.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending emails: " + e.getMessage());
		}
	}
	@PostMapping("Condidacy/sendSelectedCandidatesEmailsTest/{id_Opportunity}")
	public ResponseEntity<String>sendSelectedCandidatesEmailsTest(@PathVariable int id_Opportunity) {
		int n =0;
		List<Condidacy> condidacy=condidacyRepository.findAll();
		for(Condidacy condidacy1: condidacy)
		{
			if(condidacy1.getOpportunity() .getId_Opportunity()==id_Opportunity)
			{
				n++;
			}
		}
		try {
			pi_mobility.sendSelectedCandidatesEmailsTest( id_Opportunity);
			return ResponseEntity.ok("Emails sent successfully to the top " + n + " candidates.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending emails: " + e.getMessage());
		}
	}

}


