package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Patient;

public interface IDAOPatient extends JpaRepository<Patient, Integer> {

}