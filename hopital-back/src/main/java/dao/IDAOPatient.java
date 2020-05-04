package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Patient;

public interface IDAOPatient extends JpaRepository<Patient, Integer> {

}