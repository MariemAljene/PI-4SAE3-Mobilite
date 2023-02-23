package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Apprenant;
import tn.esprit.spring.entities.Question;
import tn.esprit.spring.entities.Reponse;
import tn.esprit.spring.entities.Type;
import tn.esprit.spring.interfaces.IExamenService;
import tn.esprit.spring.repositories.ReponseRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examen")
public class ExamenRestController {
	@Autowired
	IExamenService examenService;


	@PostMapping("/add-apprenant")
	public void addApprenant(@RequestBody Apprenant apprenant) {
		examenService.ajouterApprenant(apprenant);
	}

	//tebaa les question el koll

	@PostMapping("/Question/AddQuestion")
	public void AddQuestion(@RequestBody Question question) {
		examenService.AddQuestion(question);
	}

	@GetMapping("/Question/GetAll")
	public List<Question> findAllQuestions() {
		return examenService.findAllQuestions();
	}

	@PutMapping("/Question/UpdateQuestion/{id}")
	public ResponseEntity<Question>UpdateQuestion (@PathVariable Integer id,@RequestBody Question question) {
		Optional<Question> questionOptional = examenService.findQuestionById(id);
		if(questionOptional.isPresent()){
			question.setIdQuestion(id);
			return  ResponseEntity.ok(examenService.UpdateQuestion(question));

		}else {
			return ResponseEntity.notFound().build();
		}
	}


	@DeleteMapping("/Question/DeleteQuestion/{id}")
	public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id){
		Optional<Question> questionOptional = examenService.findQuestionById(id);
		if(questionOptional.isPresent()){
			examenService.deleteQuestionById(id);
			return ResponseEntity.noContent().build();

		}else {
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("/Question/FindQuestionByType/{type}")
	public List<Question> findQuestionByType(@PathVariable Type type){
		return examenService.findQuestionByType(type);
	}


	//tebaa les reponse el koul


	@PutMapping("/Reponse/AddAndAssignReponseToQuestion/{IdQuestion}")
	void AddReponseAndAssignTOQuestion(@RequestBody Reponse rep,@PathVariable Integer IdQuestion)
	{
		examenService.AddReponseAndAssignTOQuestion(rep,IdQuestion);


	}

	@GetMapping("/Reponse/FindAll")
	public List<Reponse> findAllReponse(){
		return examenService.findAllReponse();
	}


	@PutMapping("/Reponse/UpdateReponse/{id}")
	public ResponseEntity<Reponse>UpdadeReponse(@PathVariable Integer id,@RequestBody Reponse reponse){
		Optional<Reponse> reponseOptional=examenService.findReponseById(id);

		if(reponseOptional.isPresent()){
			reponse.setIdReponse(id);
			return ResponseEntity.ok(examenService.UpdateReponse(reponse));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/Reponse/deleteReponse/[id}")
	public ResponseEntity<Void> deleteReponse(@PathVariable Integer id){
		Optional<Reponse> reponseOptional=examenService.findReponseById(id);
		if(reponseOptional.isPresent()){
			examenService.deleteReponseById(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
























}


