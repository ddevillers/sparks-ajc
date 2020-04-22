package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Adresse implements Serializable{
	private static final long serialVersionUID = 1L;

	@Column(name = "numero", table = "patient")
	private int numero;
	
	@Column(name = "voie", length = 50, table = "patient")
	private String voie;
	
	@Column(name = "cp", length = 25, table = "patient")
	private String cp;

	@Column(name = "ville", length = 50, table = "patient")
	private String ville;
	
	public Adresse() {
		
	}
	
	
	public Adresse(int numero, String voie, String cp, String ville) {
		this.numero = numero;
		this.voie = voie;
		this.cp = cp;
		this.ville = ville;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public String getVoie() {
		return voie;
	}


	public void setVoie(String voie) {
		this.voie = voie;
	}


	public String getCp() {
		return cp;
	}


	public void setCp(String cp) {
		this.cp = cp;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", voie=" + voie + ", cp=" + cp + ", ville=" + ville + "]";
	}
	
	
}
