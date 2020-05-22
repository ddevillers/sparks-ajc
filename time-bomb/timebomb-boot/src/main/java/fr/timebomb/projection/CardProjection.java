package fr.timebomb.projection;

import fr.timebomb.enumerator.CardType;
import fr.timebomb.enumerator.MatchState;
import fr.timebomb.model.Card;

public class CardProjection {
	private int id;
	private boolean revealed;
	private CardType type;
	private int ownerId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public CardType getType() {
		return type;
	}

	public void setType(CardType type) {
		this.type = type;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public CardProjection(Card card, int currentUserId) {
		this.setId(card.getId());
		this.setRevealed(card.isRevealed());

		if (card.getOwner() != null) {
			this.setOwnerId(card.getOwner().getId());

			if (card.isRevealed() || card.getOwner().getId() == currentUserId || card.getMatch().getState() == MatchState.TERMINATED) {
				this.setType(card.getType());
			}
		}
	}
}