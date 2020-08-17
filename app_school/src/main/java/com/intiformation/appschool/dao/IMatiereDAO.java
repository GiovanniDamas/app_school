package com.intiformation.appschool.dao;

import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.modeles.Promotion;

import java.util.List; 

/**
 * Interface spécifique de la DAO pour la classe Matiere
 * Contient les méthodes spécifiques à une matière 
 * @author hannahlevardon
 *
 */
public interface IMatiereDAO extends IGenerique<Matiere> {
	
	
	// _________________ METHODES SPECIFIQUES ___________________ //
	
	public Matiere addMatiere(Matiere pMatiere);	
	
	/**
	 * Méthode pour retourner la liste des matières d'une promotion à partir de son Id
	 * @return: liste des matières par promotion
	 */
	public List<Matiere> getMatiereParPromotion(Long iDPromotion);
	
	/**
	 * Méthode pour retourner la liste des matières d'un enseignant à partir de son Id
	 * @param iDEnseignant
	 * @return liste des matières par enseignant
	 */
	public List<Matiere> getMatieresParEnseignant(Long iDEnseignant);
	
	/**
	 * methode pour retourner la liste des matière d'un enseignant à partir de son id
	 * @param pIdEnseignant
	 * @return
	 */
	public List<Matiere> afficherMatiereByEnseignant(Long pIdEnseignant);

	/**
	 * methode pour retourner la liste des matière d'un etudiant à partir de son id
	 * @param pIdEtudiant
	 * @return
	 */
	public List<Matiere> afficherMatiereByEtudiant(Long pIdEtudiant);

}//end interface 

