package com.intiformation.appschool.modeles;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * modèle de données pour un cours
 * ce modèle est mappé vers une table dans la bdd
 * @author marle
 *
 */
@Entity
@Table(name="cours")
public class Cours implements Serializable{

	/*___________ propriétés ___________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cours")
	private Long idCours;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;

	@Column(name="duree")
	private int duree;
	
	@Column(name="description")
	private String description;
	
	/*__________ associations __________*/
	/**
	 * relation entre cours et matières
	 */

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="matiere_id", referencedColumnName="id_matiere")
	private Matiere matieres;
	
	/**
	 * relation entre cours et promotion
	 */

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="promotion_id", referencedColumnName="id_promotion")
	private Promotion promotions;
	
	/**
	 * relation entre cours et etudiantCours : un cours pour plusieurs etudiantsCours (One To Many)
	 */
	@OneToMany(targetEntity=EtudiantCours.class, cascade=CascadeType.ALL, mappedBy="cours")
	private List<EtudiantCours> listeEtudiantsCours;
	
	/*___________ ctors ___________*/
	/**
	 * ctor vide
	 */
	public Cours() {
	}

	/*___________ getters/setters ___________*/

	public Long getIdCours() {
		return idCours;
	}

	public void setIdCours(Long idCours) {
		this.idCours = idCours;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Matiere getMatieres() {
		return matieres;
	}

	public void setMatieres(Matiere matieres) {
		this.matieres = matieres;
	}

	public Promotion getPromotions() {
		return promotions;
	}

	public void setPromotions(Promotion promotions) {
		this.promotions = promotions;
	}

	public List<EtudiantCours> getListeEtudiantsCours() {
		return listeEtudiantsCours;
	}

	public void setListeEtudiantsCours(List<EtudiantCours> listeEtudiantsCours) {
		this.listeEtudiantsCours = listeEtudiantsCours;
	}
	
}//end class
