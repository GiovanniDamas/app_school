package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.validator.EnseignantValidator;

/**
 * @author giovanni
 *
 */
@Controller
public class GestionEnseignantsController {

	// Déclaration de la couche service Enseignants
	@Autowired
	private IEnseignantsService enseignantsService;

	/**
	 * Déclaration du setter de enseignantsService pour l'injection par modificateur
	 * </br>
	 * 
	 * @param enseignantService
	 */
	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}

	// délcaration du validator de l'enseignant
	@Autowired
	private EnseignantValidator enseignantValidator;

	/**
	 * déclaration du setter du validator pour injection par modificateur
	 * 
	 * @param enseignantValidator
	 */
	public void setEnseignantValidator(EnseignantValidator enseignantValidator) {
		this.enseignantValidator = enseignantValidator;
	}
	
	//déclaration WelcomeController pour recup infos personne connectée
	@Autowired
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}

	/**
	 * Méthode permettant de récupérer la liste des enseignants via le service,
	 * Méthode </br>
	 * appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEnseignants/listeEnseignants", method = RequestMethod.GET)
	public String recupererListeEnseignantsBDD(ModelMap model, Authentication authentication) {

		// 1. recup de la liste des enseignants dans la bdd via le service

		List<Enseignants> listeEnseignantsBDD = enseignantsService.findAllEnseignant();

		// 2. renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));
		model.addAttribute("attribut_liste_enseignants", listeEnseignantsBDD);

		// 3. renvoie du nom logique de la vue

		return "personnels/listeEnseignants";

	}// END RECUP LISTE

	/**
	 * Méthode pour l'initialisation de l'ajout d'un enseignant. Cette méthode
	 * permet </br>
	 * d'afficher le formulaire permettant l'ajout d'un nouvel Etudiant </br>
	 * Appelée via une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEnseignants/form-edit", method = RequestMethod.GET)
	public String afficherFormulaireEdition(@RequestParam("idPersonne") Long pIdEnseignant, ModelMap model, Authentication authentication) {

		System.out.println("methode form :" + pIdEnseignant);
		if (pIdEnseignant == 0) {

			// définition d'un objet de commande

			Enseignants enseignant = new Enseignants();

			// Renvoi de l'objet vers la vue
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));
			model.addAttribute("attribut_enseignants", enseignant);
			model.addAttribute("idPersonne", pIdEnseignant);

		} else if (pIdEnseignant != 0) {

			// Récup de l'enseignant à modif

			Enseignants enseignantToUpdate = enseignantsService.findEnseignantById(pIdEnseignant);

			// Renvoi de l'objet vers la vue
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));
			model.addAttribute("attribut_enseignants", enseignantToUpdate);
			model.addAttribute("idPersonne", pIdEnseignant);

		} // END IF ELSE IF

		return "personnels/formulaireEditionEnseignants";

	}// END METHODE

	/**
	 * Méthode permettant d'ajouter l'enseignant à la bdd après soumission du
	 * formulaire </br>
	 * Appelée via une requête HTTP de type POST
	 * 
	 * @param pEnseignant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEnseignants/edit", method = RequestMethod.POST)
	public String ajoutEnseignantBdd(@ModelAttribute("attribut_enseignants") Enseignants pEnseignant, ModelMap model,
			BindingResult result, Authentication authentication) {

		// appel du validator
		enseignantValidator.validate(pEnseignant, result);

		if (result.hasErrors()) {

			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			// renvoie du formulaire

			return "personnels/formulaireEditionEnseignants";

		} else if (pEnseignant.getIdPersonne() == null) {

			// recup mdp
			String MDP = pEnseignant.getMotDePasse();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mot de passe avec la méthode encode()
			String hashedMotDePasse = passwordEncoder.encode(MDP);

			// passage du mdp crypté
			pEnseignant.setMotDePasse(hashedMotDePasse);

			// passage du role
			pEnseignant.setRole("ROLE_ENSEIGNANT");

			// Ajout enseignant via couche service

			enseignantsService.ajouterEnseignant(pEnseignant);

			// Recup nouvelle liste d'enseignant après ajout

			model.addAttribute("attribut_liste_enseignants", enseignantsService.findAllEnseignant());
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			return "personnels/listeEnseignants";

		} // END IF id == null

		if (result.hasErrors()) {

			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			// renvoie du formulaire

			return "personnels/formulaireEditionEnseignants";

		} else if (pEnseignant.getIdPersonne() != 0) {

			// recup mdp
			String MDP = pEnseignant.getMotDePasse();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mot de passe avec la méthode encode()
			String hashedMotDePasse = passwordEncoder.encode(MDP);

			// passage du mdp crypté
			pEnseignant.setMotDePasse(hashedMotDePasse);

			// passage du role
			pEnseignant.setRole("ROLE_ENSEIGNANT");

			// Modif enseignant via couche service

			enseignantsService.modifierEnseignant(pEnseignant);

			// Recup nouvelle liste d'enseignants après ajout

			model.addAttribute("attribut_liste_enseignants", enseignantsService.findAllEnseignant());
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			return "personnels/listeEnseignants";

		} // END IF

		return "personnels/listeEnseignants";

	}// END METHODE

	/**
	 * Méthode permettant de supprimer l'enseignant de la bdd. Appelée via une
	 * requête HTTP de type GET
	 * 
	 * @param pIdEnseignant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEnseignants/delete", method = RequestMethod.GET)
	public String supprimerEnseignantBDD(@RequestParam("idPersonne") Long pIdEnseignant, ModelMap model, Authentication authentication) {

		// Récup de l'enseignant à supprimer

		enseignantsService.suppEnseignant(pIdEnseignant);

		// Récup nouvelle liste et envoie vers vue

		model.addAttribute("attribut_liste_enseignants", enseignantsService.findAllEnseignant());
		model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

		return "personnels/listeEnseignants";

	}// END SUPPRIMER

}// END CLASS
