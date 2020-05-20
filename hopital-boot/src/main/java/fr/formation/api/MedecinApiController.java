package fr.formation.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.dao.IDAOCompte;
import fr.formation.model.Medecin;
import fr.formation.projection.Views;

@RestController //Ce contrôleur n'enverra PAS de vue HTML
@CrossOrigin("*") //Si jamais on besoin d'interroger ce service depuis n'importe quel autre domaine
@RequestMapping("/api/medecin")
public class MedecinApiController {
	@Autowired
	private IDAOCompte daoCompte;
	
	
	//LISTE DES PATIENTS : @Get || http://localhost.../projet/api/medecin
	@GetMapping
	@JsonView(Views.Compte.class)
	public List<Medecin> findAll() {
		return this.daoCompte.findAllMedecins();
	}
}