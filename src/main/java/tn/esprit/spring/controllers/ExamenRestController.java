package tn.esprit.spring.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.ReponseRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@ComponentScan(basePackages = "tn.esprit.spring.controllers")
@RestController
@RequestMapping("/examen")
public class ExamenRestController {
	@Autowired
	Pi_Mobility Pi_Mobility;
	@Autowired
	private ReponseRepository reponseRepository;




	@PostMapping("/add-apprenant")
	public void addApprenant(@RequestBody Apprenant apprenant) {
		Pi_Mobility.ajouterApprenant(apprenant);
	}

	//tebaa les question el koll

	@PostMapping("/Question/AddQuestion")
	public void AddQuestion(@RequestBody QuestRep questRep) {

		Pi_Mobility.AddQuestion(questRep);

	}

	@GetMapping("/Question/GetAll")
	public List < QuestRep > findAllQuestions() {
		return Pi_Mobility.findAllQuestions();
	}

	@Transactional
	@PutMapping("/Question/UpdateQuestion/{id}")
	public ResponseEntity < QuestRep > UpdateQuestion(@PathVariable Integer id, @RequestBody QuestRep questRep) {

		Optional < QuestRep > questionOptional = Pi_Mobility.findQuestionById(id);
		if (questionOptional.isPresent()) {
			questRep.setIdQuestion(id);
			return ResponseEntity.ok(Pi_Mobility.UpdateQuestion(questRep));

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/Question/DeleteQuestion/{id}")
	public ResponseEntity < Void > deleteQuestion(@PathVariable Integer id) {
		Optional < QuestRep > questionOptional = Pi_Mobility.findQuestionById(id);
		if (questionOptional.isPresent()) {
			Pi_Mobility.deleteQuestionById(id);
			return ResponseEntity.noContent().build();

		} else {
			return ResponseEntity.notFound().build();
		}
	}

  /*	@GetMapping("/Question/getQuestionByType")
  	public List<QuestRep> getQuestionByType(@RequestParam Type type){
  		return Pi_Mobility.getQuestionByType(type);
  	}
  */

	//tebaa les reponse el koul

	@PostMapping("/Reponse/AddAndAssignReponseToQuestion/{IdQuestion}")
	void AddReponseAndAssignTOQuestion(@RequestBody Reponse rep, @PathVariable Integer IdQuestion) {
		Pi_Mobility.AddReponseAndAssignTOQuestion(rep, IdQuestion);

	}

	@GetMapping("/Reponse/FindAll")
	public List < Reponse > findAllReponse() {
		return Pi_Mobility.findAllReponse();
	}

	@PutMapping("/Reponse/UpdateReponse/{id}")
	public ResponseEntity < Reponse > UpdadeReponse(@PathVariable Integer id, @RequestBody Reponse reponse) {
		Optional < Reponse > reponseOptional = Pi_Mobility.findReponseById(id);

		if (reponseOptional.isPresent()) {
			reponse.setIdReponse(id);
			return ResponseEntity.ok(Pi_Mobility.UpdateReponse(reponse));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/Reponse/deleteReponse/{id}")
	public ResponseEntity < Void > deleteReponse(@PathVariable Integer id) {
		Optional < Reponse > reponseOptional = Pi_Mobility.findReponseById(id);
		if (reponseOptional.isPresent()) {
			Pi_Mobility.deleteReponseById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/Reponse/getReponse_highest-rating")
	public ResponseEntity < Reponse > getReponseWithHighestRating() {
		List < Reponse > reponses = reponseRepository.findReponseWithHighestRating();
		if (reponses.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reponses.get(0));
	}

	@GetMapping("/Reponse/GetRponse_highestAverage")
	public ResponseEntity < List < Reponse >> getReponsesByHighestAverageRAting() {
		List < Reponse > reponses = Pi_Mobility.getReponsesByHighestAverageRAting();
		if (reponses.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reponses);
	}

	////// tebaa el Rating

	@PostMapping("/Rating/CreateRate/{idReponse}")
	public Rating CreateRating(@RequestBody Rating rating, @PathVariable Integer idReponse) {
		return Pi_Mobility.CreateRating(rating, idReponse);
	}
	@GetMapping("/Rating/GetRating/{idRating}")
	public Optional < Rating > findRatingById(@PathVariable Long idRating) {
		return Pi_Mobility.findRatingById(idRating);

	}

	@PutMapping("/Rating/UpdateRating/{idRating}")
	public ResponseEntity < Rating > UpdateRating(@RequestBody Rating rating, @PathVariable Long idRating) {
		Optional < Rating > ratingOptional = Pi_Mobility.findRatingById(idRating);
		if (ratingOptional.isPresent()) {
			rating.setIdRate(idRating);
			return ResponseEntity.ok(Pi_Mobility.UpdateRating(rating));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/Rating/getRateForReponse/{idReponse}")
	public List < Rating > getRatingsForReponse(@PathVariable Integer idReponse) {
		Reponse reponse = reponseRepository.findById(idReponse).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return reponse.getRatings();
	}

	@GetMapping("/Rating/getAllRatingByValue/{value}")
	public List < Rating > GetAllRAtingsByValue(@PathVariable int value) {
		return Pi_Mobility.GetAllRAtingsByValue(value);
	}

	@PostMapping("/Reclamation/AddReclamation/{userName}")
	public ResponseEntity < Reclamation > AddReclamation(@RequestBody Reclamation reclamation, @PathVariable String userName) {
		try {
			reclamation.setComment(BadWordFilter.getCensoredText(reclamation.getComment()));
			Reclamation AddReclamation = Pi_Mobility.AddReclamation(reclamation, userName);
			return new ResponseEntity < > (AddReclamation, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add reclamation", e);
		}
	}

	@GetMapping("/Reclamation/getRecByUserName/{userName}")
	public ResponseEntity < List < Reclamation >> getReclamationByUserName(@PathVariable String userName) {
		List < Reclamation > reclamations = Pi_Mobility.getReclamationByUserName(userName);
		return new ResponseEntity < > (reclamations, HttpStatus.OK);
	}

	@GetMapping("/Reclamation/GenderReclamationsByGender/{gender}")
	public ResponseEntity < List < Reclamation >> getReclamationsByGender(@PathVariable("gender") String gender) {
		try {
			List < Reclamation > reclamations = Pi_Mobility.getReclamatinByGender(gender);
			return new ResponseEntity < > (reclamations, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "unable to get reclamations by gender", e);
		}
	}

	@GetMapping("/Reclamation/getAllReclamation")
	public List < Reclamation > findAllRec(Reclamation reclamation) {
		return Pi_Mobility.findAllRec(reclamation);
	}

	@GetMapping("/Reclamation/GetReclamationById/{idRec}")
	public Optional < Reclamation > getReclamationById(@PathVariable Long idRec) {
		return Pi_Mobility.getReclamationById(idRec);
	}

	@PutMapping("/Reclamation/changerStatuReclamationTraitement/{admin}/{id}/{statu}")
	public Reclamation changerStatuReclamationTraitement(@PathVariable Long id, @PathVariable String admin, @PathVariable String statu) {
		return Pi_Mobility.changerStatuReclamationTraitement(admin, id, statu);
	}

	@GetMapping("/Reclamation/getReclamationVerifie")
	public List < Reclamation > getReclamationsVerifie() {
		return Pi_Mobility.getReclamationByStatus(Statu.VERIFIE.toString());
	}
	@GetMapping("/reclamations/encours")
	public List < Reclamation > getReclamationsEnCours() {
		return Pi_Mobility.getReclamationByStatus(Statu.EN_COURS.toString());
	}
	@GetMapping("/reclamations/nontraitees")
	public List < Reclamation > getReclamationsNonTraitee() {
		return Pi_Mobility.getReclamationByStatus(Statu.NON_TRAITE.toString());
	}

	@GetMapping("/reclamations/rejetees")
	public List < Reclamation > getReclamationsRejetee() {
		return Pi_Mobility.getReclamationByStatus(Statu.REJETEE.toString());
	}

	@GetMapping("/Reclamation/countByUserGender")
	public ResponseEntity < List < Object[] >> getReclamationCountByGender() {
		try {
			List < Object[] > result = Pi_Mobility.getReclamationCountByGender();
			return new ResponseEntity < > (result, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get reclamation count by user gender ", e);
		}
	}

	@GetMapping("/Reclamation/countByObjet")
	public ResponseEntity < List < Object[] >> getReclamationCountByObjet() {
		try {
			List < Object[] > result = Pi_Mobility.getReclamationCountByObjet();
			return new ResponseEntity < > (result, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get reclamation count by objet", e);
		}
	}

	@PostMapping("/Foyer/foyerAdd")
	public Foyer foyerAdd (Foyer foyer){
		return Pi_Mobility.foyerAdd(foyer);
	}

	@PostMapping("/Chambre/chambreAdd/{id}")
	public Chambre chambreAdd(@RequestBody Chambre chambre,@PathVariable Long id){
		return Pi_Mobility.chambreAdd(chambre,id);
	}
	@PostMapping("/User/AddUser")
	public void AddUser(@RequestBody User user) {
		Pi_Mobility.AddUser(user);
	}
	@GetMapping("/Foyer/getFoyer/{id}")
	public Optional<Foyer> getFoyer(@PathVariable Long id){
		return Pi_Mobility.getFoyer(id);
	}

	@GetMapping("/Chambre/getChambre/{id}")
	public Optional<Chambre> getchambre(@PathVariable Long id){
		return Pi_Mobility.getchambre(id);
	}
	@PostMapping("/Chambre/affecterEtudiant/{username}")
	public Chambre affecterChambre (@PathVariable String username)
	{
		return Pi_Mobility.affectationEtudiantFoyerChambreSurDemande(username);
	}
	@DeleteMapping("/Foyer/deletefoyer/{idFoyer}")
	public ResponseEntity< Void >  deleteFoyerById(Long idfoyer){
		Optional < Foyer > optionalFoyer = Pi_Mobility.getFoyer(idfoyer);
		if (optionalFoyer.isPresent()) {
			Pi_Mobility.deleteFoyerById(idfoyer);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}



	/*	@GetMapping("/Reclamation/getRecByUserName/{userName}")
	public ResponseEntity < List < Reclamation >> getReclamationByUserName(@PathVariable String userName) {
		List < Reclamation > reclamations = Pi_Mobility.getReclamationByUserName(userName);
		return new ResponseEntity < > (reclamations, HttpStatus.OK);
	}*/

	@GetMapping("/Chambre/getChambreByUser/{userName}")
	public Chambre getChambreByUser(@PathVariable String userName){
		Chambre chambre=Pi_Mobility.getChambreByUser(userName);
		return chambre;
	}

	@PutMapping("/Chambre/removeEtudiantFromChambre/{chambreId}/{username}")
	public Chambre removeEtudiantFromChambre(@PathVariable Long chambreId,@PathVariable String username){
		return Pi_Mobility.removeEtudiantFromChambre(chambreId,username);
	}

	@PutMapping("/foyer/deleteChambreFromFoyer/{foyerId}/{chambreId}")
	public void removeChambreFromFoyer(@PathVariable Long foyerId,@PathVariable Long chambreId){
		 Pi_Mobility.removeChambreFromFoyer(foyerId,chambreId);
	}












}
