package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Etudiants;

/**
 * Interface générique de la couche DAO 
 * @author giovanni
 *
 */
public interface IEtudiantsDAO {
	
	/**
	 * Méthode permettant d'ajouter un étudiant dans la BDD
	 * @param pEtudiant
	 */
	public void addEtudiant(Etudiants pEtudiant);
	
	/**
	 * Méthode pour modifier les données d'un étudiant
	 * @param pEtudiant
	 */
	public void updateEtudiant(Etudiants pEtudiant);
	
	/**
	 * Méthode pour supprimer un étudiant à partir de son ID
	 * @param pIdEtudiant
	 */
	public void deleteEtudiant(int pIdEtudiant);
	
	/**
	 * Méthode pour récupérer un étudiant via son ID
	 * @param pIdEtudiant
	 * @return
	 */
	public Etudiants getEtudiantById(int pIdEtudiant);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les étudiants
	 * @return
	 */
	public List<Etudiants> getAllEtudiant();
	

}//END INTERFACE
