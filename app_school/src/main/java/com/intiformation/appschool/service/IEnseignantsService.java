package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Enseignants;

/**
 * Interface pour la couche service pour les enseignants
 * @author giovanni
 *
 */
public interface IEnseignantsService {
	
	/**
	 * Méthode permettant d'ajouter un enseignant dans la BDD
	 * @param pEnseignant
	 */
	public void ajouterEnseignant(Enseignants pEnseignant);
	
	/**
	 * Méthode pour modifier les données d'un enseignant
	 * @param pEnseignant
	 */
	public void modifierEnseignant(Enseignants pEnseignant);
	
	/**
	 * Méthode pour supprimer un enseignant à partir de son ID
	 * @param pIdEnseignant
	 */
	public void suppEnseignant(Long pIdEnseignant);
	
	/**
	 * Méthode pour récupérer un enseignant via son ID
	 * @param pIdEnseignant
	 * @return
	 */
	public Enseignants findEnseignantById(Long pIdEnseignant);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les enseignants
	 * @return
	 */
	public List<Enseignants> findAllEnseignant();


}//END INTERFACE
