package api;

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

import dao.IDAOVisite;
import exception.VisiteValidationException;
import model.Visite;
import projection.Views;

@RestController //Ce contrôleur n'enverra PAS de vue HTML
@CrossOrigin("*") //Si jamais on besoin d'interroger ce service depuis n'importe quel autre domaine
@RequestMapping("/api/visite")
public class VisiteApiController {
	@Autowired
	private IDAOVisite daoVisite;
	
	
	//LISTE DES VISITES : @Get || http://localhost.../projet/api/visite
	@GetMapping
	@JsonView(Views.Common.class)
	public List<Visite> findAll() {
		return this.daoVisite.findAll();
	}
	
	//UNE VISTE : @Get || http://localhost.../projet/api/visite/5
	@GetMapping("/{id}")
	@JsonView(Views.Visite.class)
	public Visite findById(@PathVariable int id) {
		return this.daoVisite
				.findById(id)
				.orElse(new Visite());
	}
	
	
	//AJOUTER UNE VISITE : @Post || http://localhost.../projet/api/visite
	@PostMapping
	@JsonView(Views.Visite.class)
	public Visite add(@Valid @RequestBody Visite visite, BindingResult result) {
		if (result.hasErrors()) {
			throw new VisiteValidationException();
		}
		
		this.daoVisite.save(visite);
		return visite;
	}
	
	//MODIFIER UNE VISITE : @Put || http://localhost.../projet/api/visite/5
	@PutMapping("/{id}")
	@JsonView(Views.Visite.class)
	public Visite update(@PathVariable int id, @RequestBody Visite visite) {
		visite.setId(id);
		return this.daoVisite.save(visite);
	}
	
	//SUPPRIMER UNE VISITE : @Delete || http://localhost.../projet/api/visite/5
	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable int id) {
		try {
			this.daoVisite.deleteById(id);
			return true;
		}
		
		catch (Exception e) {
			return false;
		}
	}
}