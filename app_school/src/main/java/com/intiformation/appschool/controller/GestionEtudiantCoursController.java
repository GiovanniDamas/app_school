package com.intiformation.appschool.controller;

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

import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.service.ICoursService;
import com.intiformation.appschool.service.IEtudiantCoursService;
import com.intiformation.appschool.service.IEtudiantsService;



/**
 * contrôleur spring mvc pour la gestion des EtudiantCours 
 * @author marle
 *
 */
@Controller
public class GestionEtudiantCoursController {

	//déclaration de la couche service
	@Autowired
	private IEtudiantCoursService etudiantCoursService;

	/**
	 * setter pour l'injection spring du service
	 * @param etudiantCoursService
	 */
	public void setEtudiantCoursService(IEtudiantCoursService etudiantCoursService) {
		this.etudiantCoursService = etudiantCoursService;
	}
	
	//____________________________________________________________________________________________________
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
	
	//déclaration du service
	@Autowired //injection par modificateur
	private IEtudiantsService etudiantsService;

	/** 
	 * setter pour injection spring du service
	 * @param etudiantsService
	 */
	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}
	//_____________________________________________________________________________________________________
	
	
	/*__________________________ méthodes gestionnaires _______________*/
	/**
	 * permet d'afficher la liste de présence de la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/liste", method=RequestMethod.GET)
	public String afficherListeEtudiantsCoursBdd(ModelMap model) {
		
		//1. récup de la liste des étudiants des cours de la bdd via le service
		List<EtudiantCours> listePresenceBdd = etudiantCoursService.findAllEtudiantCours();
		
		//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_presence", listePresenceBdd);
		
		//_______________________________________________________________________________________
		model.addAttribute("attribut_cours", coursService.findAllCours());
		model.addAttribute("attribut_etudiants", etudiantsService.findAllEtudiant());
		//_______________________________________________________________________________________
		
		//3. renvoi du nom logique de la vue
		return "etudiants-cours/liste-presence";
		
	}//end afficherListeEtudiantsCoursBdd
	
	/**
	 * permet de supprimer ligne présence d' un étudiant à un cours de la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/supprimer", method=RequestMethod.GET)
	public String supprimerEtudiantCoursBdd(@RequestParam("etudiantCoursId") Long pIdEtudiantCours, ModelMap model) {
		
		//1. suppression dans la bdd
		etudiantCoursService.supprimerEtudiantCours(pIdEtudiantCours);
		
		//2. renvoi du nom logique de la vue
		return "redirect:/etudiants-cours/liste";
		
	}//end supprimerEtudiantCoursBdd
	
	/**
	 * permet d'afficher le formulaire pour ajouter la présnece d'un étudaint à un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/formulaire-ajout-presence", method=RequestMethod.GET)
	public String chargerEtudiantCoursBdd(ModelMap model) {
		
		//1. création objet EtudiantCours
		EtudiantCours etudiantCours = new EtudiantCours();
		
		//2. renvoi de l'étudiantCours vers la vue via l'objet model
		model.addAttribute("attribut_etudiant_cours", etudiantCours);
		
		//_______________________________________________________________________________________
		model.addAttribute("attribut_cours", coursService.findAllCours());
		model.addAttribute("attribut_etudiants", etudiantsService.findAllEtudiant());
		//_______________________________________________________________________________________
		
		//3. renvoi du nom logique de la vue
		return "etudiants-cours/formulaire-ajout-presence";
		
	}//end chargerEtudiantCoursBdd
	
	/**
	 * permet d'ajouter la présence d'un étudiant à un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/ajouter", method=RequestMethod.POST)
	public String ajouterEtudiantCoursBdd(@ModelAttribute("attribut_etudiant_cours") EtudiantCours pEtudiantCours, BindingResult result) {
		
			//1. ajout de l'étudiant cours dans la bdd
			etudiantCoursService.ajouterEtudiantCours(pEtudiantCours);
			
			//3. renvoi du nom logique de la vue
			return "redirect:/etudiants-cours/liste";		
		
	}//end ajouterEtudiantCoursBdd
	
	/**
	 * permet d'afficher le formulaire pour modifier la présence d'un étudiant à un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/formulaire-modif-presence", method=RequestMethod.GET)
	public String chargerModifEtudiantCoursBdd(@RequestParam("etudiantCoursId") Long pIdEtudiantCours, ModelMap model) {
		
		//1. récupération de l'etudiant cours à modifier
		EtudiantCours etudiantCoursToUpdate = etudiantCoursService.findEtudiantCoursById(pIdEtudiantCours);
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_etudiant_cours", etudiantCoursToUpdate);
		
		//_______________________________________________________________________________________
		model.addAttribute("attribut_cours", coursService.findAllCours());
		model.addAttribute("attribut_etudiants", etudiantsService.findAllEtudiant());
		//_______________________________________________________________________________________
		
		//3. renvoi du nom logique de la vue
		return "etudiants-cours/formulaire-modif-presence";
		
	}//end chargerModifEtudiantCoursBdd
	
	/**
	 * permet de modifier la présence d'un étudiant à un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/modifier", method=RequestMethod.POST)
	public String modifierEtudiantCoursBdd(@ModelAttribute("attribut_etudiant_cours") EtudiantCours pEtudiantCours, BindingResult result) {
		
			//1. modif de l'étudiant cours dans la bdd	
			etudiantCoursService.modifierEtudiantCours(pEtudiantCours);
			
			System.out.println("cours : " + pEtudiantCours.getCours());
			
			//2. renvoi du nom logique de la vue
			return "redirect:/etudiants-cours/liste";		
		
	}//end modifierEtudiantCoursBdd
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une matière
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/recherche-etudiant", method=RequestMethod.GET)
	public String afficherListeEtudiantCoursByEtudiant(@RequestParam("id-etudiant") Long pIdEtudiant, ModelMap model) {
		
		if(pIdEtudiant==0) {
			
			return "redirect:/etudiants-cours/liste";
			
		}else {
			
			//1. récup de la liste des étudiants cours de la bdd via le service
			List<EtudiantCours> listeEtudiantCoursByEtudiantBdd = etudiantCoursService.afficherEtudiantCoursByEtudiant(pIdEtudiant);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_presence", listeEtudiantCoursByEtudiantBdd);
				
			//_______________________________________________________________________________________
			model.addAttribute("attribut_cours", coursService.findAllCours());
			model.addAttribute("attribut_etudiants", etudiantsService.findAllEtudiant());
			//_______________________________________________________________________________________
			
			//3. renvoi du nom logique de la vue
			return "etudiants-cours/liste-presence";			
		
		}//end else
		
	}//end afficherListeCoursByMatiere
	
	/**
	 * permet d'afficher la liste de l'ensemble des étudiants absents d'un cours de la bdd 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/recherche-cours", method=RequestMethod.GET)
	public String afficherListeEtudiantCoursByCours(@RequestParam("id-cours") Long pIdCours, ModelMap model) {
		
		if(pIdCours==0) {
			
			return "redirect:/etudiants-cours/liste";
		
		}else {
						
			//1. récup de la liste des cours de la bdd via le service
			List<EtudiantCours> listeEtudiantCoursByCoursBdd = etudiantCoursService.afficherEtudiantCoursByCours(pIdCours);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_presence", listeEtudiantCoursByCoursBdd);
			
			//_______________________________________________________________________________________
			model.addAttribute("attribut_cours", coursService.findAllCours());
			model.addAttribute("attribut_etudiants", etudiantsService.findAllEtudiant());
			//_______________________________________________________________________________________
			
			//3. renvoi du nom logique de la vue
			return "etudiants-cours/liste-presence";
			
		}//end else
		
	}//end afficherListeEtudiantCoursByCours	
	
}//end class
