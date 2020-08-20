package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.dao.IEtudiantsDAO;
import com.intiformation.appschool.modeles.Adresse;
import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAdresseService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;

@Controller
public class GestionAdressesController {

	// _________________ PROPRIETES ___________________ //

	// Declaration de la couche Service:
	@Autowired // injectiion du bean dans la propriété 'matiereService'
	private IAideService aideService;
	
	@Autowired
	private IAdresseService adresseService;
	@Autowired
	private IEtudiantsService etudiantService;
	@Autowired
	private IEnseignantsService enseignantService;
	@Autowired
	private IAdministrateursService adminService;
	
	/**
	 * Setter de la couche service pour injection pour modificateur de Spring
	 * 
	 * @param aideService
	 */
	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}//end setter	
			
	public void setAdresseService(IAdresseService adresseService) {
		this.adresseService = adresseService;
	}
	public void setEtudiantService(IEtudiantsService etudiantService) {
		this.etudiantService = etudiantService;
	}

	public void setEnseignantService(IEnseignantsService enseignantService) {
		this.enseignantService = enseignantService;
	}

	public void setAdminService(IAdministrateursService adminService) {
		this.adminService = adminService;
	}



	//déclaration WelcomeController pour recup infos personne connectée
	@Autowired
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}	
	
	/*=================================================================*/
	/*======================= méthodes gestionnaires ==================*/
	/*=================================================================*/
	
	public Personnes findPersonneByRole(Long pIdPersonne, String pRole) {
		
		Personnes personne = null;
		
		if (pRole.contains("ROLE_ADMIN") ) {
				
			//1. cas d'un admin : récupération de l'administrateur connecté
			personne = adminService.findAdministrateurById(pIdPersonne);
		
		} else if (pRole.contains("ROLE_ENSEIGNANT")) {
			
			//1. cas d'un enseignant : récupération de l'enseignant connecté
			personne = enseignantService.findEnseignantById(pIdPersonne);
				
		} else if (pRole.contains("ROLE_ETUDIANT") ) {
				
			//1. cas d'un etudiant : récupération de l'eutidnat connecté
			personne = etudiantService.findEtudiantById(pIdPersonne);
		
		}//end else if

		return personne;
	}
	
	/**
	 * Méthode permettant de récupérer la liste des etudiants via le service, Méthode
	 * appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adresses/listeAdresses", method = RequestMethod.GET)
	public String recupererListeAdressePersonneBdd(@RequestParam("idPersonne") Long pIdPersonne,@RequestParam("role") String pRole, ModelMap model, Authentication authentication) {

		
		// 0. renvoi de la personne associé à cette adresse:
		Personnes personne = findPersonneByRole(pIdPersonne,pRole);
		model.addAttribute("attribut_proprio", personne );	
		
		// 1. recup de la liste des employés dans la bdd via le service
		List<Adresse> listeAdresseByPersonneBDD = adresseService.findAdresseByPersonne(personne);

		// 2. renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_adresses", listeAdresseByPersonneBDD);
	
		// 3. Recup de l'aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("adresses");
		model.addAttribute("attribut_help", aideDeLaPage);
		
		// 4. Recup des infos de la personne Co
		model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		// 5. renvoie du nom logique de la vue			
		return "liste-adresses";

	}//end recupererListeAdressePersonneBdd	
	
	
}//end class
