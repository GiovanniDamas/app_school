package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IMatiereDAO;
import com.intiformation.appschool.dao.IPromotionDAO;
import com.intiformation.appschool.modeles.Promotion;

/**
 * <pre>
 * 	> Implémentation concrète de la couche Service pour Promotion
 * 	> Implémente l'interface IPromotionService
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */
@Service("promotionServiceBean")
public class PromotionServiceImpl implements IPromotionService {

	// _________________ PROPRIETES ___________________ //

	// Déclaration de la couche DAO
	@Autowired // Injection par type du bean sessionFactory dans cette propriété
	private IPromotionDAO promotionDAO;

	/**
	 * Setter de la couche DAO pour injection par modificateur de Spring
	 * 
	 * @param sessionFactory
	 */
	public void setPromotionDAO(IPromotionDAO promotionDAO) {
		this.promotionDAO = promotionDAO;
	}// end setter

	// _________________ METHODES ___________________ //

	@Override
	public void ajouterPromotion(Promotion pPromotion) {
		promotionDAO.add(pPromotion);
	}//end ajouterPromotion

	@Override
	public void modifierPromotion(Promotion pPromotion) {
		promotionDAO.update(pPromotion);
	}//end modifierPromotion

	@Override
	public void supprimerPromotion(Long pIdPromotion) {
		promotionDAO.delete(pIdPromotion);
	}//end supprimerPromotion

	@Override
	public Promotion trouverPromotionId(Long pIdPromotion) {
		return promotionDAO.getById(pIdPromotion);
	}//end trouverPromotionId

	@Override
	public List<Promotion> trouverAllPromotions() {
		return promotionDAO.getAll();
	}//end trouverAllPromotions

}// end classe
