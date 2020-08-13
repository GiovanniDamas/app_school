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

	/*________________________________________________________________________________________________________________________*/
	
	/**
	 * permet de récup la liste des absences de la bdd liées à un enseignant
	 */
	@Override
	public List<EtudiantCours> findAbsenceEnseignant(Long pIdEnseignant) {
		return etudiantCoursDAO.afficherAbsenceEnseignant(pIdEnseignant);
	}//end afficherAbsenceEnseignant

}//end class
