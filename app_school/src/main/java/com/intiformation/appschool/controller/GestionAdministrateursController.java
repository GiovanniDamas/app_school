package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Administrateurs;
import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.service.IAdministrateursService;

/**
 * @author giovanni
 *
 */
@Controller
public class GestionAdministrateursController {

	// Déclaration de la couche service Administrateur
	@Autowired
	private IAdministrateursService adminService;

	/**
	 * Déclaration du setter de adminService pour l'injection par modificateur </br>
	 * 
	 * @param enseignantService
	 */
	public void setAdminService(IAdministrateursService adminService) {
		this.adminService = adminService;
	}

	/**
	 * Méthode permettant de récupérer la liste des admins via le service, Méthode
	 * appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionAdmin/listeAdministrateurs", method = RequestMethod.GET)
	public String recupererListeAdminBdd(ModelMap model) {

		// 1. recup de la liste des employés dans la bdd via le service

		List<Administrateurs> listeAdminBDD = adminService.findAllAdministrateur();

		// 2. renvoi de la liste vers la vue via l'objet model

		model.addAttribute("attribut_liste_admin", listeAdminBDD);

		// 3. renvoie du nom logique de la vue

		return "Personnels/listeAdministrateurs";

	}// END RECUP LISTE

	/**
	 * Méthode pour l'initialisation de l'ajout d'un administrateur. Cette méthode
	 * permet d'afficher le formulaire permettant l'ajout d'un nouvel Etudiant </br>
	 * Appelée via une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionAdmin/form-edit", method = RequestMethod.GET)
	public String afficherFormulaireEdition(@RequestParam("idPersonne") Long pIdAdmin, ModelMap model) {

		if (pIdAdmin == 0) {

			// définition d'un objet de commande

			Administrateurs administrateur = new Administrateurs();

			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_administrateurs", administrateur);
			model.addAttribute("idPersonne", pIdAdmin);

		} else if (pIdAdmin != 0) {

			// Récup de l'enseignant à modif

			Administrateurs adminToUpdate = adminService.findAdministrateurById(pIdAdmin);

			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_administrateurs", adminToUpdate);
			model.addAttribute("idPersonne", pIdAdmin);

		} // END IF ELSE IF

		return "Personnels/formulaireEditionAdministrateurs";

	}// END METHODE

	/**
	 * Méthode permettant d'ajouter l'administrateurs à la bdd après soumission du
	 * formulaire </br>
	 * Appelée via une requête HTTP de type POST
	 * @param pEnseignant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionAdmin/edit", method = RequestMethod.POST)
	public String ajoutAdminBdd(@ModelAttribute("attribut_administrateurs") Administrateurs pAdmin, ModelMap model) {

		if (pAdmin.getIdPersonne() == null) {
			
			//recup mdp 
			String MDP = pAdmin.getMotDePasse();
			
			// objet pour le  cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			// cryptage du mot de passe avec la méthode encode()
			String hashedMotDePasse = passwordEncoder.encode(MDP);
			
			// passage du mdp crypté
			pAdmin.setMotDePasse(hashedMotDePasse);
			
			// passage du role 
			pAdmin.setRole("ROLE_ADMIN");
			
			// Ajout enseignant via couche service

			adminService.ajouterAdministrateur(pAdmin);

			// Recup nouvelle liste d'enseignant après ajout

			model.addAttribute("attribut_liste_admin", adminService.findAllAdministrateur());

			return "Personnels/listeAdministrateurs";

		}
		if (pAdmin.getIdPersonne() != 0) {
			
			//recup mdp 
			String MDP = pAdmin.getMotDePasse();
			
			// objet pour le  cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
			// cryptage du mot de passe avec la méthode encode()
			String hashedMotDePasse = passwordEncoder.encode(MDP);
			
			// passage du mdp crypté
			pAdmin.setMotDePasse(hashedMotDePasse);
			
			// passage du role 
			pAdmin.setRole("ROLE_ADMIN");
			
			// Modif enseignant via couche service

			adminService.modifierAdministrateur(pAdmin);

			// Recup nouvelle liste d'enseignants après ajout

			model.addAttribute("attribut_liste_admin", adminService.findAllAdministrateur());

			return "Personnels/listeAdministrateurs";

		} // END IF

		return "Personnels/listeAdministrateurs";

	}// END METHODE

	/**
	 * Méthode permettant de supprimer l'administrateur de la bdd. 
	 * Appelée via une requête HTTP de type GET 
	 * @param pIdEnseignant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionAdmin/delete", method = RequestMethod.GET)
	public String supprimerEnseignantBDD(@RequestParam("idPersonne") Long pIdAdmin, ModelMap model) {

		// Récup de l'enseignant à supprimer

		adminService.suppAdministrateur(pIdAdmin);

		// Récup nouvelle liste et envoie vers vue

		model.addAttribute("attribut_liste_admin", adminService.findAllAdministrateur());

		return "Personnels/listeAdministrateurs";

	}// END SUPPRIMER

}// END CLASS
