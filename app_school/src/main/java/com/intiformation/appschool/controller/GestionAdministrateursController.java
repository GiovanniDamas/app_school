package com.intiformation.appschool.controller;



import com.intiformation.appschool.service.IEnseignantsService;

/**
 * @author giovanni
 *
 */

public class GestionAdministrateursController {
	
	private IEnseignantsService enseignantService;

	/**
	 * Déclaration du setter de EnseignantService pour l'injection par modificateur </br>
	 * @param enseignantService
	 */
	public void setEnseignantService(IEnseignantsService enseignantService) {
		this.enseignantService = enseignantService;	
	}
	
	

}
