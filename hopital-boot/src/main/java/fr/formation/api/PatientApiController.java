package fr.formation.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOPatient;
import fr.formation.exception.PatientValidationException;
import fr.formation.model.Patient;
import fr.formation.projection.Views;

@RestController //Ce contr�leur n'enverra PAS de vue HTML
@CrossOrigin("*") //Si jamais on besoin d'interroger ce service depuis n'importe quel autre domaine
@RequestMapping("/api/patient")
public class PatientApiController {
	@Autowired
	private IDAOPatient daoPatient;
	
	
	//LISTE DES PATIENTS : @Get || http://localhost.../projet/api/patient
	@GetMapping
	@JsonView(Views.Common.class)
	public List<Patient> findAll() {
		return this.daoPatient.findAll();
	}
	
	//UN PATIENT : @Get || http://localhost.../projet/api/patient/5
	@GetMapping("/{secu}")
	@JsonView(Views.Patient.class)
	public Patient findById(@PathVariable int secu) {
		return this.daoPatient
				.findById(secu)
				.orElse(new Patient());
	}
	
	
	//AJOUTER UN PATIENT : @Post || http://localhost.../projet/api/patient
	@PostMapping
	@JsonView(Views.Patient.class)
	public Patient add(@Valid @RequestBody Patient patient, BindingResult result) {
		if (result.hasErrors()) {
			throw new PatientValidationException();
		}
		
		this.daoPatient.save(patient);
		return patient;
	}
	
	//MODIFIER UN PATIENT : @Put || http://localhost.../projet/api/patient/5
	@PutMapping("/{secu}")
	@JsonView(Views.Patient.class)
	public Patient update(@PathVariable int secu, @RequestBody Patient patient) {
		patient.setSecu(secu);
		return this.daoPatient.save(patient);
	}
	
	//SUPPRIMER UN PATIENT : @Delete || http://localhost.../projet/api/patient/5
	@DeleteMapping("/{secu}")
	public boolean delete(@PathVariable int secu) {
		try {
			this.daoPatient.deleteById(secu);
			return true;
		}
		
		catch (Exception e) {
			return false;
		}
	}
}