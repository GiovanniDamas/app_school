package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Etudiants;

/**
 * Interface pour la couche service pour les etudiants
 * @author giovanni
 *
 */
public interface IEtudiantsService {

	/**
	 * Méthode permettant d'ajouter un étudiant dans la BDD
	 * @param pEtudiant
	 */
	public void ajouterEtudiant(Etudiants pEtudiant);
	
	/**
	 * Méthode pour modifier les données d'un étudiant
	 * @param pEtudiant
	 */
	public void modifierEtudiant(Etudiants pEtudiant);
	
	/**
	 * Méthode pour supprimer un étudiant à partir de son ID
	 * @param pIdEtudiant
	 */
	public void suppEtudiant(int pIdEtudiant);
	
	/**
	 * Méthode pour récupérer un étudiant via son ID
	 * @param pIdEtudiant
	 * @return
	 */
	public Etudiants findEtudiantById(int pIdEtudiant);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les étudiants
	 * @return
	 */
	public List<Etudiants> findAllEtudiant();
	

}//END INTERFACE
