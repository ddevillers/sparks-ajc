package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.formation.model.Medecin;
import fr.formation.projection.MedecinProjection;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = MedecinProjection.class)
public interface IDAOMedecin extends JpaRepository<Medecin, Integer> {
	
}