package fr.timebomb.model;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.timebomb.enumerator.Role;
import fr.timebomb.projection.Views;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID")
	@JsonView(Views.Common.class)
	private int id;

	@Column(name = "USR_NAME", length = 20, nullable = false)
	@JsonView({ Views.User.class, Views.Match.class })
	private String name;

	@Column(name = "USR_USERNAME", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "USR_PASSWORD", length = 250, nullable = false)
	private String password;

	@Column(name = "USR_MATCH_ROLE")
	@Enumerated(EnumType.ORDINAL)
	private Role currentRole;

	@ManyToOne
	@JoinColumn(name = "USR_MATCH_ID")
	private Match currentMatch;

	@OneToMany(mappedBy = "owner")
	private List<Card> hand;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	public Match getCurrentMatch() {
		return currentMatch;
	}

	public void setCurrentMatch(Match currentMatch) {
		this.currentMatch = currentMatch;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
}