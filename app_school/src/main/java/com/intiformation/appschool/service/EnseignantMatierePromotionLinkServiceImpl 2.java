package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IEnseignantMatierePromotionLinkDAO;
import com.intiformation.appschool.dao.IMatiereDAO;
import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;

/**
 * <pre>
 * 	> Implémentation concrète de la couche Service pour EnseignantMatierePromotionLink
 * 	> Implémente l'interface IEnseignantMatierePromotionLinkService
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */
@Service("linkEMPServiceBean")
public class EnseignantMatierePromotionLinkServiceImpl implements IEnseignantMatierePromotionLinkService {

	// _________________ PROPRIETES ___________________ //

	// Déclaration de la couche DAO
	@Autowired // Injection par type du bean sessionFactory dans cette propriété
	private IEnseignantMatierePromotionLinkDAO linkDAO;

	/**
	 * Setter de la couche DAO pour injection par modificateur de Spring
	 * 
	 * @param linkDAO
	 */
	public void setLinkDAO(IEnseignantMatierePromotionLinkDAO linkDAO) {
		this.linkDAO = linkDAO;
	}//end setter

	// _________________ METHODES ___________________ //

	@Override
	public void ajouterLink(EnseignantMatierePromotionLink pLink) {
		linkDAO.add(pLink);
	}//end ajouterLink

	@Override
	public void modifierLink(EnseignantMatierePromotionLink pLink) {
		linkDAO.update(pLink);
	}// end modifierLink

	@Override
	public void supprimerLink(Long pIdLink) {
		linkDAO.delete(pIdLink);
	}//end supprimerLink

	@Override
	public EnseignantMatierePromotionLink trouverLinkId(Long pIdLink) {
		return linkDAO.getById(pIdLink);
	}//end trouverLinkId

	@Override
	public List<EnseignantMatierePromotionLink> trouverAllLinks() {
		return linkDAO.getAll();
	}//end trouverAllLinks

	
	

}// end class
