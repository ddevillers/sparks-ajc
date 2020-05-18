package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.formation.model.Patient;
import fr.formation.projection.PatientProjection;

@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = PatientProjection.class)
public interface IDAOPatient extends JpaRepository<Patient, Integer> {

}