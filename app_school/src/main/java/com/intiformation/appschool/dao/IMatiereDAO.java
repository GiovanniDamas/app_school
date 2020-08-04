package com.intiformation.appschool.dao;

import com.intiformation.appschool.modeles.Matiere;

import java.util.List; 

/**
 * Interface spécifique de la DAO pour la classe Matiere
 * Contient les méthodes spécifiques à une matière 
 * @author hannahlevardon
 *
 */
public interface IMatiereDAO extends IGenerique<Matiere> {
	
	
	// _________________ METHODES SPECIFIQUES ___________________ //
	
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
	


}//end interface 

