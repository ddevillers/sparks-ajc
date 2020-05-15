package fr.formation.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "secu")
	@JsonView(Views.Common.class)
	private int secu;

	@Column(name = "nom", length = 50, nullable = false)
	@JsonView({ Views.Patient.class, Views.Visite.class })
	private String nom;

	@Column(name = "prenom", length = 50, nullable = false)
	@JsonView(Views.Patient.class)
	private String prenom;

	@Column(name = "tel", length = 20)
	@JsonView(Views.Patient.class)
	private String tel;

	@Embedded
	private Adresse adresse;

	// RELATION INVERSE PAS OBLIGATOIRE SI ON DECIDE QU'ON EN A PAS BESOIN
	@OneToOne(mappedBy = "lastPatient")
	@JsonView(Views.Patient.class)
	private Medecin medecin;

	// RELATION INVERSE PAS OBLIGATOIRE SI ON DECIDE QU'ON EN A PAS BESOIN
	@OneToMany(mappedBy = "patient")
	private List<Visite> visites = new ArrayList<>();

	public Patient() {

	}

	public Patient(int secu, String nom, String prenom) {
		this.secu = secu;
		this.nom = nom;
		this.prenom = prenom;
	}

	public Patient(int secu, String nom, String prenom, String tel, Adresse adresse) {
		this.secu = secu;
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.adresse = adresse;
	}

	public int getSecu() {
		return secu;
	}

	public void setSecu(int secu) {
		this.secu = secu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@Override
	public String toString() {
		return "Patient [secu=" + secu + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + ", adresse=" + adresse
				+ "]";
	}
}
