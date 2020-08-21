package com.intiformation.appschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;
import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.modeles.Promotion;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantMatierePromotionLinkService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;
import com.intiformation.appschool.service.IMatiereService;
import com.intiformation.appschool.service.IPromotionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	@Autowired
	private IPromotionService promoService;

	@Autowired
	private IEnseignantMatierePromotionLinkService linkService;

	// ___ déclaration du service de enseignant avec setter pour injection spring
	@Autowired // injection par modificateur
	private IEnseignantsService enseignantsService;

	@Autowired
	private IAideService aideService;

	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}

	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}

	// ____ déclaration du service de administrateur avec setter pour injection
	// spring
	@Autowired // injection par modificateur
	private IAdministrateursService administrateursService;

	public void setAdministrateursService(IAdministrateursService administrateursService) {
		this.administrateursService = administrateursService;
	}

	// ___ déclaration du service de etudiant avec setter pour injection spring
	@Autowired // injection par modificateur
	private IEtudiantsService etudiantsService;

	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}

	/**
	 * Setter de la couche service pour injection pour modificateur de Spring
	 * 
	 * @param matiereService
	 */
	public void setMatiereService(IMatiereService matiereService) {
		this.matiereService = matiereService;
	}

	public void setPromoService(IPromotionService promoService) {
		this.promoService = promoService;
	}

	public void setLinkService(IEnseignantMatierePromotionLinkService linkService) {
		this.linkService = linkService;
	}

	// Declaration du validator:

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
			personneConnecte = enseignantsService.findEnseignantByIdentifiant(authentication.getName());

		} else if (authentication.getAuthorities().toString().contains("ROLE_ETUDIANT")) {

			// 1. cas d'un etudiant : récupération de l'eutidnat connecté
			personneConnecte = etudiantsService.findEtudiantByIdentifiant(authentication.getName());
		}

		return personneConnecte;

	}// end getInfosPersonneConnecte

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
	@RequestMapping(value = "/matiere/liste-matiere", method = RequestMethod.GET)
	public String recupererListeMatieresDB(ModelMap model, Authentication authentication) {

		// 1. Récupération de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);

		// 2. Récupération de la liste des matières dans la databse via le service
		List<Matiere> listeMatieresDB = matiereService.trouverAllMatieres();

		// 3. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", listeMatieresDB);
		model.addAttribute("attribut_personne_connecte", personneConnecte);

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("liste-matiere");
		model.addAttribute("attribut_help", aideDeLaPage);

		// 4. Renvoi de la liste vers la vue
		return "liste-matiere";
	}// end recupererListeMatieresDB

	/**
	 * Permet de supprimer une matière dans la base de données avec son id via le
	 * service
	 * 
	 * @return le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/delete", method = RequestMethod.GET)
	public String supprimerMatiereDB(@RequestParam("idMatiere") Long pIdMatiere, ModelMap model) {

		// 1. Suppresion de la matière de la database via le service
		matiereService.supprimerMatiere(pIdMatiere);

		// 2. Récup de la nouvelle liste des employés après suppression
		List<Matiere> listeMatieresDB = matiereService.trouverAllMatieres();

		// 3. Renvoi de la liste vers la vue via l'objet model
		model.addAttribute("attribut_liste_matieres", listeMatieresDB);

		// 4 OU : rédirection vers l'url '/employe/liste' pour invoquer la méthode
		// 'recupererListeEmployesDB' et rediriger vers liste-employes.jsp
		return "redirect:/matiere/liste-matiere";

	}// end supprimerEmployeDB

	/**
	 * <pre>
	 * Permet d'afficher le formulaire d'ajout d'une matiere
	 * Méthode invoquée suite au clic sur le lien '' de liste-matiere.jsp 
	 * Invoquée avec une requete HTTP GET ayant url "/matiere/add-matiere-form"
	 * </pre>
	 * 
	 * @return
	 */
	@RequestMapping(value = "/matiere/edit-matiere-form", method = RequestMethod.GET)
	// @GetMapping
	public ModelAndView afficherFormulaire(@RequestParam("idMatiere") Long pIdMatiere, ModelMap model) {

		if (pIdMatiere == 0) {

			// Matiere matiere = new Matiere();

			EnseignantMatierePromotionLink enseignantMatierePromotionLink = new EnseignantMatierePromotionLink();

			List<Promotion> listePromotionsDB = promoService.trouverAllPromotions();
			model.addAttribute("attribut_liste_promotions", listePromotionsDB);

			Map<String, Object> dataCommand = new HashMap<>();
			dataCommand.put("linkCommand", enseignantMatierePromotionLink);

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("matiere-formulaire");
			model.addAttribute("attribut_help", aideDeLaPage);

			String viewName = "matiere-formulaire";
			return new ModelAndView(viewName, dataCommand);

			// model.addAttribute("attribut_matiere", matiere);
			// model.addAttribute("idMatiere", pIdMatiere);

		} else {

			EnseignantMatierePromotionLink linkToUpdate = new EnseignantMatierePromotionLink();

			Matiere matiereToUpdate = matiereService.trouverMatiereId(pIdMatiere);

			linkToUpdate.setMatiere(matiereToUpdate);

			List<Promotion> listePromotionsDB = promoService.trouverAllPromotions();
			model.addAttribute("attribut_liste_promotions", listePromotionsDB);

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("matiere-formulaire");
			model.addAttribute("attribut_help", aideDeLaPage);

			return new ModelAndView("matiere-formulaire", "linkCommand", linkToUpdate);

		} // end else

	}// end afficherFormulaire

	/**
	 * <pre>
	 * Méthode qui permet de rajouter una matiere dans la database 
	 * Invoquée au clic sur le lien 'Ajouter' de soumission du formulaire de la page ajouter-matiere.jsp 
	 * Invoquée avec uen requete HTTP en POST et l'URL '/matiere/add' 
	 * Cette méthode récupère l'objet de commande 'matiereCommand' lié au formulaire
	 * </pre>
	 * 
	 * @param bindingResult:
	 *            contient le resultat du processe dd la validation
	 * @return : le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/add", method = RequestMethod.POST)
	// @PostMapping
	public String ajouterMatiereDB(@ModelAttribute("linkCommand") EnseignantMatierePromotionLink pLink,
			/*
			 * @RequestParam(value = "promotion.idPromotion") List<Long> listeIDPromSelect,
			 */ ModelMap model) {

		if (pLink.getMatiere().getIdMatiere() == null) {

			// Ajout etudiant via couche service

			Matiere matiereAjoutee = matiereService.addMatiere(pLink.getMatiere());

			Long IdMatiere = matiereAjoutee.getIdMatiere();

			System.out.println("Id Matiere ajoutée = " + IdMatiere);
			/*
			 * // Ajout des liens en matière et chaque promotion selectionnée for (Long
			 * IdPromotion : listeIDPromSelect) {
			 * 
			 * List<EnseignantMatierePromotionLink> listLinkPromo =
			 * linkService.trouverlinkViaIdPromo(IdPromotion);
			 * 
			 * for (EnseignantMatierePromotionLink link : listLinkPromo) {
			 * 
			 * link.getMatiere().setIdMatiere(IdMatiere);
			 * 
			 * linkService.modifierLink(link);
			 * 
			 * } // end for
			 * 
			 * } // end for
			 */
			// Recup nouvelle liste d'etudiant après ajout
			model.addAttribute("attribut_liste_matieres", matiereService.trouverAllMatieres());

			return "redirect:/matiere/liste-matiere";

		} // end if

		if (pLink.getMatiere().getIdMatiere() != 0) {

			// Modif etudiant via couche service

			matiereService.modifierMatiere(pLink.getMatiere());

			// A FINIR

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_matieres", matiereService.trouverAllMatieres());

			return "redirect:/matiere/liste-matiere";

		} // END IF

		return "redirect:/matiere/liste-matiere";

		// Application du validateur de l'objet pEmploye
		// employeValidator.validate(pEmploye, resultatValidation);
		/*
		 * // Validation de l'objet if (resultatValidation.hasErrors()) {
		 * 
		 * // CAS 1 : La validation retourne des erreurs :
		 * 
		 * // redirection vers la page du formulaire ajouter-employe.jsp
		 * 
		 * return "ajouter-employe"; // nom logique de la vue
		 * 
		 * }else {
		 * 
		 * // CAS 2 : La validation ne détecte pas d'erreur
		 * 
		 * // 1. Ajout de l'employé à la database via couche service
		 * matiereService.ajouterMatiere(pMatiere);
		 * 
		 * // 2. Redirection vers la page 'liste-employes.jsp"
		 * 
		 * // 2.1 Récup de la nouvelle liste des employés dans la db
		 * model.addAttribute("attribut_liste_matieres",
		 * matiereService.trouverAllMatieres());
		 * 
		 * // 2.2 Redirection
		 * 
		 * return "redirect:/matiere/liste";
		 * 
		 * // }//end else
		 */
	}// end ajouterMatiereDB

	/**
	 * Permet d'afficher le formulaire de modification d'un employé Méthode invoquée
	 * suite au clic sur le lien 'Modifier' de liste-employes.jsp Invoquée avec une
	 * requete HTTP GET ayant url "employe/update-employe-form?idemploye=1"
	 * 
	 * @return
	 */

	@RequestMapping(value = "/matiere/liaison-matiere-form", method = RequestMethod.GET)
	public ModelAndView AfficherFormulaireAjoutPromotions(@RequestParam("idMatiere") Long pIdMatiere, ModelMap model, Authentication authentication) {

		EnseignantMatierePromotionLink linkToUpdate = new EnseignantMatierePromotionLink();

		
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		// List<EnseignantMatierePromotionLink> listeLinksParMatière =
		// linkService.trouverlinkViaIdMatiere(pIdMatiere);

		Matiere matiereToUpdate = matiereService.trouverMatiereId(pIdMatiere);

		linkToUpdate.setMatiere(matiereToUpdate);

		List<Promotion> listePromotionsDB = promoService.trouverAllPromotions();
		model.addAttribute("attribut_liste_promotions", listePromotionsDB);
		model.addAttribute("idMatiere", pIdMatiere);
		// model.addAttribute("attribut_liste_enseignant",
		// enseignantsService.findAllEnseignant());

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("lier-matiere-enseignant");
		model.addAttribute("attribut_help", aideDeLaPage);

		return new ModelAndView("lier-matiere-enseignant", "linkCommand", linkToUpdate);

	}// end AfficherFormulaireModification

	@RequestMapping(value = "/matiere/lier1", method = RequestMethod.POST)
	public ModelAndView afficherForumlaireajoutEnseignant(
			@RequestParam(value = "promotion.idPromotion") Long IDPromSelect, ModelMap model,
			@ModelAttribute("linkCommand") EnseignantMatierePromotionLink pLink, Authentication authentication)
			throws Exception {

		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		pLink.getPromotion().setIdPromotion(IDPromSelect);

		List<EnseignantMatierePromotionLink> listLinks = linkService.trouverlinkViaIdPromo(IDPromSelect);

		model.addAttribute("attribut_liste_enseignant", listLinks);
		model.addAttribute("attribut_liste_promotions", promoService.trouverAllPromotions());

		return new ModelAndView("lier-matiere-enseignant-promotion", "linkCommand", pLink);

	}// end

	/**
	 * Méthode qui permet de modifier une matiere dans la database Invoquée au clic
	 * sur le lien 'modifier' de soumission du formulaire de la page
	 * modifier-employe.jsp Invoquée avec uen requete HTTP en POST et l'URL
	 * '/employe/update' Cette méthode récupère l'objet de commande
	 * 'employeModifCommand' lié au formulaire
	 * 
	 * @return : le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/lier2", method = RequestMethod.POST)
	public String lierMatierePromotionDB(@RequestParam(value = "enseignant.idPersonne") List<Long> listeIDEnseignants ,
			ModelMap model, HttpServletRequest request,
			@ModelAttribute("linkCommand") EnseignantMatierePromotionLink pLink) throws Exception {

	
		
		System.out.println("pIdMatiere" + pLink.getMatiere().getIdMatiere());
		System.out.println("pIdPromotion" + pLink.getPromotion().getIdPromotion());

		List<EnseignantMatierePromotionLink> listeDePromo = linkService
				.trouverlinkViaIdPromo(pLink.getPromotion().getIdPromotion());

		Matiere matiereAAjouter = matiereService.trouverMatiereId(pLink.getMatiere().getIdMatiere());

		for (EnseignantMatierePromotionLink link : listeDePromo) {

			for (Long idEnseignant : listeIDEnseignants) {

				if (idEnseignant == link.getEnseignant().getIdPersonne()) {
					
					link.setMatiere(matiereAAjouter);
					linkService.modifierLink(link);
				}
	

			} // end for
		}
		
		model.addAttribute("attribut_liste_matieres", matiereService.trouverAllMatieres());

		return "redirect:/matiere/liste-matiere";

	}// end modifierEmployeDB()

	@RequestMapping(value = "/matiere/promotion-matiere", method = RequestMethod.GET)
	public String afficherPromotionParMatiere(@RequestParam("idMatiere") Long pIdMatiere, ModelMap model,
			Authentication authentication) {

		// 3. récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);

		model.addAttribute("attribut_liste_promotion_matiere", linkService.trouverlinkViaIdMatiere(pIdMatiere));
		model.addAttribute("attribut_personne_connecte", personneConnecte);

		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("enseignants-promotion");
		model.addAttribute("attribut_help", aideDeLaPage);

		return "promotion-matiere";

	}// end afficherPromotionEnseignants
	
	/**
	 * Permet de supprimer une matière dans la base de données avec son id via le
	 * service
	 * 
	 * @return le nom logique de la vue
	 */
	@RequestMapping(value = "/matiere/supprimer-lien", method = RequestMethod.GET)
	public String supprimerLienMatierePromotionDB(@RequestParam("idLien") Long pIdLink, ModelMap model, RedirectAttributes redirectAttributes) {

		EnseignantMatierePromotionLink lienASupprimer = linkService.trouverLinkId(pIdLink);

		Long IdMatiere = lienASupprimer.getMatiere().getIdMatiere();
		redirectAttributes.addFlashAttribute("idMatiere", IdMatiere);
		
		
		model.addAttribute("attribut_liste_promotion_matiere", linkService.trouverlinkViaIdMatiere(IdMatiere));
		
		//Matiere matiereNulle = new Matiere(0L);
		lienASupprimer.setMatiere(null);
		
		linkService.modifierLink(lienASupprimer);
	
		return "promotion-matiere";

	}// end supprimerEmployeDB
	

}// end class
