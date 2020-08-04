package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Classe Fille qui hérite de la classe Personne
 * @author giovanni
 */
@Entity
@Table(name="administrateurs")
public class Administrateurs extends Personnes implements Serializable{
	
	//////// CTOR ///////:
	public Administrateurs() {
	}

	
	/**
	 * Ctor chargé à partir de la classe mère Personne
	 * @param identifiant
	 * @param motDePasse
	 * @param nom
	 * @param prenom
	 * @param email
	 */
	public Administrateurs(String identifiant, String motDePasse, String nom, String prenom, String email) {
		super(identifiant, motDePasse, nom, prenom, email);
	}
	
	
	
}//END CLASS
