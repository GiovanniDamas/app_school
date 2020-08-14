package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date dateDeNaissance;

	//association entre Etudiants et Promotion : ManyToOne
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id", referencedColumnName="id_promotion")
	private Promotion promotion;
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
	public Etudiants(String identifiant, String motDePasse, String nom, String prenom, String email, String role, String photo, Date dateDeNaissance, Promotion promotion) {
		super(identifiant, motDePasse, nom, prenom, email, role);
		this.photo = photo;
		this.dateDeNaissance = dateDeNaissance ;
		this.promotion = promotion;
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

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
	

}// END CLASS
