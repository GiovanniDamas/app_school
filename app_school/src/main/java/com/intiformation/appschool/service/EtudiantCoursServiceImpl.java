package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IEtudiantCoursDAO;
import com.intiformation.appschool.modeles.EtudiantCours;

/**
 * implémentation concrète de la couche service pour les EtudiantCours
 * implémente IEtudiantCoursService
 * @author marle
 *
 */
@Service //bean spring de la couche service
public class EtudiantCoursServiceImpl implements IEtudiantCoursService{
	
	//déclaration de la DAO
	@Autowired //injection par type du bean coursDAO dans cette propriété
	private IEtudiantCoursDAO etudiantCoursDAO;

	/**
	 * setter de etudiantCoursDAO pour l'injection par modificateur de spring
	 * @param etudiantCoursDAO
	 */
	public void setEtudiantCoursDAO(IEtudiantCoursDAO etudiantCoursDAO) {
		this.etudiantCoursDAO = etudiantCoursDAO;
	}
	
	/**
	 * permet d'ajouter un EtudiantCours dans la bdd
	 */
	@Override
	public void ajouterEtudiantCours(EtudiantCours pEtudiantCours) {
		etudiantCoursDAO.add(pEtudiantCours);
	}//end ajouterEtudiantCours

	/**
	 * permet de modifier un EtudiantCours dans la bdd
	 */
	@Override
	public void modifierEtudiantCours(EtudiantCours pEtudiantCours) {
		etudiantCoursDAO.update(pEtudiantCours);
	}//end modifierEtudiantCours

	/**
	 * permet de supprimer un EtudiantCours dans la bdd
	 */
	@Override
	public void supprimerEtudiantCours(Long pIdEtudiantCours) {
		etudiantCoursDAO.delete(pIdEtudiantCours);
	}//end supprimerEtudiantCours

	/**
	 * permet de récup un EtudiantCours dans la bdd via son id
	 */
	@Override
	public EtudiantCours findEtudiantCoursById(Long pIdEtudiantCours) {
		return etudiantCoursDAO.getById(pIdEtudiantCours);
	}//end findEtudiantCoursById

	/**
	 * permet de récup la liste des EtudiantCours de la bdd
	 */
	@Override
	public List<EtudiantCours> findAllEtudiantCours() {
		return etudiantCoursDAO.getAll();
	}//end findAllEtudiantCours

	/**
	 * permet de récup la liste des EtudiantCours par cours de la bdd
	 */
	@Override
	public List<EtudiantCours> afficherEtudiantCoursByCours(Long pIdCours) {
		return etudiantCoursDAO.afficherEtudiantCoursByCours(pIdCours);
	}//end afficherEtudiantCoursByCours

	/**
	 * permet de récup la liste des EtudiantCours par étudiant de la bdd
	 */
	@Override
	public List<EtudiantCours> afficherEtudiantCoursByEtudiant(Long pIdEtudiant) {
		return etudiantCoursDAO.afficherEtudiantCoursByEtudiant(pIdEtudiant);
	}//end afficherEtudiantCoursByEtudiant

	/**
	 * permet de récupérer l'ensemble des absences d'un etudiant dans la bdd
	 */
	public List<EtudiantCours> afficherAbsencesByEtudiant(Long pIdEtudiant){
		return etudiantCoursDAO.afficherAbsencesByEtudiant(pIdEtudiant);
	}
	
	/*________________________________________________________________________________________________________________________*/

	/**
	 * permet de récup la liste de présence de la bdd en fonction de la personne connectée
	 */
	@Override
	public List<EtudiantCours> findEtudiantCoursPersonne(Long pIdPersonne, String pRole) {
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return etudiantCoursDAO.getAll();

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return etudiantCoursDAO.afficherEtudiantCoursByEnseignant(pIdPersonne);

		} else if (pRole.contains("ROLE_ETUDIANT")) {
			//cas d'un role = ROLE_ETUDIANT
			return etudiantCoursDAO.afficherEtudiantCoursByEtudiant(pIdPersonne);
			
		}//end else
		
		return null;
	}//end findEtudiantCoursPersonne
	
	/**
	 * permet de récup la liste des absences de la bdd en fonction de la personne connectée
	 */
	@Override
	public List<EtudiantCours> findAbsencesPersonne(Long pIdPersonne, String pRole) {
		
		if (pRole.contains("ROLE_ADMIN")) {
			//cas d'un role = ROLE_ADMIN
			return etudiantCoursDAO.getAllAbsences();

		} else if (pRole.contains("ROLE_ENSEIGNANT")){
			//cas d'un role = ROLE_ENSEIGNANT
			return etudiantCoursDAO.afficherAbsencesByEnseignant(pIdPersonne);

		} else if (pRole.contains("ROLE_ETUDIANT")) {
			//cas d'un role = ROLE_ETUDIANT
			return etudiantCoursDAO.afficherAbsencesByEtudiant(pIdPersonne);
			
		}//end else
		
		return null;
	}//end findEtudiantCoursPersonne

}//end class
