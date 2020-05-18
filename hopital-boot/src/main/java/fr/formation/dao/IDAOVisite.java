package fr.formation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import fr.formation.model.Visite;
import fr.formation.projection.VisiteProjection;


@CrossOrigin("*")
@RepositoryRestResource(excerptProjection = VisiteProjection.class)
public interface IDAOVisite extends JpaRepository<Visite, Integer> {
	@RestResource(path = "by-prix")
	public List<Visite> findByPrix(double prix);
	
	@RestResource(path = "by-prix-between")
	public List<Visite> findByPrixBetweenOrderByPrixDesc(double a, double b);
}