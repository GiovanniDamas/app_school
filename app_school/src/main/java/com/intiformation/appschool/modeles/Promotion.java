package com.intiformation.appschool.modeles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.io.Serializable;

/**
 * <pre>
 * Modèle de données pour une promotion
 * Cette classe est mappée sur une table dans la database
 * </pre>
 * @author hannahlevardon
 *
 */

@Entity
@Table(name="promotions")
public class Promotion implements Serializable {
	
	// _________________ PROPRIETES ___________________ //
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_promotion")
	private Long idPromotion;
	
	@Column(name="libelle")
	private String libelle; 
	
	// _________________ ASSOCIATIONS ___________________ //
	/**
	 * Relation entre Promotion et Etudiant: plusieurs promotion pour plusieurs etudiants
	 * ManyToMany	 	 
	 */
	/*
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="promotion_assos_etudiants",
			   joinColumns=@JoinColumn(name="promotion_id"),
			   inverseJoinColumns=@JoinColumn(name="etudiant_id"))	
	private List<Etudiant> etudiantsPromotions;
	*/
	
	/**
	 * Relation entre Promotion et EnseignantMatierePromotionLink: OneToMany
	 * 	 	 
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="promotions")
	private List<EnseignantMatierePromotionLink> enseignantMatierePromotionLinks;
	
	/**
	 * Relation entre Promotion et Cours: OneToMany
	 * (une promotion pour plusieurs cours)
	 * Mais je ne suis pas trop d'accord : en parler avec les autre
	 * 	 	 
	 */
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="promotions")
	//private List<Cours> coursPromotion; 
	
	// _________________ CONSTRUCTEUR ___________________ //
	/**
	 * Contructeur vide
	 */
	public Promotion() {
	}
	
	
	// _________________ GETTER / SETTER ___________________ //

	public Long getIdPromotion() {
		return idPromotion;
	}

	public void setIdPromotion(Long idPromotion) {
		this.idPromotion = idPromotion;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

	
	
	

}//end classe
