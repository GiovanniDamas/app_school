package com.intiformation.appschool.modeles;

import java.io.Serializable;

<<<<<<< HEAD
=======
import javax.persistence.CascadeType;
>>>>>>> ff55219e0a5bd6a9db83eebe036a0b1ca95c9a82
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
=======
import javax.persistence.ManyToOne;
>>>>>>> ff55219e0a5bd6a9db83eebe036a0b1ca95c9a82
import javax.persistence.Table;

/**
 * modèle de données pour les étudiants absents à un cours
 * ce modèle est mappé vers une table dans la bdd
 * @author marle
 *
 */
@Entity
@Table(name="etudiants_cours")
public class EtudiantCours implements Serializable{

	/*___________ propriétés ___________*/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_etudiant_cours")
	private Long idEtudiantCours;
	
	@Column(name="absence")
	private boolean absence;
	
	@Column(name="motif")
	private String motif;
	
	/*__________ associations __________*/
		
	/**
	 * relation entre etudiant et etudiantCours 
	 */
<<<<<<< HEAD
=======
	@ManyToOne(cascade=CascadeType.ALL)
>>>>>>> ff55219e0a5bd6a9db83eebe036a0b1ca95c9a82
	@JoinColumn(name="etudiant_id", referencedColumnName="idPersonne")
	private Etudiants etudiant;
	
	/**
	 * relation entre cours et etudiantCours 
	 */
<<<<<<< HEAD
=======
	@ManyToOne(cascade=CascadeType.ALL)
>>>>>>> ff55219e0a5bd6a9db83eebe036a0b1ca95c9a82
	@JoinColumn(name="cours_id", referencedColumnName="id_cours")
	private Cours cours;
	
	/*__________ ctor __________*/
	/**
	 * ctor vide
	 */
	public EtudiantCours() {
	}
	
	/*__________ getters/setters __________*/
	
	public Long getIdEtudiantCours() {
		return idEtudiantCours;
	}

	public void setIdEtudiantCours(Long idEtudiantCours) {
		this.idEtudiantCours = idEtudiantCours;
	}

	public boolean getAbsence() {
		return absence;
	}

	public void setAbsence(boolean absence) {
		this.absence = absence;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Etudiants getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiants etudiant) {
		this.etudiant = etudiant;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}
	
}//end class
