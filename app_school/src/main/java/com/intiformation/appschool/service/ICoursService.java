package com.intiformation.appschool.service;

import java.util.Date;
import java.util.List;

import com.intiformation.appschool.modeles.Cours;

/**
 * interface spécifique de la couche service pour les cours 
 * @author marle
 *
 */
public interface ICoursService {
	
	/**
	 * Méthode pour ajouter un cours
	 * @param pCours
	 */
	public void ajouterCours(Cours pCours);	
	
	/**
	 * Méthode pour modifier un cours
	 * @param pCours
	 */
	public void modifierCours(Cours pCours);
	
	/**
	 * Méthode pour supprimer un cours
	 * @param pIdCours
	 */
	public void supprimerCours(Long pIdCours);
	
	/**
	 * Méthode pour retrouver un cours par son Id
	 * @param pIdCours
	 * @return le cours recherché
	 */
	public Cours findCoursById(Long pIdCours);
	
	/**
	 * Méthode pour afficher la liste des cours
	 * @return la liste de tous les cours
	 */
	public List<Cours> findAllCours(); 
		
	/**
	 * Méthode pour afficher la liste des cours par matière
	 * @param pIdMatiere
	 * @return la liste des cours de la matière souhaitée
	 */
	public List<Cours> findCoursParMatiere(Long pIdMatiere);
	
	/**
	 * Méthode pour afficher la liste des cours par promotion
	 * @param pIdPromotion
	 * @return la liste des cours de la promotion souhaitée
	 */
	public List<Cours> findCoursParPromotion(Long pIdPromotion);
	
	/**
	 * méthode pour affichier la liste des cours par date
	 * @param pDate
	 * @return la liste des cours à la date souhaitée
	 */
	public List<Cours> findCoursParDate(Date pDate);
	
	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant à une date donnée
	 */
	public List<Cours> findCoursEnseignantByDate(Long pIdEnseignant, Date pDate);
	
	/**
	 * permet de récup la liste des cours de la bdd associés à une personne
	 * @param pIdPersonne : l'id de la personne
	 * @return
	 */
	public List<Cours> findCoursPersonne(Long pIdPersonne, String pRole);
	
	/**
	 * Méthode pour afficher la liste des cours par matière associés à une personne
	 * @param pIdMatiere
	 * @return la liste des cours de la matière souhaitée
	 */
	public List<Cours> findCoursPersonneMatiere(Long pIdPersonne, String pRole, Long pIdMatiere);
	
	/**
	 * permet de récup la liste des cours de la bdd d'une promotion associés à une personne
	 */
	public List<Cours> findCoursPersonneByPromotion(Long pIdPersonne, Long pIdPromotion, String pRole);
	
	/**
	 * méthode pour affichier la liste des cours par date associés à une personne
	 * @param pDate
	 * @return la liste des cours à la date souhaitée
	 */
	public List<Cours> findCoursPersonneByDate(Long pIdPersonne, String pRole, Date pDate);
	
}//end interface
