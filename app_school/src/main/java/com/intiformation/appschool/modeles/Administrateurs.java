package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe Fille qui hérite de la classe Personne
 * @author giovanni
 */
@Entity
@Table(name="administrateurs")
public class Administrateurs extends Personnes implements Serializable{
	
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_admin")
	int idAmin;
	
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


	public int getIdAmin() {
		return idAmin;
	}


	public void setIdAmin(int idAmin) {
		this.idAmin = idAmin;
	}
	
	
	
}//END CLASS
