package fr.timebomb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.timebomb.model.Card;

public interface ICardDao extends JpaRepository<Card, Integer> {
	
}