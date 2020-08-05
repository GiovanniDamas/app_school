package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
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
	@Column(name="id_personne")
	private int idPersonne;
	
	@Column(name="identifiant")
	private String identifiant;
	
	@Column(name="mot_de_passe")
	private String motDePasse;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom; 
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="adresse")
	@OneToMany(mappedBy="personnes", cascade= CascadeType.ALL)
	private List<Adresse> adresses;
	
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
	
	public long getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(long idPersonne) {
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

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}
	
	

}//END CLASS
