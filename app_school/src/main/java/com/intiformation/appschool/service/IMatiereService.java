package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Matiere;

/**
 * Interface spécifique de la couche service pour la classe Matiere
 *  
 * @author hannahlevardon
 *
 */
public interface IMatiereService {
	
	/**
	 * Méthode pour ajouter une matière à la database
	 * @param pMatiere
	 */
	public void ajouterMatiere(Matiere pMatiere);	
	
	/**
	 * Méthode pour modifier une matière dans la database
	 * @param pMatiere
	 */
	public void modifierMatiere(Matiere pMatiere);
	
	/**
	 * Méthode pour supprimer une matière de la database
	 * @param pIdMatiere
	 */
	public void supprimerMatiere(Long pIdMatiere);
	
	/**
	 * Méthode pour retrouver une matièere par son Id
	 * @param pIdpIdMatiere
	 * @return Matiere
	 */
	public Matiere trouverMatiereId(Long pIdMatiere);
	
	
	/**
	 * Méthode pour afficher la liste des matières de la database
	 * @return Liste des matières
	 */
	public List<Matiere> trouverAllMatieres();
	
	/**
	 * méthode pour afficher la liste des matières associées à une personne 
	 * @param pIdPersonne
	 * @return
	 */
	public List<Matiere> findMatiereByPersonne(Long pIdPersonne, String pRole);
	

}//end interface
