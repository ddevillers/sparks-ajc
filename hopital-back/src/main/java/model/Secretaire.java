package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

@Entity
@DiscriminatorValue("Secretaire")
public class Secretaire extends Compte {

	@ManyToMany
	@JoinTable(
		name = "liste_attente",
		uniqueConstraints = @UniqueConstraint(columnNames = { "id_secretaire", "id_visite" }),
		joinColumns = @JoinColumn(name = "id_secretaire", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "id_visite", referencedColumnName = "id")
	)
	private List<Visite> visites = new ArrayList<>();

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public Secretaire() {

	}

	public Secretaire(int id, String login, String password, String typeCompte) {
		super(id, login, password, typeCompte);
	}

	// Affichage
	@Override
	public String toString() {
		return "Secretaire [id=" + id + ", login=" + login + ", password=" + password + ", typeCompte=" + typeCompte
				+ "]";
	}

}
