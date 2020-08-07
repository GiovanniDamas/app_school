package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.intiformation.appschool.service.IAideService;


@Controller
public class GestionAideService {
	
	// _________________ PROPRIETES ___________________ //

		// Declaration de la couche Service:
		@Autowired // injectiion du bean dans la propriété 'matiereService'
		private IAideService aideService;


		/**
		 * Setter de la couche service pour injection pour modificateur de Spring
		 * 
		 * @param aideService
		 */
		
		public void setAideService(IAideService aideService) {
			this.aideService = aideService;
		}//end setter

	
		

}//end classe
