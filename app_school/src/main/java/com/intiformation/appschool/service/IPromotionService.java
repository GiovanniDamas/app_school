package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.modeles.Promotion;

/**
 * Interface spécifique de la couche service pour la classe Promotion
 *  
 * @author hannahlevardon
 *
 */
public interface IPromotionService {
	
	/**
	 * Méthode pour ajouter une promotion à la database
	 * @param pPromotion
	 */
	public void ajouterPromotion(Promotion pPromotion);	
	
	/**
	 * Méthode pour modifier une promotion dans la database
	 * @param pPromotion
	 */
	public void modifierPromotion(Promotion pPromotion);
	
	/**
	 * Méthode pour supprimer une promotion de la database
	 * @param pIdPromotion
	 */
	public void supprimerPromotion(Long pIdPromotion);
	
	/**
	 * Méthode pour retrouver une matièere par son Id
	 * @param pIdPromotion
	 * @return promotion
	 */
	public Promotion trouverPromotionId(Long pIdPromotion);
	
	
	/**
	 * Méthode pour afficher la liste des promotions de la database
	 * @return Liste des promotions
	 */
	public List<Promotion> trouverAllPromotions();
	
	/**
	 * méthode pour afficher la liste des promotions associées à une personne 
	 * @param pIdPersonne
	 * @return
	 */
	public List<Promotion> findPromotionByPersonne(Long pIdPersonne, String pRole);
	
	

}//end interface
