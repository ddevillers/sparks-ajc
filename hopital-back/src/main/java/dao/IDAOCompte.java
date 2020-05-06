package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Compte;
import model.Medecin;

public interface IDAOCompte extends JpaRepository<Compte, Integer> {
	@Query("select c from Compte c where c.login = ?1 and c.password = ?2")
	public Compte checkConnect(String login, String password);
	
	public List<Compte> findByTypeCompte(String typeCompte);
	
	@Query("select m from Medecin m")
	public List<Medecin> findAllMedecins();
}