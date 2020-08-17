package com.intiformation.appschool.modeles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;
import java.io.Serializable;

/**
 * <pre>
 * Modèle de données pour une matière
 * Cette classe est mappée sur une table dans la database
 * </pre>
 * @author hannahlevardon
 *
 */
@Entity
@Table(name="matieres")
public class Matiere implements Serializable {
	
	// _________________ PROPRIETES ___________________ //
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_matiere")
	private Long idMatiere;
	
	@Column(name="libelle")
	private String libelle; 
	
	// _________________ ASSOCIATIONS ___________________ //
	/**
	 * Relation entre Matiere et Cours: un matiere pour plusieurs cours
	 * OneToMany
	 * 	 
	 */
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="matieres")
	private List<Cours> coursMatiere;
	
	/**
	 * Relation entre Promotion et EnseignantMatierePromotionLink: OneToMany
	 * 	 	 
	 */
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="matiere")
	private List<EnseignantMatierePromotionLink> enseignantMatierePromotionLinks;
	

	// _________________ CONSTRUCTEUR ___________________ //
	/**
	 * Contructeur vide
	 */
	public Matiere() {
	}
	
	
	// _________________ GETTER / SETTER ___________________ //
	public Long getIdMatiere() {
		return idMatiere;
	}

	public void setIdMatiere(Long idMatiere) {
		this.idMatiere = idMatiere;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	
	public List<Cours> getCoursMatiere() {
		return coursMatiere;
	}

	public void setCoursMatiere(List<Cours> coursMatiere) {
		this.coursMatiere = coursMatiere;
	}


}//end classe
