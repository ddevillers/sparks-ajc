package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Compte;

public interface IDAOCompte extends JpaRepository<Compte, Integer> {
	@Query("select c from Compte c where c.login = ?1 and c.password = ?2")
	public Compte checkConnect(String login, String password);
}