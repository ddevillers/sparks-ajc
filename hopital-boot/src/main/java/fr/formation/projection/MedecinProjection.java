package fr.formation.projection;

import org.springframework.data.rest.core.config.Projection;

import fr.formation.model.Medecin;

@Projection(
	name = "medecinProjection",
	types = { Medecin.class }
)
public interface MedecinProjection extends CompteProjection {

}