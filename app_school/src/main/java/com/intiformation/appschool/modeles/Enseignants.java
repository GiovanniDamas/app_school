package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Classe fille qui hérite de la classe Personne
 * 
 * @author giovanni
 *
 */
@Entity
@Table(name = "enseignants")
public class Enseignants extends Personnes implements Serializable {

	// @GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name = "id_enseignant")
	//Long idEnseignant;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "enseignant")
	private List<EnseignantMatierePromotionLink> enseignantMatierePromotionLinks;

	//////// CTOR ///////
	/**
	 * Ctor vide
	 */
	public Enseignants() {
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
	public Enseignants(String identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);

	}


	//// GETTERS / SETTERS ////
	/*
	public Long getIdEnseignant() {
		return idEnseignant;
	}

	public void setIdEnseignant(Long idEnseignant) {
		this.idEnseignant = idEnseignant;
	}
	
	*/

}// END CLASS
