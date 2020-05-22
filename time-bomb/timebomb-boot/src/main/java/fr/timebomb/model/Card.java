package fr.timebomb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.timebomb.enumerator.CardType;
import fr.timebomb.projection.Views;

@Entity
@Table(name = "card")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CARD_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "CARD_TYPE", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	@JsonView(Views.CardRevealed.class)
	private CardType type = CardType.BAIT;

	@Column(name = "CARD_REVEALED")
	@JsonView(Views.CardRevealed.class)
	private boolean revealed;

	@ManyToOne
	@JoinColumn(name = "CARD_MATCH_ID", nullable = false)
	private Match match;

	@ManyToOne
	@JoinColumn(name = "CARD_OWNER_ID")
	@JsonView(Views.CardRevealed.class)
	private User owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
}