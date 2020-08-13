package com.intiformation.appschool.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.ICoursDAO;
import com.intiformation.appschool.modeles.Cours;

/**
 * implémentation concrète de la couche service pour les cours
 * implémente ICoursService
 * @author marle
 *
 */
@Service //bean spring de la couche service
public class CoursServiceImpl implements ICoursService{

	//déclaration de la DAO
	@Autowired //injection par type du bean coursDAO dans cette propriété
	private ICoursDAO coursDAO;

	/**
	 * setter de coursDAO pour l'injection par modificateur de spring
	 * @param coursDAO
	 */
	public void setCoursDAO(ICoursDAO coursDAO) {
		this.coursDAO = coursDAO;
	}

	/**
	 * permet d'ajouter un cours à la bdd
	 */
	@Override
	public void ajouterCours(Cours pCours) {
		coursDAO.add(pCours);
	}//end ajouterCours

	/**
	 * permet de modifier un cours de la bdd
	 */
	@Override
	public void modifierCours(Cours pCours) {
		coursDAO.update(pCours);
	}//end modifierCours

	/**
	 * permet de supprimer un cours de la bdd
	 */
	@Override
	public void supprimerCours(Long pIdCours) {
		coursDAO.delete(pIdCours);
	}//end supprimerCours

	/**
	 * permet de trouver un cours de la bdd par son id
	 */
	@Override
	public Cours findCoursById(Long pIdCours) {
		return coursDAO.getById(pIdCours);
	}//end findCoursById

	/**
	 * permet de récup la liste de tous les cours de la bdd
	 */
	@Override
	public List<Cours> findAllCours() {
		return coursDAO.getAll();
	}//end findAllCours

	/**
	 * permet de récup la liste des cours par matière
	 */
	@Override
	public List<Cours> findCoursParMatiere(Long pIdMatiere) {
		return coursDAO.afficherCoursParMatiere(pIdMatiere);
	}//end findCoursParMatiere
	
	/**
	 * permet de récup la liste des cours par promotion
	 */
	@Override
	public List<Cours> findCoursParPromotion(Long pIdPromotion) {
		return coursDAO.afficherCoursParPromotion(pIdPromotion);
	}//end findCoursParPromotion

	/**
	 * permet de récup la liste des cours par date
	 */
	@Override
	public List<Cours> findCoursParDate(Date pDate) {
		return coursDAO.afficherCoursParDate(pDate);
	}//end findCoursParDate

	/**
	 * permet de récup la liste des cours d'un enseignant
	 */
	@Override
	public List<Cours> findCoursEnseignant(Long pIdEnseignant) {
		return coursDAO.afficherCoursEnseignant(pIdEnseignant);
	}
	
	/**
	 * permet de récup la liste des cours d'un etudiant
	 */
	@Override
	public List<Cours> findCoursEtudiant(Long pIdEtudiant) {
		return coursDAO.afficherCoursEtudiant(pIdEtudiant);
	}

}//end class
