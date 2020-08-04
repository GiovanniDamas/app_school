package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Enseignants;


/**
 * Interface de la couche DAO pour les enseignants
 * @author giovanni
 *
 */
public interface IEnseignantsDAO {
	
	/**
	 * Méthode permettant d'ajouter un enseignant dans la BDD
	 * @param pEnseignant
	 */
	public void addEnseignant(Enseignants pEnseignant);
	
	/**
	 * Méthode pour modifier les données d'un enseignant
	 * @param pEnseignant
	 */
	public void updateEnseignant(Enseignants pEnseignant);
	
	/**
	 * Méthode pour supprimer un enseignant à partir de son ID
	 * @param pIdEnseignant
	 */
	public void deleteEnseignant(int pIdEnseignant);
	
	/**
	 * Méthode pour récupérer un enseignant via son ID
	 * @param pIdEnseignant
	 * @return
	 */
	public Enseignants getEnseignantById(int pIdEnseignant);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les enseignants
	 * @return
	 */
	public List<Enseignants> getAllEnseignant();
	

}//END INTERFACE
