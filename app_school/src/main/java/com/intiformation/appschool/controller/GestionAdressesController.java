package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	private Personnes propriétaire;
	
	public Personnes getPropriétaire() {
		return propriétaire;
	}

	public void setPropriétaire(Personnes propriétaire) {
		this.propriétaire = propriétaire;
	}

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
		
		System.out.println(pRole);
		System.out.println(pRole.contains("ROLE_ETUDIANT"));
		
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
	 * Méthode permettant de récupérer la liste des adresses d'une personne via le service.
	 *  Méthode appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adresses/listeAdresses", method = RequestMethod.GET)
	public String recupererListeAdressePersonneBdd(@RequestParam("idPersonne") Long pIdPersonne,@RequestParam("role") String pRole, ModelMap model, Authentication authentication) {

		
		// 0. renvoi de la personne associé à cette adresse:
		propriétaire = findPersonneByRole(pIdPersonne,pRole);
		model.addAttribute("attribut_proprio", propriétaire );	
		
		// 1. recup de la liste des employés dans la bdd via le service
		List<Adresse> listeAdresseByPersonneBDD = adresseService.findAdresseByPersonne(propriétaire);

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
	
	/**
	 * Méthode pour l'initialisation de l'ajout d'une adresse Cette méthode permet
	 * d'afficher le formulaire permettant l'ajout d'un nouvel Etudiant </br>
	 * Appelée via une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adresses/editAdresse", method = RequestMethod.GET)
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
		
		return "edit-adresse";

	}//end afficherFormulaireEdition	
	
	
	/**
	 * Méthode permettant d'ajouter l'adresse à la bdd après soumission du
	 * formulaire </br>
	 * Appelée via une requête HTTP de type POST
	 * 
	 * @param pAide
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adresses/edit", method = RequestMethod.POST)
	public String ajoutAdresseBdd(@ModelAttribute("attribut_adresse") Adresse pAdresse, ModelMap model, Authentication authentication) {
		
		pAdresse.setPersonne(propriétaire);
		
		if (pAdresse.getIdAdresse() == null) {

		
				
			// Ajout adresse via couche service
			adresseService.ajouterAdresse(pAdresse);


		}
		else {			

			// Modif etudiant via couche service
			adresseService.modifierAdresse(pAdresse);

		}//end if

		// redirection => necessité params
		return "redirect:/adresses/listeAdresses?idPersonne="+ propriétaire.getIdPersonne() + "&role="+propriétaire.getRole();

	}//end ajoutAdresseBdd

	/**
	 * Méthode permettant de supprimer l'adresse de la bdd Appelée via une requête
	 * HTTP de type GET
	 * 
	 * @param pIdAide
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/adresses/delete", method = RequestMethod.GET)
	public String supprimerAdresseBDD(@RequestParam("idAdresse") Long pIdAdresse, ModelMap model, Authentication authentication) {

		// Récup de l'aide à supprimer

		adresseService.supprimerAdresse(pIdAdresse);

		model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		//// ====> ATTENTION NECESSITE PARAM POUR LE GET
		return "redirect:/adresses/listeAdresses?idPersonne="+ propriétaire.getIdPersonne() + "&role="+propriétaire.getRole();

	}//end supprimerAdresseBDD	
	
	
}//end class
