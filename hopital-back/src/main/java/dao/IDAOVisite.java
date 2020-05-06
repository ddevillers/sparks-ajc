package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Visite;

public interface IDAOVisite extends JpaRepository<Visite, Integer> {

}