package fr.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.dao.IDAOCompte;
import fr.formation.dao.IDAOPatient;
import fr.formation.dao.IDAOVisite;
import fr.formation.model.Compte;
import fr.formation.model.Patient;
import fr.formation.model.Visite;

@Controller
public class VisiteController {
	@Autowired
	private IDAOVisite daoVisite;
	
	@Autowired
	private IDAOCompte daoCompte;
	
	@Autowired
	private IDAOPatient daoPatient;
	
	@GetMapping({ "/", "/visite" })
	public String findAll(Model model/*, Authentication auth*/) {
		return "visites";
	}
	
	
	@PostMapping("/visite")
	//@PreAuthorize("hasRole('ADMIN')")
	public String add(@Valid @ModelAttribute Visite visite, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "visites";
		}
		
		this.daoVisite.save(visite);
		
		return "redirect:/visite";
	}
	
	@GetMapping("/visite/{id}/supprimer")
	//@PreAuthorize("hasRole('ADMIN')")
	public String deleteById(@PathVariable int id) {
		try {
			this.daoVisite.deleteById(id);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/visite";
	}

	
	@ModelAttribute("visites")
	public List<Visite> visites() {
		return this.daoVisite.findAll();
	}
	
	@ModelAttribute("medecins")
	public List<Compte> medecins() {
		return this.daoCompte.findByTypeCompte("Medecin");
	}
	
	@ModelAttribute("patients")
	public List<Patient> patients() {
		return this.daoPatient.findAll();
	}
}