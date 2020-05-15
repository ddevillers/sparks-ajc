package fr.formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.dao.IDAOPatient;
import fr.formation.model.Patient;

@Controller
public class PatientController {
	@Autowired
	private IDAOPatient daoPatient;
	
	@GetMapping("/patient")
	public String findAll(Model model) {
		model.addAttribute("patients", this.daoPatient.findAll());
		
		return "patients";
	}
	
	@PostMapping("/patient")
	public String add(Patient patient) {
		this.daoPatient.save(patient);
		
		return "redirect:/patient";
	}
	
	@GetMapping("/patient/{secu}")
	public String findById(@PathVariable int secu, Model model) {
		model.addAttribute("patient", this.daoPatient.findById(secu).orElse(new Patient()));
		
		return "form-patient";
	}
	
	@GetMapping("/patient/{secu}/supprimer")
	public String deleteById(@PathVariable int secu) {
		try {
			this.daoPatient.deleteById(secu);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/patient";
	}
}