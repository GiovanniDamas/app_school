package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
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


		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("compte");
		model.addAttribute("attribut_help", aideDeLaPage);	
		
		return "mon-compte";

	}//end 	afficherInfoCompte
	

}//end class
