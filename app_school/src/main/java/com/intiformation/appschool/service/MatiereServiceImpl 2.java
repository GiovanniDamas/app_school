package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IMatiereDAO;
import com.intiformation.appschool.modeles.Matiere;

/**
 * <pre>
 * 	> Implémentation concrète de la couche Service pour Matiere
 * 	> Implémente l'interface IMatiereService
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */
@Service("matiereServiceBean")
public class MatiereServiceImpl implements IMatiereService {

	// _________________ PROPRIETES ___________________ //

	// Déclaration de la couche DAO 
	@Autowired // Injection par type du bean sessionFactory dans cette propriété
	private IMatiereDAO matiereDAO;

	/**
	 * Setter de la couche DAO pour injection par modificateur de Spring
	 * 
	 * @param matiereDAO
	 */
	public void setMatiereDAO(IMatiereDAO matiereDAO) {
		this.matiereDAO = matiereDAO;
	}//end setter
	
	// _________________ METHODES ___________________ //


	@Override
	public void ajouterMatiere(Matiere pMatiere) {
		matiereDAO.add(pMatiere);
	}//end ajouterMatiere

	@Override
	public void modifierMatiere(Matiere pMatiere) {
		matiereDAO.update(pMatiere);

	}//end modifierMatiere

	@Override
	public void supprimerMatiere(Long pIdMatiere) {
		matiereDAO.delete(pIdMatiere);
	}//end supprimerMatiere

	@Override
	public Matiere trouverMatiereId(Long pIdMatiere) {

		return matiereDAO.getById(pIdMatiere);
	}//end trouverMatiereId()

	@Override
	public List<Matiere> trouverAllMatieres() {
		return matiereDAO.getAll();
	}//end trouverAllMatieres

}// end classe
