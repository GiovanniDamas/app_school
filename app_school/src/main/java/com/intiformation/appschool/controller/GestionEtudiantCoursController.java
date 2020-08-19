package com.intiformation.appschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.ICoursService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantCoursService;
import com.intiformation.appschool.service.IEtudiantsService;
import com.intiformation.appschool.validator.EtudiantCoursValidator;



/**
 * contrôleur spring mvc pour la gestion des EtudiantCours 
 * @author marle
 *
 */
@Controller
public class GestionEtudiantCoursController {

	//___ déclaration de la couche service etudiantCoursService
	@Autowired
	private IEtudiantCoursService etudiantCoursService;

	/**
	 * setter pour l'injection spring du service
	 * @param etudiantCoursService
	 */
	public void setEtudiantCoursService(IEtudiantCoursService etudiantCoursService) {
		this.etudiantCoursService = etudiantCoursService;
	}
	
	//___ déclaration du service coursService avec setter pour injection spring
	@Autowired //injection par modificateur
	private ICoursService coursService;

	public void setCoursService(ICoursService coursService) {
		this.coursService = coursService;
	}
	
	//___ déclaration du service etudiantsService avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEtudiantsService etudiantsService;

	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}
	
	//___ déclaration du service de enseignant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEnseignantsService enseignantsService;

	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}

	//____ déclaration du service de administrateur avec setter pour injection spring
	@Autowired //injection par modificateur
	private IAdministrateursService administrateursService;

	public void setAdministrateursService(IAdministrateursService administrateursService) {
		this.administrateursService = administrateursService;
	}
	
	@Autowired
	private IAideService aideService;
		
	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}

	//___ déclaration du validateur
	@Autowired //injection par modificateur
	private EtudiantCoursValidator etudiantCoursValidator;
	
	/** 
	 * setter pour injection spring 
	 * @param coursValidator
	 */
	public void setEtudiantCoursValidator(EtudiantCoursValidator etudiantCoursValidator) {
		this.etudiantCoursValidator = etudiantCoursValidator;
	}
	
	/**
	 * méthode qui permet de récupérer les informations de la personne connectée
	 * @param authentication
	 * @return
	 */
	public Personnes getInfosPersonneConnecte(Authentication authentication) {
		
		Personnes personneConnecte = null;
		
		if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
			
			//1. cas d'un admin : récupération de l'administrateur connecté
			personneConnecte = administrateursService.findAdministrateurByIdentifiant(authentication.getName());
	
		} else if (authentication.getAuthorities().toString().contains("ROLE_ENSEIGNANT")) {
			
			//1. cas d'un enseignant : récupération de l'enseignant connecté
			personneConnecte = enseignantsService.findEnseignantByIdentifiant(authentication.getName());
			
		} else if (authentication.getAuthorities().toString().contains("ROLE_ETUDIANT")) {
			
			//1. cas d'un etudiant : récupération de l'etudiant connecté
			personneConnecte = etudiantsService.findEtudiantByIdentifiant(authentication.getName());
		}
		
		return personneConnecte;
		
	}//end getInfosPersonneConnecte

	/*=================================================================*/
	/*======================= méthodes gestionnaires ==================*/
	/*=================================================================*/
	/**
	 * permet d'afficher le formulaire pour modifier la présence d'un étudiant à un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/formulaire-modif-presence", method=RequestMethod.GET)
	public String chargerModifEtudiantCoursBdd(@RequestParam("etudiantCoursId") Long pIdEtudiantCours, ModelMap model, Authentication authentication) {
		
		//1. récupération de l'etudiant cours à modifier
		EtudiantCours etudiantCoursToUpdate = etudiantCoursService.findEtudiantCoursById(pIdEtudiantCours);
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_etudiant_cours", etudiantCoursToUpdate);
		
		//3. récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		//4. renvoi de la liste des cours et des étudiants et de la personne connectée vers la vue 
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_liste_presence", etudiantCoursService.findEtudiantCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_cours", coursService.findCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_etudiants", etudiantsService.findEtudiantsByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));			
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("formulaire-modif-presence");
		model.addAttribute("attribut_help", aideDeLaPage);		
		
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
		
		//1. application du validateur sur l'objet pEtudiantCours
		etudiantCoursValidator.validate(pEtudiantCours, result);
		
		if (result.hasErrors()) {
			
			
			//2.a renvoi vers le formulaire
			return "etudiants-cours/formulaire-modif-presence";
		
		}else {
			
			//2.b modif de l'étudiant cours dans la bdd	
			etudiantCoursService.modifierEtudiantCours(pEtudiantCours);
			
			//3. renvoi du nom logique de la vue
			return "redirect:/etudiants-cours/liste";	
			
		}//end else			
		
	}//end modifierEtudiantCoursBdd

	/**
	 * permet d'afficher la liste des absences d'un étudiant 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/recherche-absence-etudiant", method=RequestMethod.GET)
	public String afficherListeAbsencesByEtudiant(@RequestParam("etudiant-id") Long pIdEtudiant, ModelMap model, Authentication authentication) {
		
		// récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		if(pIdEtudiant==0) {
			
			return "redirect:/etudiants-cours/liste";
			
		}else {
			
			//1. récup de la liste des étudiants cours de la bdd via le service
			List<EtudiantCours> listeAbsencesByEtudiantBdd = etudiantCoursService.afficherAbsencesByEtudiant(pIdEtudiant);
			
			//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_absence", listeAbsencesByEtudiantBdd);
				
			// renvoi de la liste des cours et des étudiants et de la personne connectée vers la vue 
			model.addAttribute("attribut_personne_connecte", personneConnecte);
			model.addAttribute("attribut_liste_presence", etudiantCoursService.findEtudiantCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
			model.addAttribute("attribut_cours", coursService.findCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
			model.addAttribute("attribut_etudiants", etudiantsService.findEtudiantsByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));			
			
			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("liste-presence");
			model.addAttribute("attribut_help", aideDeLaPage);	
			
			//3. renvoi du nom logique de la vue
			return "etudiants-cours/liste-presence";			
		
		}//end else
		
	}//end afficherListeAbsencesByEtudiant
	
	/**
	 * permet d'afficher la liste de présence en lien avec la personne connectée 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/liste", method=RequestMethod.GET)
	public String afficherListeAbsencesPersonne(ModelMap model, Authentication authentication) {
		
		//1. récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
			
		//2. récup de la liste des étudiants cours de la bdd via le service
		List<EtudiantCours> listeAbsenceByPersonneBdd = etudiantCoursService.findAbsencesPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole());
			
		//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_absence", listeAbsenceByPersonneBdd);
				
		//4. renvoi de la liste des cours et des étudiants et de la personne connectée vers la vue
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_liste_presence", etudiantCoursService.findEtudiantCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_cours", coursService.findCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_etudiants", etudiantsService.findEtudiantsByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));			
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("liste-presence");
		model.addAttribute("attribut_help", aideDeLaPage);			
		
		//5. renvoi du nom logique de la vue
		return "etudiants-cours/liste-presence";			
				
	}//end afficherListeEtudiantCoursByEtudiant
	
	/**
	 * permet d'afficher la liste de présence d'un cours de la bdd 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/etudiants-cours/recherche-cours", method=RequestMethod.GET)
	public String afficherListeEtudiantCoursByCours(@RequestParam("id-cours") Long pIdCours, ModelMap model, Authentication authentication) {
		
		//1. récup de la personne connectée
		Personnes personneConnecte = getInfosPersonneConnecte(authentication);
		
		if(pIdCours==0) {
			
			//2. récup de la liste des étudiants cours de la bdd via le service
			List<EtudiantCours> listeEtudiantCoursByPersonneBdd = etudiantCoursService.findEtudiantCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole());
				
			//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_presence", listeEtudiantCoursByPersonneBdd);
		
		}else {
					
			//2. récup de la liste de présence du cours de la bdd via le service
			List<EtudiantCours> listeEtudiantCoursByCoursBdd = etudiantCoursService.afficherEtudiantCoursByCours(pIdCours);
			
			//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_presence", listeEtudiantCoursByCoursBdd);
			
		}//end else
		
		//4. renvoi de la liste des cours et des étudiants et de la personne connectée vers la vue
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_liste_absence", etudiantCoursService.findAbsencesPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_cours", coursService.findCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		model.addAttribute("attribut_etudiants", etudiantsService.findEtudiantsByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));			
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("liste-presence");
		model.addAttribute("attribut_help", aideDeLaPage);			
		
		//5. renvoi du nom logique de la vue
		return "etudiants-cours/liste-presence";
		
	}//end afficherListeEtudiantCoursByCours
	
}//end class
