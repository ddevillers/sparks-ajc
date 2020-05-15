package fr.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("Medecin")
public class Medecin extends Compte {
	@Column(name = "salle", nullable = false)
	private int salle;
	
//	@Transient
	@OneToOne
	@JoinColumn(name = "id_patient")
	private Patient lastPatient;
	
	@OneToMany(mappedBy = "medecin")
	private List<Visite> visites = new ArrayList<>();

	
	public Medecin() {

	}

	public Medecin(int id, String login, String password, String typeCompte, int salle) {
		super(id, login, password, typeCompte);
		this.salle = salle;
	}

	public int getSalle() {
		return salle;
	}

	public void setSalle(int salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "Medecin [salle=" + salle + ", id=" + id + ", login=" + login + ", password=" + password
				+ ", typeCompte=" + typeCompte + "]";
	}

}
