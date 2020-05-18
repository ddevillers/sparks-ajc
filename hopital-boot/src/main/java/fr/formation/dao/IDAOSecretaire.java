package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.formation.model.Secretaire;
import fr.formation.projection.CompteProjection;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = CompteProjection.class)
public interface IDAOSecretaire extends JpaRepository<Secretaire, Integer> {
	
}