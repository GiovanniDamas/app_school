package com.intiformation.appschool.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.modeles.Promotion;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.ICoursService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantCoursService;
import com.intiformation.appschool.service.IEtudiantsService;
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

	//___ déclaration du service coursService
	@Autowired //injection par modificateur
	private ICoursService coursService;
	
	//___ déclaration du service de matière avec setter pour injection spring
	@Autowired //injection par modificateur
	private IMatiereService matiereService;

	//___ déclaration du service de promotion avec setter pour injection spring
	@Autowired //injection par modificateur
	private IPromotionService promotionService;
	
	//___ déclaration du service de enseignant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEnseignantsService enseignantsService;

	//____ déclaration du service de administrateur avec setter pour injection spring
	@Autowired //injection par modificateur
	private IAdministrateursService administrateursService;

	//___ déclaration du service de etudiant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEtudiantsService etudiantsService;

	//___ déclaration du service de etudiantcours avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEtudiantCoursService etudiantCoursService;

	@Autowired
	private IAideService aideService;

	/** 
	 * setter pour injection spring du service
	 * @param coursService
	 */
	public void setCoursService(ICoursService coursService) {
		this.coursService = coursService;
	}
	public void setMatiereService(IMatiereService matiereService) {
		this.matiereService = matiereService;
	}	
	public void setEnseignantsService(IEnseignantsService enseignantsService) {
		this.enseignantsService = enseignantsService;
	}	
	public void setPromotionService(IPromotionService promotionService) {
		this.promotionService = promotionService;
	}
	public void setAdministrateursService(IAdministrateursService administrateursService) {
		this.administrateursService = administrateursService;
	}
	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}	
	public void setEtudiantCoursService(IEtudiantCoursService etudiantCoursService) {
		this.etudiantCoursService = etudiantCoursService;
	}	
	
	public void setAideService(IAideService aideService) {
		this.aideService = aideService;
	}


	//___ déclaration du validateur
	@Autowired //injection par modificateur
	private CoursValidator coursValidator;

	/** 
	 * setter pour injection spring 
	 * @param coursValidator
	 */
	public void setCoursValidator(CoursValidator coursValidator) {
		this.coursValidator = coursValidator;
	}

	
	//déclaration WelcomeController pour recup infos personne connectée
	@Autowired
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}
		
	/*=================================================================*/
	/*======================= méthodes gestionnaires ==================*/
	/*=================================================================*/
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
	public String chargerCoursBdd(ModelMap model, Authentication authentication) {
		
		//1. création objet cours
		Cours cours = new Cours();
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_cours", cours);
		
		//3. récup de la personne connectée
		Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
		
		//4. renvoi de la liste des matières et des promotions et de la personne connectée vers la vue 
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
		model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("formulaire-ajout");
		model.addAttribute("attribut_help", aideDeLaPage);		
		
		
		//5. renvoi du nom logique de la vue
		return "cours/formulaire-ajout";
		
	}//end chargerCoursBdd
	
	/**
	 * permet d'ajouter un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/ajouter", method=RequestMethod.POST)
	public String ajouterCoursBdd(@ModelAttribute("attribut_cours") @Validated Cours pCours, BindingResult result, Authentication authentication, ModelMap model) {
		
			//1. application du validateur sur l'objet pCours
			coursValidator.validate(pCours, result);
					
			if (result.hasErrors()) {
				
				//récup de la personne connectée
				Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
				
				//renvoi de la liste des matières et des promotions et de la personne connectée vers la vue 
				model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
				model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));				

				// aide de la page
				Aide aideDeLaPage = aideService.findAideByURL("formulaire-ajout");
				model.addAttribute("attribut_help", aideDeLaPage);
				
				//2.a renvoi vers le formulaire
				return "cours/formulaire-ajout";
			
			}else {
				
				//2.b ajout du cours dans la bdd
				coursService.ajouterCours(pCours);

				// ajout des étudiants de la promo liés au cours dans la liste de présence
				Long idPromo = pCours.getPromotions().getIdPromotion(); 
				List<Etudiants> listeEtudiants = etudiantsService.findAllEtudiant();
				List<Etudiants> listeEtudiantsPromo = new ArrayList<>();

				if (listeEtudiants != null) {

					for (Etudiants etudiants : listeEtudiants) {
						
						if(etudiants.getPromotion() != null) {
							if (etudiants.getPromotion().getIdPromotion() == idPromo) {
								listeEtudiantsPromo.add(etudiants);
							}//end if
						}//end if
						
					}//end for each
					
					if (listeEtudiantsPromo != null) {

						for (Etudiants etudiants : listeEtudiantsPromo) {
							EtudiantCours etudiantCoursToAdd = new EtudiantCours();
							etudiantCoursToAdd.setCours(pCours);
							etudiantCoursToAdd.setEtudiant(etudiants);
							etudiantCoursService.ajouterEtudiantCours(etudiantCoursToAdd);
						}//end for each
						
					}//end if
					
				}//end if
							
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
	public String chargerModifCoursBdd(@RequestParam("coursId") Long pIdCours, ModelMap model, Authentication authentication) {
		
		//1. récupération du cours à modifier
		Cours coursToUpdate = coursService.findCoursById(pIdCours);
		
		//2. renvoi du cours vers la vue via l'objet model
		model.addAttribute("attribut_cours", coursToUpdate);
				
		//3. récup de la personne connectée
		Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
		
		//4. renvoi de la liste des matières et des promotions et de la personne connectée vers la vue 
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
		model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("formulaire-modif");
		model.addAttribute("attribut_help", aideDeLaPage);
		
		//5. renvoi du nom logique de la vue
		return "cours/formulaire-modif";
		
	}//end chargerModifCoursBdd
	
	/**
	 * permet de modifier un cours dans la bdd
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/modifier", method=RequestMethod.POST)
	public String modifierCoursBdd(@ModelAttribute("attribut_cours") @Validated Cours pCours, BindingResult result, Authentication authentication, ModelMap model) {
		
		//1. application du validateur sur l'objet pCours
		coursValidator.validate(pCours, result);
			
		if (result.hasErrors()) {

			//récup de la personne connectée
			Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
			
			//renvoi de la liste des matières et des promotions et de la personne connectée vers la vue 
			model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
			model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));				
			
			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("formulaire-modif");
			model.addAttribute("attribut_help", aideDeLaPage);
			
			//2.a renvoi vers le formulaire
			return "cours/formulaire-modif";
			
		}else {
				
				//vérification si modif de la promotion
				Cours coursUpdate = coursService.findCoursById(pCours.getIdCours());
				
				if (coursUpdate.getPromotions().equals(pCours.getPromotions())){
					//pas de modif dans etudiantcours
	
				} else {
					
					// suppression des étudiants de l'ancienne promo liée au cours dans etudiantcours
					Long idAnciennePromo = coursUpdate.getPromotions().getIdPromotion(); 
					List<Etudiants> listeEtudiants = etudiantsService.findAllEtudiant();
					List<Etudiants> listeEtudiantsAnciennePromo = new ArrayList<>();

					if (listeEtudiants != null) {

						for (Etudiants etudiants : listeEtudiants) {
							
							if(etudiants.getPromotion() != null) {
								if (etudiants.getPromotion().getIdPromotion() == idAnciennePromo) {
									listeEtudiantsAnciennePromo.add(etudiants);
								}//end if
							}//end if
							
						}//end for each
						
						if (listeEtudiantsAnciennePromo != null) {

							for (Etudiants etu : listeEtudiantsAnciennePromo) {
								etudiantCoursService.supprimerEtudiantCours(etudiantCoursService.findIdEtudiantCours(etu.getIdPersonne(), pCours.getIdCours()));
							}//end for each
							
						}//end if
						
					}//end if
												
					// ajout des étudiants de la nouvelle promo liés au cours dans etudiantcours
					Long idPromo = pCours.getPromotions().getIdPromotion(); 
					List<Etudiants> listeEtudiantsPromo = new ArrayList<>();

					if (listeEtudiants != null) {

						for (Etudiants etudiants : listeEtudiants) {
							
							if(etudiants.getPromotion() != null) {
								if (etudiants.getPromotion().getIdPromotion() == idPromo) {
									listeEtudiantsPromo.add(etudiants);
								}//end if
							}//end if
									
						}//end for each
						
						if (listeEtudiantsPromo != null) {

							for (Etudiants etudiants : listeEtudiantsPromo) {
								EtudiantCours etudiantCoursToAdd = new EtudiantCours();
								etudiantCoursToAdd.setCours(pCours);
								etudiantCoursToAdd.setEtudiant(etudiants);
								etudiantCoursService.ajouterEtudiantCours(etudiantCoursToAdd);
							}//end for each
							
						}//end if
						
					}//end if
					
				}//end else
									
				//2.b modif du cours dans la bdd
				coursService.modifierCours(pCours);
				
				//3. renvoi du nom logique de la vue
				return "redirect:/cours/liste";	
				
		}//end else		
		
	}//end modifierCoursBdd
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd associé à une personne
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/liste", method=RequestMethod.GET)
	public String afficherListeCoursByPersonne(ModelMap model, Authentication authentication) {
		
		//1. récup de la personne connectée
		Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
		System.out.println(personneConnecte.getNom());
		
		//2. récup de la liste des cours de la bdd via le service
		List<Cours> listeCoursByPersonneBdd = coursService.findCoursPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole());
		
		//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
		model.addAttribute("attribut_liste_cours", listeCoursByPersonneBdd);
				
		//4. renvoi de la liste des enseignants, des matières et des promotions vers la vue 
		model.addAttribute("attribut_personne_connecte", personneConnecte);
		model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
		model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
		
		// aide de la page
		Aide aideDeLaPage = aideService.findAideByURL("liste-cours");
		model.addAttribute("attribut_help", aideDeLaPage);
		
		//5. renvoi du nom logique de la vue
		return "cours/liste-cours";
				
	}//end afficherListeCoursByPersonne
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une matière associé à une personne
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-matiere", method=RequestMethod.GET)
	public String afficherListeCoursByMatiere(@RequestParam("id-matiere") Long pIdMatiere, ModelMap model, Authentication authentication) {
		
		if (pIdMatiere == 0) {
			
			return "redirect:/cours/liste";
		
		} else {
			
			//1. récup de la personne connectée
			Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 

			//2. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByMatiereBdd = coursService.findCoursPersonneMatiere(personneConnecte.getIdPersonne(), personneConnecte.getRole(), pIdMatiere);
			
			//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByMatiereBdd);
			
			//4. renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_personne_connecte", personneConnecte);
			model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
			model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
			
			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("liste-cours");
			model.addAttribute("attribut_help", aideDeLaPage);
			
			//5. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}
		
	}//end afficherListeCoursByMatiere
	
	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une promotion
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-promotion", method=RequestMethod.GET)
	public String afficherListeCoursByPromo(@RequestParam("id-promo") Long pIdPromotion, ModelMap model, Authentication authentication) {
		
		if (pIdPromotion == 0) {
			
			return "redirect:/cours/liste";
		
		} else {
			
			//1. récup de la personne connectée
			Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
			
			//2. récup de la liste des cours de la bdd via le service
			List<Cours> listeCoursByPromoBdd = coursService.findCoursPersonneByPromotion(personneConnecte.getIdPersonne(), pIdPromotion, personneConnecte.getRole());
			
			//3. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
			model.addAttribute("attribut_liste_cours", listeCoursByPromoBdd);
			
			//4. renvoi de la liste des matières et des promotions vers la vue 
			model.addAttribute("attribut_personne_connecte", personneConnecte);
			model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
			model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
			
			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("liste-cours");
			model.addAttribute("attribut_help", aideDeLaPage);
			
			//5. renvoi du nom logique de la vue
			return "cours/liste-cours";
			
		}//end else
		
	}//end afficherListeCoursByPromo

	/**
	 * permet d'afficher la liste de l'ensemble des cours de la bdd d'une date
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/cours/recherche-date", method=RequestMethod.GET)
	public String afficherListeCoursByDate(@RequestParam("date") String pDateString, ModelMap model, Authentication authentication) {
				
		try {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date pDate;
			pDate = formatter.parse(pDateString);
	
			if (pDate == null) {
			
				return "redirect:/cours/liste";
		
			} else {
				
				//1. récup de la personne connectée
				Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication); 
			
				//2. récup de la liste des cours de la bdd via le service
				List<Cours> listeCoursByDateBdd = coursService.findCoursPersonneByDate(personneConnecte.getIdPersonne(), personneConnecte.getRole(), pDate);
			
				//2. renvoi de la liste vers la vue via l'objet model de type 'ModelMap'
				model.addAttribute("attribut_liste_cours", listeCoursByDateBdd);
			
				// renvoi de la liste des matières et des promotions vers la vue 
				model.addAttribute("attribut_personne_connecte", personneConnecte);
				model.addAttribute("attribut_matieres", matiereService.findMatiereByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));					
				model.addAttribute("attribut_promotions", promotionService.findPromotionByPersonne(personneConnecte.getIdPersonne(), personneConnecte.getRole()));
				
				// aide de la page
				Aide aideDeLaPage = aideService.findAideByURL("liste-cours");
				model.addAttribute("attribut_help", aideDeLaPage);
				
				//3. renvoi du nom logique de la vue
				return "cours/liste-cours";
			
			}//end else
		
		} catch (ParseException e) {
			e.printStackTrace();
		}//end catch
		
		return "redirect:/cours/liste";
	
	}//end afficherListeCoursByDate
	
}//end class
