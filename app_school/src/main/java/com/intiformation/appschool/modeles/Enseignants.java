package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe fille qui hérite de la classe Personne
 * @author giovanni
 *
 */
@Entity
@Table(name="enseignants")
public class Enseignants extends Personnes implements Serializable{
	
	//////// CTOR ///////
	/**
	 * Ctor vide
	 */
	public Enseignants() {
	}
	
	/**
	 * Ctor chargé à partir de la classe mère sans ID
	 * @param identifiant
	 * @param motDePasse
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Enseignants(String identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
		
	}
	
	
	
}//END CLASS
