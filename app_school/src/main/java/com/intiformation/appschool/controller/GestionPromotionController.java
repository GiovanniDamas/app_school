package com.intiformation.appschool.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;
import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.modeles.Promotion;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantMatierePromotionLinkService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;
import com.intiformation.appschool.service.IPromotionService;
import com.intiformation.appschool.validator.LinkValidator;
import com.intiformation.appschool.validator.PromotionValidator;

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
	public void setPromotionService(IPromotionService promotionService) {
		this.promotionService = promotionService;
	}

	public void setEnseignantService(IEnseignantsService enseignantService) {
		this.enseignantService = enseignantService;
	}

	public void setEnseignantMatierePromotionLinkService(
			IEnseignantMatierePromotionLinkService enseignantMatierePromotionLinkService) {
		this.enseignantMatierePromotionLinkService = enseignantMatierePromotionLinkService;
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


	// Declaration du validator:
	@Autowired
	private LinkValidator linkValidator;

	public void setLinkValidator(LinkValidator linkValidator) {
		this.linkValidator = linkValidator;
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
	}

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
	public String recupererListePromotionsDB(ModelMap model, Authentication authentication) {

		// 1. Récupération de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		// 2. Récupération de la liste des matières dans la databse via le service
		List<Promotion> listePromotionsDB = promotionService.trouverAllPromotions();

		// 3. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_promotions", listePromotionsDB);
		model.addAttribute("attribut_personne_connecte", personneConnecte);

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("liste-promotion");
		model.addAttribute("attribut_help", aideDeLaPage);
		
		// 4. Renvoi de la liste vers la vue
		return "Promotions/liste-promotion";
	}// end recupererListePromotionsDB

	/**
	 * Permet de supprimer une promotion dans la base de données avec son id via le
	 * service
	 * 
	 * @return le nom logique de la vue
	 */
	@RequestMapping(value = "/promotion/delete", method = RequestMethod.GET)
	public String supprimerPromotionDB(@RequestParam("idPromotion") Long pIdPromotion, ModelMap model) {

		// modification des etudiants liés à la promo supprimée
		List<Etudiants> listeEtudiantsBdd = etudiantService.findAllEtudiant();
		for (Etudiants etudiants : listeEtudiantsBdd) {
			if (etudiants.getPromotion() != null) {
				if (etudiants.getPromotion().getIdPromotion() == pIdPromotion) {
					etudiants.setPromotion(null);
					etudiantService.modifierEtudiant(etudiants);
				} // end if
			} // end if
		} // end for each

		// 1. Suppresion de la matière de la database via le service
		promotionService.supprimerPromotion(pIdPromotion);

		// 2. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_promotions", promotionService.trouverAllPromotions());

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
	public ModelAndView afficherFormulaire(@RequestParam("idPromotion") Long pIdPromotion, ModelMap model, Authentication authentication) {

		if (pIdPromotion == 0) {

			// Promotion promotion = new Promotion();
			EnseignantMatierePromotionLink enseignantMatierePromotionLink = new EnseignantMatierePromotionLink();

			//3. récup de la personne connectée
			Personnes personneConnecte = getInfosPersonneConnecte(authentication);
			
			// ajout liste des enseignants
			List<Enseignants> listeEnseignantsDB = enseignantService.findAllEnseignant();
			model.addAttribute("attribut_liste_enseignants", listeEnseignantsDB);
			model.addAttribute("attribut_personne_connecte", personneConnecte);


			Map<String, Object> dataCommand = new HashMap<>();
			dataCommand.put("linkCommand", enseignantMatierePromotionLink);

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("promotion-formulaire");
			model.addAttribute("attribut_help", aideDeLaPage);
			
			String viewName = "Promotions/promotion-formulaire";
			return new ModelAndView(viewName, dataCommand);

		} else {

			EnseignantMatierePromotionLink linkToUpdate = new EnseignantMatierePromotionLink();
			// enseignantMatierePromotionLinkService.trouverLinkId(pIdLink);

			//3. récup de la personne connectée
			Personnes personneConnecte = getInfosPersonneConnecte(authentication);
			
			Promotion promotionToUpdate = promotionService.trouverPromotionId(pIdPromotion);

			linkToUpdate.setPromotion(promotionToUpdate);

			List<Enseignants> listeEnseignantsDB = enseignantService.findAllEnseignant();
			model.addAttribute("attribut_liste_enseignants", listeEnseignantsDB);
			model.addAttribute("attribut_personne_connecte", personneConnecte);

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("promotion-formulaire");
			model.addAttribute("attribut_help", aideDeLaPage);

			return new ModelAndView("Promotions/promotion-formulaire", "linkCommand", linkToUpdate);

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
	public String ajouterPromotionDB(@ModelAttribute("linkCommand") EnseignantMatierePromotionLink pLink,
			@RequestParam(value = "enseignant.idPersonne") List<Long> listeIDEnsSelect, ModelMap model,
			BindingResult result) {

		linkValidator.validate(pLink.getPromotion(), result);
		if (result.hasErrors()) {

			Long idPromotion = pLink.getPromotion().getIdPromotion();

			model.addAttribute("idPromotion", idPromotion);

			return "redirect:/promotion/edit-promotion-form";

		} else {

			// Cas d'un ajout de promotion:
			if (pLink.getPromotion().getIdPromotion() == null) {

				// Ajout de la promotion via la couche service
				promotionService.ajouterPromotion(pLink.getPromotion());

				// Ajout des liens entre Promotion et chaque Enseignant selectionné
				for (Long IdEnseignant : listeIDEnsSelect) {

					System.out.println("Id Enseignant" + IdEnseignant);

					// set de l'enseignant
					pLink.getEnseignant().setIdPersonne(IdEnseignant);

					enseignantMatierePromotionLinkService.ajouterLink(pLink);
				}

				// Récupération de la nouvelle lsite des promotions
				model.addAttribute("attribut_liste_promotions", promotionService.trouverAllPromotions());
				// model.addAttribute("attribut_link", pLink);

				// Renvoi vers la page liste-promotion.jsp
				return "redirect:/promotion/liste-promotion";

			} // end if

			if (pLink.getPromotion().getIdPromotion() != 0) {

				// Modif etudiant via couche service

				promotionService.modifierPromotion(pLink.getPromotion());

				List<EnseignantMatierePromotionLink> listeLinkLiésAPromotion = enseignantMatierePromotionLinkService
						.trouverlinkViaIdPromo(pLink.getPromotion().getIdPromotion());

				for (EnseignantMatierePromotionLink link : listeLinkLiésAPromotion) {

					// Surpprimer puis ajouter

					enseignantMatierePromotionLinkService.supprimerLink(link.getId());

				} // end for

				for (Long IdEnseignant : listeIDEnsSelect) {

					System.out.println("Id Enseignant" + IdEnseignant);

					pLink.getEnseignant().setIdPersonne(IdEnseignant);

					enseignantMatierePromotionLinkService.ajouterLink(pLink);
				} // end for

				// Recup nouvelle liste d'etudiant après ajout

				model.addAttribute("attribut_liste_promotions", promotionService.trouverAllPromotions());

				return "redirect:/promotion/liste-promotion";

			} // END IF

		} // end else

		return "redirect:/promotion/liste-promotion";

	}// end ajouterMatiereDB

	@RequestMapping(value = "promotion/enseignant-promotion", method = RequestMethod.GET)
	public String afficherPromotionEnseignants(@RequestParam("idPromotion") Long pIdPromotion, ModelMap model, Authentication authentication) {

		//3. récup de la personne connectée
				Personnes personneConnecte = getInfosPersonneConnecte(authentication);
				
		
		model.addAttribute("attribut_liste_enseignants_promotion",
				enseignantMatierePromotionLinkService.trouverlinkViaIdPromo(pIdPromotion));
		model.addAttribute("attribut_personne_connecte", personneConnecte);

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("enseignants-promotion");
		model.addAttribute("attribut_help", aideDeLaPage);
		
		return "Promotions/enseignants-promotion";

	}// end afficherPromotionEnseignants

}// end classe
