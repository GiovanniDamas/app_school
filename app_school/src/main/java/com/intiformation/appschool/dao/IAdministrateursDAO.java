package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Administrateurs;
import com.intiformation.appschool.modeles.Enseignants;

/**
 * Interface de la couche DAO pour les administrateurs
 * @author giovanni
 *
 */
public interface IAdministrateursDAO {
	
	/**
	 * Méthode permettant d'ajouter un administrateur dans la BDD
	 * @param pAdministrateur
	 */
	public void addAdministrateur(Administrateurs pAdministrateur);
	
	/**
	 * Méthode pour modifier les données d'un administrateur
	 * @param pAdministrateur
	 */
	public void updateAdministrateur(Administrateurs pAdministrateur);
	
	/**
	 * Méthode pour supprimer un administrateur à partir de son ID
	 * @param pIdAdministrateur
	 */
	public void deleteAdministrateur(Long pIdAdministrateur);
	
	/**
	 * Méthode pour récupérer un administrateur via son ID
	 * @param pIdAdministrateur
	 * @return
	 */
	public Administrateurs getAdministrateurById(Long pIdAdministrateur);
	
	/**
	 * Méthode permettant la récupération de la liste de tous les administrateurs </br>
	 * @return
	 */
	public List<Administrateurs> getAllAdministrateur();

	/*____________________________________________________________________________________________________________*/
	public Administrateurs getAdministrateurByIdentifiant(String pIdentifiant);

}//END INTERFACE
