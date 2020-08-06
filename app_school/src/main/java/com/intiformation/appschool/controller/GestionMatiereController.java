package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.service.IMatiereService;
import java.util.List;

/**
 * Implémentation d'un controleur spring mvc pour la gestion des matières
 * 
 * 
 * @author hannahlevardon
 *
 */
@Controller // déclaration de la classe comme bean Spring de type contrôleur Spring MVC
public class GestionMatiereController {
	
	// _________________ PROPRIETES ___________________ //
	
	// Declaration de la couche Service:
	@Autowired // injectiion du bean dans la propriété 'matiereService' 
	private IMatiereService matiereService;

	/**
	 * Setter de la couche service pour injection pour modificateur de Spring
	 * 
	 * @param matiereService
	 */
	public void setMatiereService(IMatiereService matiereService) {
		this.matiereService = matiereService;
	}
	
	// Declaration du validator:
	
	
	// _________________ METHODES GESTIONNAIRES DU CONTROLLEUR ___________________ //
	/**
	 * <pre>
	 * Méthode pour récupérer la liste des matières dans la database via le service
	 * Invoquée via une requete HTTP en GET ayant l'URL:
	 * 
	 * </pre>
	 * @param model: model de données à renvoyer à la vue 
	 * @return
	 */
	@RequestMapping(value="/matiere/liste", method = RequestMethod.GET)
	public String recupererListeMatieresDB(ModelMap model) {
		
		// 1. Récupération de la liste des matières dans la databse via le service
		List<Matiere> listeMatieresDB = matiereService.trouverAllMatieres() ; 
		
		// 2. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", listeMatieresDB);
		
		// 3. Renvoi de la liste vers la vue 
		
		return "liste-matiere"; 
	}//end recupererListeMatieresDB

}//end class











