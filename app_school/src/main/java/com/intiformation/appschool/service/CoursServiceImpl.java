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
	 * permet de récup la liste des cours de la bdd d'un enseignant à une date donnée
	 */
	@Override
	public List<Cours> findCoursEnseignantByDate(Long pIdEnseignant, Date pDate) {
		return coursDAO.afficherCoursEnseignantByDate(pIdEnseignant, pDate);
	}

	/**
	 * permet de récup la liste des cours de la bdd associés à une personne
	 * @param pIdPersonne : l'id de la personne
	 * @return
	 */
	@Override
	public List<Cours> findCoursPersonne(Long pIdPersonne, String pRole) {
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return coursDAO.getAll();

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return coursDAO.afficherCoursEnseignant(pIdPersonne);

		} else if (pRole.contains("ROLE_ETUDIANT")) {
			//cas d'un role = ROLE_ETUDIANT
			return coursDAO.afficherCoursEtudiant(pIdPersonne);
			
		}//end else
		
		return null;
	}//end findCoursPersonne
	
	/**
	 * permet de récup la liste des cours de la bdd associés à une personne d'une matière
	 * @param pIdPersonne : l'id de la personne
	 * @return
	 */
	@Override
	public List<Cours> findCoursPersonneMatiere(Long pIdPersonne, String pRole, Long pIdMatiere){
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return coursDAO.afficherCoursParMatiere(pIdMatiere);

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return coursDAO.afficherCoursEnseignantMatiere(pIdPersonne, pIdMatiere);

		} else if (pRole.contains("ROLE_ETUDIANT")) {
			//cas d'un role = ROLE_ETUDIANT
			return coursDAO.afficherCoursEtudiantMatiere(pIdPersonne, pIdMatiere);
			
		}//end else
		
		return null;
	}//end findCoursPersonneMatiere

	/**
	 * permet de récup la liste des cours de la bdd associés à une personne d'une promotion
	 */
	@Override
	public List<Cours> findCoursPersonneByPromotion(Long pIdPersonne, Long pIdPromotion, String pRole) {
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return coursDAO.afficherCoursParPromotion(pIdPromotion);

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return coursDAO.afficherCoursEnseignantByPromotion(pIdPersonne, pIdPromotion);
			
		}//end else
		
		return null;	
		
	}//end findCoursEnseignantByPromotion

	/**
	 * méthode pour affichier la liste des cours par date associés à une personne
	 * @param pDate
	 * @return la liste des cours à la date souhaitée
	 */
	@Override
	public List<Cours> findCoursPersonneByDate(Long pIdPersonne, String pRole, Date pDate) {
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return coursDAO.afficherCoursParDate(pDate);

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return coursDAO.afficherCoursEnseignantByDate(pIdPersonne, pDate);
			
		}//end else
		
		return null;		
	}//end findCoursPersonneByDate

}//end class
