package com.intiformation.appschool.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.service.ICoursService;

/**
 * contrôleur spring mvc pour la gestion des cours
 * @author marle
 *
 */
@Controller 
public class GestionCoursController {

	//déclaration du service
	@Autowired //injection par modificateur
	private ICoursService coursService;

	/** 
	 * setter pour injection spring du service
	 * @param coursService
	 */
	public void setCoursService(ICoursService coursService) {
		this.coursService = coursService;
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
		
		//3. renvoi du nom logique de la vue
		return "cours/formulaire-ajout";
		
	}//end chargerCoursBdd
	
	/**
	 * permet d'ajouter un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/ajouter", method=RequestMethod.POST)
	public String ajouterCoursBdd(@ModelAttribute("attribut_cours") Cours pCours, BindingResult result) {
		
			//1. ajout du cours dans la bdd
			coursService.ajouterCours(pCours);
			
			//3. renvoi du nom logique de la vue
			return "redirect:/cours/liste";		
		
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
		
		//3. renvoi du nom logique de la vue
		return "cours/formulaire-modif";
		
	}//end chargerModifCoursBdd
	
	/**
	 * permet de modifier un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/modifier", method=RequestMethod.POST)
	public String modifierCoursBdd(@ModelAttribute("attribut_cours") Cours pCours, BindingResult result) {
		
			//1. ajout du cours dans la bdd
			coursService.modifierCours(pCours);
			
			//3. renvoi du nom logique de la vue
			return "redirect:/cours/liste";		
		
	}//end modifierCoursBdd
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une matière
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/liste/{matiere-id}", method=RequestMethod.GET)
	public String afficherListeCoursByMatiere(@PathVariable("matiere-id") Long pIdMatiere, ModelMap model) {
		
		//1. récup de la liste des cours de la bdd via le service
		List<Cours> listeCoursByMatiereBdd = coursService.findCoursParMatiere(pIdMatiere);
		
		//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_cours", listeCoursByMatiereBdd);
		
		//3. renvoi du nom logique de la vue
		return "cours/liste-cours";
		
	}//end afficherListeCoursByMatiere
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une promotion
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/liste/{promo-id}", method=RequestMethod.GET)
	public String afficherListeCoursByPromo(@PathVariable("promo-id") Long pIdPromotion, ModelMap model) {
		
		//1. récup de la liste des cours de la bdd via le service
		List<Cours> listeCoursByPromoBdd = coursService.findCoursParPromotion(pIdPromotion);
		
		//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_cours", listeCoursByPromoBdd);
		
		//3. renvoi du nom logique de la vue
		return "cours/liste-cours";
		
	}//end afficherListeCoursByPromo
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une date
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/liste/{date}", method=RequestMethod.GET)
	public String afficherListeCoursByDate(@PathVariable("date") Date pDate, ModelMap model) {
		
		//1. récup de la liste des cours de la bdd via le service
		List<Cours> listeCoursByDateBdd = coursService.findCoursParDate(pDate);
		
		//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_cours", listeCoursByDateBdd);
		
		//3. renvoi du nom logique de la vue
		return "cours/liste-cours";
		
	}//end afficherListeCoursByDate
	
}//end class
