package com.intiformation.appschool.modeles;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <pre>
 * Modèle de données pour une table d'association entre Etudiant, Matiere et Promotion
 * Cette classe est mappée sur une table dans la database
 * </pre>
 * 
 * @author hannahlevardon
 *
 */

@Entity
@Table(name = "enseignantMatierePromotionLink")
public class EnseignantMatierePromotionLink {

	@Id
	@GeneratedValue
	private Long id;

	// _________________ ASSOCIATIONS ___________________ //
	/**
	 * avec Matiere, Promotion et Etudiants: ManyToMany
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matiere_id")
	private Matiere matiere;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;

	//@ManyToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "etudiant_id")
	//private Etudiant etudiant;

	// _________________ CONSTRUCTEUR ___________________ //
	/**
	 * Contructeur vide
	 */
	public EnseignantMatierePromotionLink() {
	}
	
	// _________________ GETTER / SETTER ___________________ //


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}
/*
	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
*/	
	

}// end class
