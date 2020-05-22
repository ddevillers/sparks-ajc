package fr.timebomb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.timebomb.enumerator.MatchState;
import fr.timebomb.model.Match;
import fr.timebomb.model.User;

public interface IMatchDao extends JpaRepository<Match, Integer> {
	public List<Match> findAllByStateIn(List<MatchState> states);
	public Match findByPlayers(User player);
}