package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Modele de données d'une adresse associée à une personne (et une seule)
 * @author gabri
 *
 */
@Entity
@Table(name="adresses")
public class Adresse implements Serializable  {

	
	/*_____________________props_________________________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//auto-increment
	@Column(name="id_adresse")
	private Long idAdresse;
	
	@Column(name="numero")
	private int numero;
	
	@Column(name="rue")
	private String rue;
	
	@Column(name="code_postal")
	private String codePostal;
	
	@Column(name="ville")
	private String ville;

	@ManyToOne
	@JoinColumn(name = "personne_id", referencedColumnName="id_personne")
	private Personnes proprio;
	
	
	/*_____________________ctors_________________________*/
	public Adresse() {
	}//end ctor vide

	public Adresse(Long idAdresse, int numero, String rue, String codePostal, String ville, Personnes personne) {
		super();
		this.idAdresse = idAdresse;
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.proprio = personne;
	}

	public Adresse(int numero, String rue, String codePostal, String ville, Personnes personne) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.proprio = personne;
	}

	
	/*____________________getters/setters_______________________*/
	
	public Long getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(Long idAdresse) {
		this.idAdresse = idAdresse;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Personnes getPersonne() {
		return proprio;
	}

	public void setPersonne(Personnes personne) {
		this.proprio = personne;
	}
	
	
	
	
	
	
	
}//end class
