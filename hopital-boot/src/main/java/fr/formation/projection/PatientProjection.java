package fr.formation.projection;

import org.springframework.data.rest.core.config.Projection;

import fr.formation.model.Patient;

@Projection(
	name = "patientProjection",
	types = { Patient.class }
)
public interface PatientProjection {
	public int getSecu();
	public String getNom();
	public String getPrenom();
}