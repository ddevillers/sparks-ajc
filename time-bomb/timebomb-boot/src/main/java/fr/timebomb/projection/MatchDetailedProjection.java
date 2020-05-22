package fr.timebomb.projection;

import java.util.ArrayList;
import java.util.List;

import fr.timebomb.enumerator.MatchState;
import fr.timebomb.enumerator.Role;
import fr.timebomb.model.Card;
import fr.timebomb.model.Match;
import fr.timebomb.model.User;

public class MatchDetailedProjection {
	private int id;
	private String name;
	private int size;
	private MatchState state;
	private Role winner;
	private PlayerProjection owner;
	private PlayerProjection current;
	
	private List<PlayerProjection> players = new ArrayList<>();
	private List<CardProjection> deck = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public MatchState getState() {
		return state;
	}

	public void setState(MatchState state) {
		this.state = state;
	}

	public Role getWinner() {
		return winner;
	}

	public void setWinner(Role winner) {
		this.winner = winner;
	}

	public PlayerProjection getOwner() {
		return owner;
	}

	public void setOwner(PlayerProjection owner) {
		this.owner = owner;
	}

	public PlayerProjection getCurrent() {
		return current;
	}

	public void setCurrent(PlayerProjection current) {
		this.current = current;
	}

	public List<PlayerProjection> getPlayers() {
		return players;
	}

	public void setPlayers(List<PlayerProjection> players) {
		this.players = players;
	}

	public List<CardProjection> getDeck() {
		return deck;
	}

	public void setDeck(List<CardProjection> deck) {
		this.deck = deck;
	}

	public MatchDetailedProjection(Match match, int currentUserId) {
		if (match != null) {
			this.setId(match.getId());
			this.setName(match.getName());
			this.setSize(match.getSize());
			this.setState(match.getState());
			this.setWinner(match.getWinner());
			this.setOwner(new PlayerProjection(match.getOwner()));
			this.setCurrent(new PlayerProjection(match.getCurrent()));

			for (User player : match.getPlayers()) {
				this.getPlayers().add(new PlayerProjection(match, player, currentUserId));
			}

			for (Card card : match.getDeck()) {
				this.getDeck().add(new CardProjection(card, currentUserId));
			}
		}
	}
}