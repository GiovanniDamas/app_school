package com.intiformation.appschool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;
import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.modeles.Promotion;
import com.intiformation.appschool.service.IEnseignantMatierePromotionLinkService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IPromotionService;


/**
 * Implémentation d'un controleur spring mvc pour la gestion des promotions
 * 
 * 
 * @author hannahlevardon
 *
 */
@Controller // déclaration de la classe comme bean Spring de type contrôleur Spring MVC
public class GestionPromotionController {
	
	// _________________ PROPRIETES ___________________ //

	// Declaration de la couche Service:
	@Autowired // injectiion du bean dans la propriété 'matiereService'
	private IPromotionService promotionService;
	@Autowired
	private IEnseignantsService enseignantService; 
	@Autowired
	private IEnseignantMatierePromotionLinkService enseignantMatierePromotionLinkService;



	/**
	 * 
	 * Setter de la couche service pour injection pour modificateur de Spring
	 * 
	 * @param promotionService
	 */
	public void setPromotionService(IPromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	public void setEnseignantService(IEnseignantsService enseignantService) {
		this.enseignantService = enseignantService;
	}
	

	public void setEnseignantMatierePromotionLinkService(IEnseignantMatierePromotionLinkService enseignantMatierePromotionLinkService) {
		this.enseignantMatierePromotionLinkService = enseignantMatierePromotionLinkService;
	}
	
	// Declaration du validator:


	// _________________ METHODES GESTIONNAIRES DU CONTROLLEUR ___________________

	
	/**
	 * <pre>
	 * Méthode pour récupérer la liste des promotions dans la database via le service
	 * Invoquée via une requete HTTP en GET ayant l'URL:
	 * 
	 * </pre>
	 * 
	 * @param model:
	 *            model de données à renvoyer à la vue
	 * @return
	 */
	@RequestMapping(value = "/promotion/liste-promotion", method = RequestMethod.GET)
	public String recupererListePromotionsDB(ModelMap model) {

		// 1. Récupération de la liste des matières dans la databse via le service
		List<Promotion> listePromotionsDB = promotionService.trouverAllPromotions();

		// 2. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_promotions", listePromotionsDB);

		// 3. Renvoi de la liste vers la vue

		return "Promotions/liste-promotion";
	}// end recupererListePromotionsDB
	
	
	/**
	 * Permet de supprimer une promotion dans la base de données avec son id via le
	 * service
	 * 
	 * @return le nom logique de la vue
	 */
	@RequestMapping(value = "promotion/delete", method = RequestMethod.GET)
	public String supprimerPromotionDB(@RequestParam("idPromotion") Long pIdPromotion, ModelMap model) {

		// 1. Suppresion de la matière de la database via le service
		promotionService.supprimerPromotion(pIdPromotion);

		// 2. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", promotionService.trouverAllPromotions());

		// 3 OU : rédirection vers l'url '/employe/liste' pour invoquer la méthode
		// 'recupererListeEmployesDB' et rediriger vers liste-employes.jsp
		return "redirect:/promotion/liste-promotion";

	}// end supprimerPromotionDB

	
	/**
	 * <pre>
	 * Permet d'afficher le formulaire d'ajout ou de modification d'une promotion
	 * Méthode invoquée suite au clic sur le lien 'ajouter une promotion' de liste-promotion.jsp 
	 * Invoquée avec une requete HTTP GET ayant url "/promotion/edit-promotion-form"
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/promotion/edit-promotion-form", method = RequestMethod.GET)
	public ModelAndView afficherFormulaire(@RequestParam("idPromotion") Long pIdPromotion, ModelMap model) {

		if (pIdPromotion == 0) {

			Promotion promotion = new Promotion();
			
			EnseignantMatierePromotionLink enseignantMatierePromotionLink =new EnseignantMatierePromotionLink();
			
			
			
			// ajout liste des enseignants
			List<Enseignants> listeEnseignantsDB = enseignantService.findAllEnseignant(); 
			model.addAttribute("attribut_liste_enseignants", listeEnseignantsDB);
		
			

			Map<String, Object> dataCommand = new HashMap<>();
			dataCommand.put("promotionCommand", promotion);
			dataCommand.put("linkCommand", enseignantMatierePromotionLink);

			String viewName = "Promotions/promotion-formulaire";

			return new ModelAndView(viewName, dataCommand);

		} else {

			Promotion promotionToUpdate = promotionService.trouverPromotionId(pIdPromotion);

			return new ModelAndView("Promotions/promotion-formulaire", "promotionCommand", promotionToUpdate );

		} // end else

	}// end afficherFormulaire
	
	/**
	 * <pre>
	 * Méthode qui permet de rajouter une promotion dans la database 
	 * Invoquée au clic sur le lien 'Ajouter' de soumission du formulaire de la page promotion-formulaire.jsp 
	 * Invoquée avec uen requete HTTP en POST et l'URL '/promotion/add' 
	 * Cette méthode récupère l'objet de commande 'promotionCommand' lié au formulaire
	 * </pre>
	 * 
	 * @param bindingResult:
	 *            contient le resultat du processe dd la validation
	 * @return : le nom logique de la vue
	 */
	@RequestMapping(value = "/promotion/add", method = RequestMethod.POST)
	public String ajouterPromotionDB(@ModelAttribute("attribut_link") EnseignantMatierePromotionLink pLink, ModelMap model) {

		if (pLink.getPromotion().getIdPromotion() == null) {

			// Ajout etudiant via couche service

			promotionService.ajouterPromotion(pLink.getPromotion());

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_promotions", promotionService.trouverAllPromotions());

			return "Promotions/liste-promotion";

		} // end if

		if (pLink.getPromotion().getIdPromotion()  != 0) {

			// Modif etudiant via couche service

			promotionService.ajouterPromotion(pLink.getPromotion());

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_promotions", promotionService.trouverAllPromotions());

			return "Promotions/liste-promotion";

		} // END IF

		return "Promotions/liste-promotion";

		
	}// end ajouterMatiereDB



	
	
	
	
}//end classe
