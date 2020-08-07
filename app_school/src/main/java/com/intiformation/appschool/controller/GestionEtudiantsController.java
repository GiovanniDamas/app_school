package com.intiformation.appschool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.service.IEtudiantsService;

/**
 * @author giovanni
 *
 */
@Controller
public class GestionEtudiantsController {
	
	// Déclaration de la couche service Etudiants
	@Autowired
	private IEtudiantsService etudiantsService;

	/**
	 * Déclaration du setter de etudiantsService pour l'injection par modificateur
	 * </br>
	 * 
	 * @param etudiantService
	 */
	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}

	/**
	 * Méthode permettant de récupérer la liste des etudiants via le service, Méthode
	 * appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/listeEtudiants", method = RequestMethod.GET)
	public String recupererListeEtudiantBdd(ModelMap model) {

		// 1. recup de la liste des employés dans la bdd via le service

		List<Etudiants> listeEtudiantsBDD = etudiantsService.findAllEtudiant();

		// 2. renvoi de la liste vers la vue via l'objet model

		model.addAttribute("attribut_liste_etudiants", listeEtudiantsBDD);

		// 3. renvoie du nom logique de la vue

		return "listeEtudiants";

	}// END RECUP LISTE

	/**
	 * Méthode pour l'initialisation de l'ajout d'étudiant Cette méthode permet
	 * d'afficher le formulaire permettant l'ajout d'un nouvel Etudiant </br>
	 * Appelée via une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/form-edit", method = RequestMethod.GET)
	public String afficherFormulaireEdition(@RequestParam("idPersonne") Long pIdEtudiant, ModelMap model) {

		if (pIdEtudiant == 0) {

			// définition d'un objet de commande

			Etudiants etudiant = new Etudiants();

			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_etudiants", etudiant);
			model.addAttribute("idPersonne", pIdEtudiant);

		} else if (pIdEtudiant != 0) {

			// Récup de l'étudiant à récup

			Etudiants etudiantToUpdate = etudiantsService.findEtudiantById(pIdEtudiant);

			// Renvoi de l'objet vers la vue

			model.addAttribute("attribut_etudiants", etudiantToUpdate);
			model.addAttribute("idPersonne", pIdEtudiant);

		} // END IF ELSE IF

		return "formulaireEditionEtudiants";

	}// END METHODE

	/**
	 * Méthode permettant d'ajouter l'étudiant à la bdd après soumission du
	 * formulaire </br>
	 * Appelée via une requête HTTP de type POST
	 * 
	 * @param pEtudiant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/edit", method = RequestMethod.POST)
	public String ajoutEtudiantBdd(@ModelAttribute("attribut_etudiants") Etudiants pEtudiant, ModelMap model) {
		
		if (pEtudiant.getIdPersonne() == null) {

			// Ajout etudiant via couche service

			etudiantsService.ajouterEtudiant(pEtudiant);

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());
			
			return "listeEtudiants";

		}
		if (pEtudiant.getIdPersonne() != 0) {			

			// Modif etudiant via couche service

			etudiantsService.modifierEtudiant(pEtudiant);

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

			return "listeEtudiants";
		
		} // END IF

		return "listeEtudiants";

	}// END METHODE

	/**
	 * Méthode permettant de supprimer l'étudiant de la bdd Appelée via une requête
	 * HTTP de type GET
	 * 
	 * @param pIdEtudiant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/delete", method = RequestMethod.GET)
	public String supprimerEtudiantsBDD(@RequestParam("idPersonne") Long pIdEtudiant, ModelMap model) {

		// Récup de l'étudiant à supprimer

		etudiantsService.suppEtudiant(pIdEtudiant);

		// Récup nouvelle liste et envoie vers vue

		model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

		return "listeEtudiants";

	}// END SUPPRIMER

	@InitBinder
	public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	}// END Init Binder

}// END CLASS
