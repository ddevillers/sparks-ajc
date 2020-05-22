package fr.timebomb.projection;

import fr.timebomb.enumerator.MatchState;
import fr.timebomb.enumerator.Role;
import fr.timebomb.model.Match;
import fr.timebomb.model.User;

public class PlayerProjection {
	private int id;
	private String name;
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public PlayerProjection(User player) {
		this(null, player, 0);
	}

	public PlayerProjection(Match match, User player, int currentUserId) {
		if (player != null) {
			this.setId(player.getId());
			this.setName(player.getName());

			if (
				(match != null && match.getState() == MatchState.TERMINATED)
				||
				(currentUserId == player.getId())
			) {
				this.setRole(player.getCurrentRole());
			}
		}
	}
}