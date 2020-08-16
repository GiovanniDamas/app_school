package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;

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
	
	/**
	 * permet de récupérer l'ensemble des absences d'un etudiant dans la bdd
	 */
	public List<EtudiantCours> afficherAbsencesByEtudiant(Long pIdEtudiant);
	/*________________________________________________________________________________________________________________________*/
	
	/**
	 * permet de récup la liste de présence de la bdd en fonction de la personne connectée
	 */
	public List<EtudiantCours> findEtudiantCoursPersonne(Long pIdPersonne, String pRole);
	
	/**
	 * permet de récup la liste des absences de la bdd en fonction de la personne connectée
	 */
	public List<EtudiantCours> findAbsencesPersonne(Long pIdPersonne, String pRole);
	
	/**
	 * permet de récupérer l'id d'un EtudiantCours à partir de l'étudiant et du cours
	 */
	public Long findIdEtudiantCours(Long pIdEtudiant, Long pIdCours);
	
}//end interface
