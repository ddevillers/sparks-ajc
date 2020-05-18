package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.formation.model.Compte;
import fr.formation.model.Medecin;
import fr.formation.projection.CompteProjection;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = CompteProjection.class)
public interface IDAOCompte extends JpaRepository<Compte, Integer> {
	public Compte findByLogin(String login);
	
	@Query("select c from Compte c where c.login = ?1 and c.password = ?2")
	public Compte checkConnect(String login, String password);
	
	public List<Compte> findByTypeCompte(String typeCompte);
	
	@Query("select m from Medecin m")
	public List<Medecin> findAllMedecins();
}