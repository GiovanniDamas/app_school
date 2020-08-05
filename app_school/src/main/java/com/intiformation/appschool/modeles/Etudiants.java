package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
	@Column(name="Photo")
	String photo;
	
	@Column(name="DateDeNaissance")
	Date dateDeNaissance;
	
	//associations
	/**
	 * relation entre étudiant et etudiantCours : un étudiant pour plusieurs etudiantsCours (One To Many)
	 */
	@OneToMany(targetEntity=EtudiantCours.class, cascade=CascadeType.ALL, mappedBy="etudiant")
	private List<EtudiantCours> listeEtudiantsCours;

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

	public List<EtudiantCours> getListeEtudiantsCours() {
		return listeEtudiantsCours;
	}

	public void setListeEtudiantsCours(List<EtudiantCours> listeEtudiantsCours) {
		this.listeEtudiantsCours = listeEtudiantsCours;
	}
	
	

}// END CLASS
