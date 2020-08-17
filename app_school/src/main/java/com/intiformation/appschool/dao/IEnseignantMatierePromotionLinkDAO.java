package com.intiformation.appschool.dao;

import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;
import java.util.List; 

/**
 * Interface spécifique de la DAO pour la classe EnseignantMatierePromotionLink
 * Contient les méthodes spécifiques 
 * @author hannahlevardon
 *
 */
public interface IEnseignantMatierePromotionLinkDAO extends IGenerique<EnseignantMatierePromotionLink> {
	
	
	
	// _________________ METHODES SPECIFIQUES ___________________ //
	
	public List<EnseignantMatierePromotionLink> getByIdPromotion(Long pIdPromotion);
	
	public List<EnseignantMatierePromotionLink> getByIdMatiere(Long pIdMatiere);
	
	
	

}//end interface
