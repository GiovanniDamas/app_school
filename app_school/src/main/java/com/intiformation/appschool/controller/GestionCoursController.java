package com.intiformation.appschool.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.tag.el.fmt.ParseDateTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.service.ICoursService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IMatiereService;
import com.intiformation.appschool.service.IPromotionService;
import com.intiformation.appschool.validator.CoursValidator;

/**
 * contrôleur spring mvc pour la gestion des cours
 * @author marle
 *
 */
@Controller 
public class GestionCoursController {

	//déclaration du service coursService
	@Autowired //injection par modificateur
	private ICoursService coursService;

	/** 
	 * setter pour injection spring du service
	 * @param coursService
	 */
	public void setCoursService(ICoursService coursService) {
		this.coursService = coursService;
	}
	
	//déclaration du service de matière
	@Autowired //injection par modificateur
	private IMatiereService matiereService;

	/** 
	 * setter pour injection spring 
	 * @param matiereService
	 */
	public void setMatiereService(IMatiereService matiereService) {
		this.matiereService = matiereService;
	}

	//déclaration du service de promotion
	@Autowired //injection par modificateur
	private IPromotionService promotionService;

	/** 
	 * setter pour injection spring 
	 * @param promotionService
	 */
	public void setPromotionService(IPromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	/*____________________________________________________________________________________________________________*/
	//déclaration du service de enseignant
	@Autowired //injection par modificateur
	private IEnseignantsService enseignantsService;

	/** 
	 * setter pour injection spring 
	 * @param promotionService
	 */
	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}
	/*____________________________________________________________________________________________________________*/

	//déclaration du validateur
	@Autowired //injection par modificateur
	private CoursValidator coursValidator;

	/** 
	 * setter pour injection spring 
	 * @param coursValidator
	 */
	public void setCoursValidator(CoursValidator coursValidator) {
		this.coursValidator = coursValidator;
	}
	
	/*__________________________ méthodes gestionnaires _______________*/
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/liste", method=RequestMethod.GET)
	public String afficherListeCoursBdd(ModelMap model) {
		
		//1. récup de la liste des cours de la bdd via le service
		List<Cours> listeCoursBdd = coursService.findAllCours();
		
		//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_cours", listeCoursBdd);
		
		// renvoi de la liste des matières et des promotions vers la vue 
		model.addAttribute("attribut_enseignant", enseignantsService.findAllEnseignant());
		model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
		model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
		
		//3. renvoi du nom logique de la vue
		return "cours/liste-cours";
		
	}//end afficherListeCoursBdd
	
