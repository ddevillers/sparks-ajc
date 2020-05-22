package fr.timebomb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.timebomb.enumerator.MatchState;
import fr.timebomb.enumerator.Role;
import fr.timebomb.projection.Views;

@Entity
@Table(name = "[match]")
public class Match {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATCH_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "MATCH_NAME", length = 20, nullable = false, unique = true)
	@JsonView(Views.Match.class)
	private String name;

	@Column(name = "MATCH_SIZE", nullable = false)
	@JsonView(Views.Match.class)
	private int size = 4;

	@Column(name = "MATCH_STATE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@JsonView(Views.Match.class)
	private MatchState state = MatchState.WAITING;

	@Column(name = "MATCH_WINNER")
	@Enumerated(EnumType.ORDINAL)
	@JsonView(Views.Match.class)
	private Role winner;

	@ManyToOne
	@JoinColumn(name = "MATCH_OWNER_ID")
	@JsonView(Views.Match.class)
	private User owner;

	@OneToOne
	@JoinColumn(name = "MATCH_CURRENT_ID")
	private User current;

	@OneToMany(mappedBy = "currentMatch")
	@JsonView(Views.Match.class)
	private List<User> players;

	@OneToMany(mappedBy = "match")
	private List<Card> deck;

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public User getCurrent() {
		return current;
	}

	public void setCurrent(User current) {
		this.current = current;
	}

	public List<User> getPlayers() {
		return players;
	}

	public void setPlayers(List<User> players) {
		this.players = players;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}

	public void addPlayer(User user) {
		if (this.players == null) {
			this.players = new ArrayList<>();
		}

		this.players.add(user);
		user.setCurrentMatch(this);
	}
}