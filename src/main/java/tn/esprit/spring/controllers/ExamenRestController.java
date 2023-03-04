package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.interfaces.Pi_Mobility;
import tn.esprit.spring.repositories.NotificationRepository;
import tn.esprit.spring.repositories.ReponseRepository;
import tn.esprit.spring.services.Pi_MobilityImplementation;

import javax.transaction.Transactional;
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
	@Autowired
	private NotificationRepository notificationRepository;



	@PostMapping("/add-apprenant")
	public void addApprenant(@RequestBody Apprenant apprenant) {
		Pi_Mobility.ajouterApprenant(apprenant);
	}

	//tebaa les question el koll

	@PostMapping("/Question/AddQuestion")
	public void AddQuestion(@RequestBody Question question) {

		Pi_Mobility.AddQuestion(question);



	}

	@GetMapping("/Question/GetAll")
	public List<Question> findAllQuestions() {
		return Pi_Mobility.findAllQuestions();
	}

	@Transactional
	@PutMapping("/Question/UpdateQuestion/{id}")
	public ResponseEntity<Question>UpdateQuestion (@PathVariable Integer id,@RequestBody Question question) {

		Optional<Question> questionOptional = Pi_Mobility.findQuestionById(id);
		if(questionOptional.isPresent()){
			question.setIdQuestion(id);
			return  ResponseEntity.ok(Pi_Mobility.UpdateQuestion(question));

		}else {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("/Question/DeleteQuestion/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id){
		Optional<Question> questionOptional = Pi_Mobility.findQuestionById(id);
		if(questionOptional.isPresent()){
			Pi_Mobility.deleteQuestionById(id);
			return ResponseEntity.noContent().build();

		}else {
			return ResponseEntity.notFound().build();
		}
	}





	//tebaa les reponse el koul


	@PostMapping("/Reponse/AddAndAssignReponseToQuestion/{IdQuestion}")
	void AddReponseAndAssignTOQuestion(@RequestBody Reponse rep,@PathVariable Integer IdQuestion)
	{
		Pi_Mobility.AddReponseAndAssignTOQuestion(rep,IdQuestion);


	}

	@GetMapping("/Reponse/FindAll")
	public List<Reponse> findAllReponse(){
		return Pi_Mobility.findAllReponse();
	}


	@PutMapping("/Reponse/UpdateReponse/{id}")
	public ResponseEntity<Reponse>UpdadeReponse(@PathVariable Integer id,@RequestBody Reponse reponse){
		Optional<Reponse> reponseOptional=Pi_Mobility.findReponseById(id);

		if(reponseOptional.isPresent()){
			reponse.setIdReponse(id);
			return ResponseEntity.ok(Pi_Mobility.UpdateReponse(reponse));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/Reponse/deleteReponse/{id}")
	public ResponseEntity<Void> deleteReponse(@PathVariable Integer id){
		Optional<Reponse> reponseOptional=Pi_Mobility.findReponseById(id);
		if(reponseOptional.isPresent()){
			Pi_Mobility.deleteReponseById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/Reponse/getReponse_highest-rating")
	public ResponseEntity<Reponse> getReponseWithHighestRating() {
		List<Reponse> reponses = reponseRepository.findReponseWithHighestRating();
		if (reponses.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reponses.get(0));
	}

	@GetMapping("/Reponse/GetRponse_highestAverage")
	public ResponseEntity<List<Reponse>> getReponsesByHighestAverageRAting(){
		List<Reponse> reponses = Pi_Mobility.getReponsesByHighestAverageRAting();
		if (reponses.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reponses);
	}



	////// tebaa el Rating

	@PostMapping("/Rating/CreateRate/{idReponse}")
	public Rating CreateRating(@RequestBody Rating rating, @PathVariable Integer idReponse){
		return Pi_Mobility.CreateRating(rating,idReponse);
	}
	@GetMapping("/Rating/GetRating/{idRating}")
	public Optional<Rating> findRatingById(@PathVariable Long idRating){
		return Pi_Mobility.findRatingById(idRating);

	}

	@PutMapping("/Rating/UpdateRating/{idRating}")
	public ResponseEntity<Rating> UpdateRating(@RequestBody Rating rating,@PathVariable Long idRating){
		Optional<Rating> ratingOptional=Pi_Mobility.findRatingById(idRating);
		if (ratingOptional.isPresent()){
			rating.setIdRate(idRating);
			return ResponseEntity.ok(Pi_Mobility.UpdateRating(rating));
		}else {
			return ResponseEntity.notFound().build();
		}

	}


	@GetMapping("/Rating/getRateForReponse/{idReponse}")
	public List<Rating> getRatingsForReponse(@PathVariable Integer idReponse){
		Reponse reponse = reponseRepository.findById(idReponse).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
		return reponse.getRatings();
	}


	@GetMapping("/Rating/getAllRatingByValue/{value}")
	public List<Rating> GetAllRAtingsByValue(@PathVariable int value){
		return Pi_Mobility.GetAllRAtingsByValue(value);
	}








	///////notifications






	////////////// Reclamation


	@PostMapping("/Reclamation/AddReclamation")
	public void AddReclamation(@RequestBody Reclamation reclamation)
	{
		reclamation.setComment(BadWordFilter.getCensoredText(reclamation.getComment()));
		Pi_Mobility.AddReclamation(reclamation);
	}



	@PutMapping("/Reclamation/UpdateReclamation/{idReclam}")
	public Reclamation UpdateREclamation(@RequestBody Reclamation reclamation,@PathVariable Long idReclam){
		return Pi_Mobility.UpdateREclamation(reclamation,idReclam);

	}

	@GetMapping("/Reclamation/GetReclama/{idReclam}")
	public Optional<Reclamation> GetReclamationById(@PathVariable Long idReclam){
		return Pi_Mobility.GetReclamationById(idReclam);
	}




	@PostMapping("/User/AddUser")
	public void AddUser(@RequestBody User user ){
		Pi_Mobility.AddUser(user);
	}

































}


