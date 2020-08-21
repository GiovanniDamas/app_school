package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Adresse;
import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAdresseService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantMatierePromotionLinkService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;
import com.intiformation.appschool.service.IPromotionService;
import com.intiformation.appschool.validator.LinkValidator;


/**
 * 
 * @author gabri
 *
 */
@Controller
public class GestionEspacePerso {

	@Autowired
	private IEnseignantsService enseignantService;

	@Autowired
	private IEtudiantsService etudiantService;
	
	@Autowired
	private IAdministrateursService administrateursService;
	
	@Autowired
	private IAideService aideService;
	@Autowired
	private IAdresseService adresseService;
	
	/**
	 * 
	 * Setter de la couche service pour injection pour modificateur de Spring
	 * 
	 * @param promotionService
	*/

	public void setEnseignantService(IEnseignantsService enseignantService) {
		this.enseignantService = enseignantService;
	}

	public void setEtudiantService(IEtudiantsService etudiantService) {
		this.etudiantService = etudiantService;
	}

	public void setAdministrateursService(IAdministrateursService administrateursService) {
		this.administrateursService = administrateursService;
	}
	
	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}

	public void setAdresseService(IAdresseService adresseService) {
		this.adresseService = adresseService;
	}

	
	//déclaration WelcomeController pour recup infos personne connectée
	@Autowired
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}	
	
	// _________________ METHODES GESTIONNAIRES DU CONTROLLEUR ___________________



	/**
	 * méthode qui permet de récupérer les informations de la personne connectée
	 * 
	 * @param authentication
	 * @return
	 */
	public Personnes getInfosPersonneConnecte(Authentication authentication) {

		Personnes personneConnecte = null;
		
		if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {

			// 1. cas d'un admin : récupération de l'administrateur connecté
			personneConnecte = administrateursService.findAdministrateurByIdentifiant(authentication.getName());

		} else if (authentication.getAuthorities().toString().contains("ROLE_ENSEIGNANT")) {

			// 1. cas d'un enseignant : récupération de l'enseignant connecté
			personneConnecte = enseignantService.findEnseignantByIdentifiant(authentication.getName());

		} else if (authentication.getAuthorities().toString().contains("ROLE_ETUDIANT")) {

			// 1. cas d'un etudiant : récupération de l'eutidnat connecté
			personneConnecte = etudiantService.findEtudiantByIdentifiant(authentication.getName());
		}


		return personneConnecte;
	}//end getInfosPersonneConnecte
	
	
	/**
	 * 
	 * @param pIdEtudiant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionCompte/compte", method = RequestMethod.GET)
	public String afficherInfoCompte( ModelMap model, Authentication authentication) {

		// Récup de la personne connectée
		Personnes personne = getInfosPersonneConnecte(authentication);

		// Renvoi de l'objet vers la vue
		model.addAttribute("attribut_personne_connecte", personne);
		//model.addAttribute("photo", etudiantToUpdate.getPhoto());

		model.addAttribute("attribut_adresses", adresseService.findAdresseByPersonne(personne));
		
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("compte");
		model.addAttribute("attribut_help", aideDeLaPage);	
		
		return "compte/mon-compte";

	}//end 	afficherInfoCompte
	
	
	
	
	@RequestMapping(value = "/compte/editAdresse", method = RequestMethod.GET)
	public String afficherFormulaireEdition(@RequestParam("idAdresse") Long pIdAdresse, ModelMap model, Authentication authentication) {


		if (pIdAdresse == 0) {

			// définition d'un objet de commande

			Adresse adresse = new Adresse();
			
			
			
			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_adresse", adresse);
			model.addAttribute("idAdresse", pIdAdresse);
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		} else if (pIdAdresse != 0) {

			// Récup de l'aiide à récup

			Adresse adresseToUpdate = adresseService.findAdresseById(pIdAdresse);

			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_adresse", adresseToUpdate);
			model.addAttribute("idAdresse", pIdAdresse);
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		} //end else

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("edit-adresse");
		model.addAttribute("attribut_help", aideDeLaPage);			
		
		return "compte/edit-adresse-compte";

	}//end afficherFormulaireEdition
	
	
	
	@RequestMapping(value = "/compte/editAdresse", method = RequestMethod.POST)
	public String ajoutAdresseBdd(@ModelAttribute("attribut_adresse") Adresse pAdresse, ModelMap model, Authentication authentication) {
		
		
		Personnes proprio = welcomeController.getInfosPersonneConnecte(authentication);
		model.addAttribute("attribut_personne_connecte", proprio);
		
		pAdresse.setPersonne(proprio);
		
		if (pAdresse.getIdAdresse() == null) {
				
			
			// Ajout adresse via couche service
			adresseService.ajouterAdresse(pAdresse);

		}
		else {			

			// Modif etudiant via couche service
			adresseService.modifierAdresse(pAdresse);

		}//end if

		// redirection => necessité params
		return "redirect:/gestionCompte/compte";

	}//end ajoutAdresseBdd	
	
	@RequestMapping(value = "/compte/deleteAdresse", method = RequestMethod.GET)
	public String supprimerAdresseBDD(@RequestParam("idAdresse") Long pIdAdresse, ModelMap model, Authentication authentication) {

		// Récup de l'aide à supprimer

		adresseService.supprimerAdresse(pIdAdresse);

		model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		//// ====> ATTENTION NECESSITE PARAM POUR LE GET
		return "redirect:/gestionCompte/compte";

	}//end supprimerAdresseBDD		
	
}//end class
