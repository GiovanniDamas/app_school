package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Classe fille héritant de la classe Personne
 * 
 * @author giovanni
 *
 */
@Entity
@Table(name="etudiants")
public class Etudiants extends Personnes implements Serializable {

	////// PROPS ////////
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etudiant")
	Long idEtudiant;
	
	@Column(name="Photo")
	String photo;
	
	@Column(name="DateDeNaissance")
	Date dateDeNaissance;

	///////// CTOR /////////
	/**
	 * Ctor vide
	 */
	public Etudiants() {
	}

	/**
	 * Ctor chargé à partir de la classe mère sans ID
	 * 
	 * @param identifiant
	 * @param motDePasse
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Etudiants(String identifiant, String motDePasse, String nom, String prenom, String email, String photo, Date dateDeNaissance) {
		super(identifiant, motDePasse, nom, prenom, email);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance ;
	}

	////// GETTERS / SETTERS //////
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public Long getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(Long idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
	
	

}// END CLASS
