package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.EtudiantCours;

/**
 * interface spécifique de la couche service pour les EtudiantCours 
 * @author marle
 *
 */
public interface IEtudiantCoursService {
	
	/**
	 * Méthode pour ajouter un EtudiantCours
	 * @param pEtudiantCours
	 */
	public void ajouterEtudiantCours(EtudiantCours pEtudiantCours);	
	
	/**
	 * Méthode pour modifier un EtudiantCours
	 * @param pEtudiantCours
	 */
	public void modifierEtudiantCours(EtudiantCours pEtudiantCours);
	
	/**
	 * Méthode pour supprimer un EtudiantCours
	 * @param pIdEtudiantCours
	 */
	public void supprimerEtudiantCours(Long pIdEtudiantCours);
	
	/**
	 * Méthode pour retrouver un EtudiantCours par son Id
	 * @param pIdEtudiantCours
	 * @return le EtudiantCours recherché
	 */
	public EtudiantCours findEtudiantCoursById(Long pIdEtudiantCours);
	
	/**
	 * Méthode pour afficher la liste des EtudiantCours
	 * @return la liste de tous les EtudiantCours
	 */
	public List<EtudiantCours> findAllEtudiantCours(); 
	
	/**
	 * Méthode pour afficher la liste des EtudiantCours d'un cours
	 * @param pIdCours
	 * @return
	 */
	public List<EtudiantCours> afficherEtudiantCoursByCours(Long pIdCours);
	
	/**
	 * Méthode pour afficher la liste des EtudiantCours d'un étudiant
	 * @param pIdEtudiant
	 * @return
	 */
	public List<EtudiantCours> afficherEtudiantCoursByEtudiant(Long pIdEtudiant);
	
	/*________________________________________________________________________________________________________________________*/
	
	/**
	 * permet de récup la liste des absences de la bdd liées à un enseignant
	 */
	public List<EtudiantCours> findAbsenceEnseignant(Long pIdEnseignant);
	
}//end interface
