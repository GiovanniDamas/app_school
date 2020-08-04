package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Administrateurs;

/**
 * Interface pour la couche service pour les administrateurs
 * @author giovanni
 *
 */
public interface IAdministrateursService {
	
	/**
	 * Méthode permettant d'ajouter un administrateur dans la BDD
	 * @param pAdministrateur
	 */
	public void ajouterAdministrateur(Administrateurs pAdministrateur);
	
	/**
	 * Méthode pour modifier les données d'un administrateur
	 * @param pAdministrateur
	 */
	public void modifierAdministrateur(Administrateurs pAdministrateur);
	
	/**
	 * Méthode pour supprimer un administrateur à partir de son ID
	 * @param pIdAdministrateur
	 */
	public void suppAdministrateur(int pIdAdministrateur);
	
	/**
	 * Méthode pour récupérer un administrateur via son ID
	 * @param pIdAdministrateur
	 * @return
	 */
	public Administrateurs findAdministrateurById(int pIdAdministrateur);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les administrateurs </br>
	 * @return
	 */
	public List<Administrateurs> findAllAdministrateur();

}//END INTERFACE
