package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Promotion;

/**
 * Interface spécifique de la DAO pour la classe Promotion
 * Contient les méthodes spécifiques à une promotion 
 * @author hannahlevardon
 *
 */
public interface IPromotionDAO extends IGenerique<Promotion> {
	
	// _________________ METHODES SPECIFIQUES ___________________ //
	
	/**
	 * methode pour retourner la liste des promotions d'un etudiant à partir de son id
	 * @param pIdEtudiant
	 * @return
	 */
	public List<Promotion> afficherPromotionByEtudiant(Long pIdEtudiant);
	
	/**
	 * methode pour retourner la liste des promotions d'un enseignant à partir de son id
	 * @param pIdEnseignant
	 * @return
	 */
	public List<Promotion> afficherPromotionByEnseignant(Long pIdEnseignant);

	/**
	 * methode pour retourner la liste des promotions associés à une matière 
	 * @param pIdMatiere
	 * @return
	 */
	public List<Promotion> afficherPromotionByMatiere(Long pIdMatiere);
	

}//end interface
