package fr.formation.model;



import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import fr.formation.projection.Views;

@Entity
@Table(name = "compte")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "typecompte")
public class Compte {
	@Id //OBLIGATOIRE
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AUTOINCREMENT
	@Column(name = "id")
	@JsonView(Views.Common.class)
	protected int id;

	@Column(name = "login", length = 25, nullable = false)
	@JsonView({ Views.Compte.class, Views.Visite.class })
	protected String login;

	@Column(name = "password", length = 50, nullable = false)
	protected String password;
	
	@Column(name="typecompte", length = 10, insertable = false, updatable = false)
	protected String typeCompte;

	public Compte() {

	}

	public Compte(int id, String login, String password, String typeCompte) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.typeCompte = typeCompte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	@Override
	public String toString() {
		return this.login;
	}
}