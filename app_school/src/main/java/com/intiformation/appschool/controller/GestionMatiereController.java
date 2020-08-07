package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.service.IMatiereService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// _________________ METHODES GESTIONNAIRES DU CONTROLLEUR ___________________
	// //

	/**
	 * <pre>
	 * Méthode pour récupérer la liste des matières dans la database via le service
	 * Invoquée via une requete HTTP en GET ayant l'URL:
	 * 
	 * </pre>
	 * 
	 * @param model:
	 *            model de données à renvoyer à la vue
	 * @return
	 */
	@RequestMapping(value = "/matiere/liste", method = RequestMethod.GET)
	public String recupererListeMatieresDB(ModelMap model) {

		// 1. Récupération de la liste des matières dans la databse via le service
		List<Matiere> listeMatieresDB = matiereService.trouverAllMatieres();

		// 2. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", listeMatieresDB);

		// 3. Renvoi de la liste vers la vue

		return "liste-matiere";
	}// end recupererListeMatieresDB

	/**
	 * Permet de supprimer une matière dans la base de données avec son id via le
	 * service
	 * 
	 * @return le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/delete/{matiere-id}", method = RequestMethod.GET)
	public String supprimerMatiereDB(@PathVariable("matiere-id") Long pIdMatiere, ModelMap model) {

		// 1. Suppresion de la matière de la database via le service
		matiereService.supprimerMatiere(pIdMatiere);

		// 2. Récup de la nouvelle liste des employés après suppression
		List<Matiere> listeMatieresDB = matiereService.trouverAllMatieres();

		// 3. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", listeMatieresDB);

		
		// 4 OU : rédirection vers l'url '/employe/liste' pour invoquer la méthode
		// 'recupererListeEmployesDB' et rediriger vers liste-employes.jsp
		return "redirect:/matiere/liste";

	}// end supprimerEmployeDB
	
	/**
	 * <pre>
	 * Permet d'afficher le formulaire d'ajout d'une matiere
	 * Méthode invoquée suite au clic sur le lien '' de liste-matiere.jsp 
	 * Invoquée avec une requete HTTP GET ayant url "/matiere/add-matiere-form"
	 * </pre>
	 * @return
	 */
	@RequestMapping(value = "/matiere/add-matiere-form", method = RequestMethod.GET)
	// @GetMapping
	public ModelAndView afficherFormulaireAjout() {

		// 1. Définition d'un objet de commande pour lier les champs

		// 1.1 L'objet de commande: objet Matiere vide
		Matiere matiere = new Matiere();

		// 1.2 affectation d'un nom à l'objet de commande (par défaut : ... Command)
		String nomObjetCommande = "matiereCommand";

		// 2. Envoi de l'objet de commande vers la vue (page du formulaire)
		Map<String, Object> dataCommand = new HashMap<>();
		dataCommand.put(nomObjetCommande, matiere);

		// 3. Définition du nom logique de la vue
		String viewName = "ajouter-matiere";

		// Renvoie de l'objet ModelAndView vers la dispatcherServle
		return new ModelAndView(viewName, dataCommand);

	}// end afficherFormulaireAjout
	
	/**
	 * <pre>
	 * Méthode qui permet de rajouter una matiere dans la database 
	 * Invoquée au clic sur le lien 'Ajouter' de soumission du formulaire de la page ajouter-matiere.jsp 
	 * Invoquée avec uen requete HTTP en POST et l'URL '/matiere/add' 
	 * Cette méthode récupère l'objet de commande 'matiereCommand' lié au formulaire
	 * </pre>
	 * @param bindingResult: contient le resultat du processe dd la validation
	 * @return : le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/add", method = RequestMethod.POST)
	// @PostMapping
	public String ajouterMatiereDB(@ModelAttribute("matiereCommand") @Validated Matiere pMatiere, 
									ModelMap model, BindingResult resultatValidation) {

		
		// Application du validateur de l'objet pEmploye
		//employeValidator.validate(pEmploye, resultatValidation);
		/*
		// Validation de l'objet
		if (resultatValidation.hasErrors()) {
			
			// CAS 1 : La validation retourne des erreurs : 
			
			// redirection vers la page du formulaire  ajouter-employe.jsp
			
			return "ajouter-employe"; // nom logique de la vue 
			
		}else {
		*/	
			// CAS 2 : La validation ne détecte pas d'erreur
			
			// 1. Ajout de l'employé à la database via couche service
			matiereService.ajouterMatiere(pMatiere);

			// 2. Redirection vers la page 'liste-employes.jsp"

			// 2.1 Récup de la nouvelle liste des employés dans la db
			model.addAttribute("attribut_liste_matieres", matiereService.trouverAllMatieres());

			// 2.2 Redirection

			return "redirect:/matiere/liste";
					
		//}//end else
				
	}// end ajouterMatiereDB
	
	
	/**
	 * Permet d'afficher le formulaire de modification d'un employé Méthode invoquée
	 * suite au clic sur le lien 'Modifier' de liste-employes.jsp Invoquée avec une
	 * requete HTTP GET ayant url "employe/update-employe-form?idemploye=1"
	 * 
	 * @return
	 */

	@RequestMapping(value = "/matiere/update-matiere-form", method = RequestMethod.GET)
	public ModelAndView AfficherFormulaireModification(@RequestParam("idMatiere") Long pIdMatiere) {

		// 1. récup de la matiere à modifier via son id dans la database
		Matiere matiereAModifier = matiereService.trouverMatiereId(pIdMatiere);

		// 2. Définition du modèle de données (objet de commande nommé
	
		/**
		 * 'employeModifCommand" + définition du nom logique de la vue + Ajout du modèle
		 * et du nom dans un objet ModelAndView 
		 * modifer-employe :
		 * /WEB-INF/views/mofidier-employe.jsp
		 */
		return new ModelAndView("modifier-matiere", "matiereModifCommand", matiereAModifier);

	}// end AfficherFormulaireModification
	
	
	/**
	 * Méthode qui permet de modifier une matiere dans la database 
	 * Invoquée au clic
	 * sur le lien 'modifier' de soumission du formulaire de la page
	 * modifier-employe.jsp Invoquée avec uen requete HTTP en POST et l'URL
	 * '/employe/update' Cette méthode récupère l'objet de commande
	 * 'employeModifCommand' lié au formulaire
	 * 
	 * @return : le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/update", method = RequestMethod.POST)
	public String modifierMatiereDB(@ModelAttribute("matiereModifCommand") Matiere pMatiereToUpdate, ModelMap model) {

		// 1. Modif de la matiere dans la database via la couche service
		matiereService.modifierMatiere(pMatiereToUpdate);

		// 2. Récup de la nouvelle liste des employés dans la database
		model.addAttribute("attribut_liste_matieres", matiereService.trouverAllMatieres());

		// 3. Renvoi du nom logique de la vue
		return "liste-matiere";

	}// end modifierEmployeDB()


}// end class
