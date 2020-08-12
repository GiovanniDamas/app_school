package com.intiformation.appschool.modeles;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class EnseignantMatierePromotionLink implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	// _________________ ASSOCIATIONS ___________________ //
	/**
	 * avec Matiere, Promotion et Etudiants: ManyToMany
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "matiere_id", referencedColumnName="id_matiere")
	private Matiere matiere;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "promotion_id", referencedColumnName="id_promotion")
	private Promotion promotion;


	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personne_id", referencedColumnName="id_personne")
	private Enseignants enseignant;

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

	public Enseignants getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignants enseignant) {
		this.enseignant = enseignant;
	}



	
	

}// end class
