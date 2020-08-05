package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;





/**
 * Entité mère des classes etudiant, enseignant, administrateur </br>
 * Classe mappé par rapport à la table Personne </br>
 * @author giovanni
 *
 */
@Entity
@Table(name="personnes")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Personnes implements Serializable{
	
	////////// PROPS ////////
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idPersonne")
	Long idPersonne;
	
	@Column(name="Identifiant")
	String identifiant;
	
	@Column(name="MotDePasse")
	String motDePasse;
	
	@Column(name="Nom")
	String nom;
	
	@Column(name="Prenom")
	String prenom; 
	
	@Column(name="Email")
	String email;
	
	
	/////// CTOR ////////
	/**
	 * ctor vide
	 */
	public Personnes() {
	
	}

	/**
	 * ctor chargé sans id
	 * @param identifiant
	 * @param motDePasse
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Personnes(String identifiant, String motDePasse, String nom, String prenom, String email) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	
	////// GETTERS / SETTERS //////////
	
	public int getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}//END CLASS
