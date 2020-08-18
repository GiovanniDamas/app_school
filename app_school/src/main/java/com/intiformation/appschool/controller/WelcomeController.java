package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;

/**
 * controleur spring MVC pour la page d'accueil
 * @author marle
 *
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {

	//___ déclaration du service de enseignant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEnseignantsService enseignantsService;

	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}

	//____ déclaration du service de administrateur avec setter pour injection spring
	@Autowired //injection par modificateur
	private IAdministrateursService administrateursService;

	public void setAdministrateursService(IAdministrateursService administrateursService) {
		this.administrateursService = administrateursService;
	}

	//___ déclaration du service de etudiant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEtudiantsService etudiantsService;

	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}


	
	
	// Declaration de la couche Service:
	@Autowired // injectiion du bean dans la propriété 'matiereService'
	private IAideService aideService;

	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}//end setter
	
	
	
	
	
	/**
	 * méthode qui permet de récupérer les informations de la personne connectée
	 * @param authentication
	 * @return
	 */
	public Personnes getInfosPersonneConnecte(Authentication authentication) {
		
		Personnes personneConnecte = null;
		
		if(authentication == null) {
			
			personneConnecte = null;
			
		} else if (authentication != null) {
			
			if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
				
				//1. cas d'un admin : récupération de l'administrateur connecté
				personneConnecte = administrateursService.findAdministrateurByIdentifiant(authentication.getName());
		
			} else if (authentication.getAuthorities().toString().contains("ROLE_ENSEIGNANT")) {
				
				//1. cas d'un enseignant : récupération de l'enseignant connecté
				personneConnecte = enseignantsService.findEnseignantByIdentifiant(authentication.getName());
				
			} else if (authentication.getAuthorities().toString().contains("ROLE_ETUDIANT")) {
				
				//1. cas d'un etudiant : récupération de l'eutidnat connecté
				personneConnecte = etudiantsService.findEtudiantByIdentifiant(authentication.getName());
			}//end else if
			
		}//end else if
		
		return personneConnecte;
		
	}//end getInfosPersonneConnecte
	
	
	/*=================================================================*/
	/*======================= méthodes gestionnaires ==================*/
	/*=================================================================*/

	/**
	 * permet d'afficher la page d'accueil en récupérant les infos de la personne connectée
	 * @param model
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public String welcomeMethod(ModelMap model, Authentication authentication) {
		
		//1. récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		//2. renvoi de la personne connectée vers la vue 
		model.addAttribute("attribut_personne_connecte", personneConnecte);

		// 3. recup de l'aide associée a la page
		Aide aideDeLaPage = aideService.findAideByURL("index");
		System.out.println("\n\t> aide de la page : " + aideDeLaPage);
		System.out.println("\n\t> aide not null : " + (aideDeLaPage != null ) );

		model.addAttribute("attribut_help", aideDeLaPage);
		
		
		//4. renvoi du nom logique de la vue
		return "accueil";
		
	}//end welcomeMethod
	
	
	
}//end class
