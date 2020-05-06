package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "visite")
public class Visite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "prix", nullable = false)
	@Min(value = 1, message = "DOIT ETRE SUP A 1 !!!")
	private double prix = 20;

	@Column(name = "date_visite", nullable = false)
//	@Temporal(TemporalType.DATE)
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateVisite;

	@ManyToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medecin")
	private Medecin medecin;

	@ManyToOne
	@JoinColumn(name = "id_patient")
	private Patient patient;

	@ManyToMany(mappedBy = "visites")
	private List<Secretaire> secretaires = new ArrayList<>();

	public Visite() {

	}

	public Visite(Medecin medecin, Patient patient) {
		this.medecin = medecin;
		this.patient = patient;
		this.dateVisite = LocalDate.now();
	}

	public Visite(int id, Medecin medecin, Patient patient, LocalDate dateVisite) {
		this.id = id;
		this.medecin = medecin;
		this.patient = patient;
		this.dateVisite = dateVisite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDate getDateVisite() {
		return dateVisite;
	}

	public void setDateVisite(LocalDate dateVisite) {
		this.dateVisite = dateVisite;
	}

	public List<Secretaire> getSecretaires() {
		return secretaires;
	}

	public void setSecretaires(List<Secretaire> secretaires) {
		this.secretaires = secretaires;
	}

	@Override
	public String toString() {
		return "Visite [id=" + id + ", prix=" + prix + ", medecin=" + medecin + ", patient=" + patient + ", dateVisite="
				+ dateVisite + "]";
	}

}
