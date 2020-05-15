package fr.formation.projection;

import java.time.LocalDate;

import org.springframework.data.rest.core.config.Projection;

import fr.formation.model.Visite;

@Projection(
	name = "visiteProjection",
	types = { Visite.class }
)
public interface VisiteProjection {
	public int getId();
	public LocalDate getDateVisite();
	public double getPrix();
}