package fr.formation.projection;

import org.springframework.data.rest.core.config.Projection;

import fr.formation.model.Compte;

@Projection(
	name = "compteProjection",
	types = { Compte.class }
)
public interface CompteProjection {
	public int getId();
	public String getLogin();
}