	/**
	 * permet de supprimer un cours de la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/supprimer", method=RequestMethod.GET)
	public String supprimerCoursBdd(@RequestParam("coursId") Long pIdCours, ModelMap model) {
		
		//1. suppression dans la bdd
		coursService.supprimerCours(pIdCours);
		
		//2. renvoi du nom logique de la vue
		return "redirect:/cours/liste";
		
	}//end supprimerCoursBdd
	
	/**
	 * permet d'afficher le formulaire pour ajouter un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/formulaire-ajout", method=RequestMethod.GET)
	public String chargerCoursBdd(ModelMap model) {
		
		//1. création objet cours
		Cours cours = new Cours();
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_cours", cours);
		
		// renvoi de la liste des matières et des promotions vers la vue 
		model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
		model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
		
		//3. renvoi du nom logique de la vue
		return "cours/formulaire-ajout";
		
	}//end chargerCoursBdd
	
	/**
	 * permet d'ajouter un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/ajouter", method=RequestMethod.POST)
	public String ajouterCoursBdd(@ModelAttribute("attribut_cours") @Validated Cours pCours, BindingResult result) {
		
			//1. application du validateur sur l'objet pCours
			coursValidator.validate(pCours, result);
					
			if (result.hasErrors()) {
				
				//2.a renvoi vers le formulaire
				return "cours/formulaire-ajout";
			
			}else {
				
				//2.b ajout du cours dans la bdd
				coursService.ajouterCours(pCours);
				
				//3. renvoi du nom logique de la vue
				return "redirect:/cours/liste";	
				
			}	
		
	}//end ajouterCoursBdd
	
	/**
	 * permet d'afficher le formulaire pour modifier un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/formulaire-modif", method=RequestMethod.GET)
	public String chargerModifCoursBdd(@RequestParam("coursId") Long pIdCours, ModelMap model) {
		
		//1. récupération du cours à modifier
		Cours coursToUpdate = coursService.findCoursById(pIdCours);
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_cours", coursToUpdate);
		
		// renvoi de la liste des matières et des promotions vers la vue 
		model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
		model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
		
		//3. renvoi du nom logique de la vue
		return "cours/formulaire-modif";
		
	}//end chargerModifCoursBdd
	
	/**
	 * permet de modifier un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/modifier", method=RequestMethod.POST)
	public String modifierCoursBdd(@ModelAttribute("attribut_cours") @Validated Cours pCours, BindingResult result) {
		
			//1. application du validateur sur l'objet pCours
			coursValidator.validate(pCours, result);
			
			if (result.hasErrors()) {
				
				//2.a renvoi vers le formulaire
				return "cours/formulaire-modif";
			
			}else {
				
				//2.b modif du cours dans la bdd
				coursService.modifierCours(pCours);
				
				//3. renvoi du nom logique de la vue
				return "redirect:/cours/liste";	
				
			}		
		
	}//end modifierCoursBdd
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une matière
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-matiere", method=RequestMethod.GET)
	public String afficherListeCoursByMatiere(@RequestParam("id-matiere") Long pIdMatiere, ModelMap model) {
		
		if (pIdMatiere == 0) {
			
			return "redirect:/cours/liste";
		
		} else {

			//1. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByMatiereBdd = coursService.findCoursParMatiere(pIdMatiere);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByMatiereBdd);
			
			// renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
			
			//3. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}
		
	}//end afficherListeCoursByMatiere
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une promotion
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-promotion", method=RequestMethod.GET)
	public String afficherListeCoursByPromo(@RequestParam("id-promo") Long pIdPromotion, ModelMap model) {
		
		if (pIdPromotion == 0) {
			
			return "redirect:/cours/liste";
		
		} else {
			
			//1. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByPromoBdd = coursService.findCoursParPromotion(pIdPromotion);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByPromoBdd);
			
			// renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
			
			//3. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}
		
	}//end afficherListeCoursByPromo
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une date
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-date", method=RequestMethod.GET)
	public String afficherListeCoursByDate(@RequestParam("date") String pDateString, ModelMap model) {
		
		System.out.println("date string = " + pDateString + " class: " + pDateString.getClass());
		
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date pDate;
			pDate = formatter.parse(pDateString);
			System.out.println("date = " + pDate + " class: " + pDate.getClass());
	
			if (pDate == null) {
			
				return "redirect:/cours/liste";
		
			} else {
			
			//1. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByDateBdd = coursService.findCoursParDate(pDate);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByDateBdd);
			
			// renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
			
			//3. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}//end else
		
		} catch (ParseException e) {
			e.printStackTrace();
		}//end catch
		
		return "cours/liste-cours";
	
	}//end afficherListeCoursByDate
	
	/*_____________________________________________________________________________________________________*/
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd donné par un enseignant
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-enseignant", method=RequestMethod.GET)
	public String afficherListeCoursByEns(@RequestParam("id-enseignant") Long pIdEnseignant, ModelMap model) {
		
		if (pIdEnseignant == 0) {
			
			return "redirect:/cours/liste";
		
		} else {
			
			//1. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByEnsBdd = coursService.findCoursEnseignant(pIdEnseignant);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByEnsBdd);
			
			// renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_enseignant", enseignantsService.findAllEnseignant());
			model.addAttribute("attribut_matieres", matiereService.trouverAllMatieres());
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());		
			
			//3. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}
		
	}//end afficherListeCoursByPromo
	
}//end class
