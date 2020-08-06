package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.intiformation.appschool.modeles.Administrateurs;
import com.intiformation.appschool.service.IAdministrateursService;


/**
 * @author giovanni
 *
 */

public class GestionAdministrateursController {


	// Déclaration de la couche service Administrateur


	private IAdministrateursService adminService;

	/**
	 * Déclaration du setter de adminService pour l'injection par modificateur
	 * </br>
	 * 
	 * @param enseignantService
	 */
	public void setAdminService(IAdministrateursService adminService) {
		this.adminService = adminService;
	}
	
	
	/**
	 * Méthode permettant de récupérer la liste des admins via le service,
	 * Méthode appellée lors d'une requête HTTP de type GET
	 * @param model
	 * @return
	 */

	public String recupererListeAdminBdd(ModelMap model) {

		// 1. recup de la liste des employés dans la bdd via le service
		
		List<Administrateurs> listeAdminBDD = adminService.findAllAdministrateur();

		// 2. renvoi de la liste vers la vue via l'objet model
		
		model.addAttribute("attribut_liste_admin", listeAdminBDD );

		// 3. renvoie du nom logique de la vue
		
		return "liste-admin";

	}//END RECUP LISTE
	

	
	

}//END CLASS
