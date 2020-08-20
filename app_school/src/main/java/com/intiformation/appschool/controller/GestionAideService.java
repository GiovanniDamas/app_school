package com.intiformation.appschool.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.dao.AideDAOImpl;
import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IAideService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;

/**
 * 
 * @author gabri
 *
 */
@Controller
public class GestionAideService {
	

	
	
	// _________________ PROPRIETES ___________________ //

		// Declaration de la couche Service:
		@Autowired // injectiion du bean dans la propriété 'matiereService'
		private IAideService aideService;
		
		@Autowired //injection par modificateur
		private IEnseignantsService enseignantsService;
		
		@Autowired //injection par modificateur
		private IAdministrateursService administrateursService;
		
		@Autowired //injection par modificateur
		private IEtudiantsService etudiantsService;		
		
		/**
		 * Setter de la couche service pour injection pour modificateur de Spring
		 * 
		 * @param aideService
		 */
		
		public void setAideService(IAideService aideService) {
			this.aideService = aideService;
		}//end setter	
				
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
		 * Méthode permettant de récupérer la liste des aides via le service, Méthode
		 * appellée lors d'une requête HTTP de type GET
		 * 
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/aide/listeAide", method = RequestMethod.GET)
		public String recupererListeAideBdd(ModelMap model, Authentication authentication) {

			// 1. recup de la liste des employés dans la bdd via le service

			List<Aide> listeAideBDD = aideService.findAll();

			// 2. renvoi de la liste vers la vue via l'objet model

			model.addAttribute("attribut_liste_aide", listeAideBDD);

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("liste-aide");
			model.addAttribute("attribut_help", aideDeLaPage);
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			// 3. renvoie du nom logique de la vue			
			return "liste-aide";

		}//end recupererListeAideBdd

		/**
		 * Méthode pour l'initialisation de l'ajout d'aide.
		 * Cette méthode permet d'afficher le formulaire permettant l'ajout d'une nouvelle Aide </br>
		 * Appelée via une requête HTTP de type GET
		 * 
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/aide/editAide", method = RequestMethod.GET)
		public String afficherFormulaireEdition(@RequestParam("idAide") Long pIdAide, ModelMap model, Authentication authentication) {

			if (pIdAide == 0) {

				// définition d'un objet de commande

				Aide aide = new Aide();
				
				// Renvoi de l'objet vers la vue

				model.addAttribute("attribut_aide", aide);
				model.addAttribute("idAide", pIdAide);
				model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			} else if (pIdAide != 0) {

				// Récup de l'aiide à récup

				Aide aideToUpdate = aideService.findAideById(pIdAide);

				// Renvoi de l'objet vers la vue

				model.addAttribute("attribut_aide", aideToUpdate);
				model.addAttribute("idAide", pIdAide);
				model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			} //end else

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("edit-aide");
			model.addAttribute("attribut_help", aideDeLaPage);			
			
			return "edit-aide";

		}//end afficherFormulaireEdition

		/**
		 * Méthode permettant d'ajouter l'aide à la bdd après soumission du
		 * formulaire </br>
		 * Appelée via une requête HTTP de type POST
		 * 
		 * @param pAide
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/aide/edit", method = RequestMethod.POST)
		public String ajoutAideBdd(@ModelAttribute("attribut_aide") Aide pAide, ModelMap model, Authentication authentication) {
			
			if (pAide.getIdAide() == null) {

				// Ajout etudiant via couche service

				aideService.ajouterAide(pAide);

				// Recup nouvelle liste d'etudiant après ajout

				model.addAttribute("attribut_liste_aide", aideService.findAll());
				model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));


			}else  {			

				// Modif etudiant via couche service

				aideService.modifierAide(pAide);

				// Recup nouvelle liste d'etudiant après ajout

				model.addAttribute("attribut_liste_aide", aideService.findAll());
				model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

				
			
			}//end if

			// aide de la page
			Aide aideDeLaPage = aideService.findAideByURL("liste-aide");
			model.addAttribute("attribut_help", aideDeLaPage);	
			
			return "liste-aide";
		}//end ajoutAideBdd

		/**
		 * Méthode permettant de supprimer l'étudiant de la bdd Appelée via une requête
		 * HTTP de type GET
		 * 
		 * @param pIdAide
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/aide/delete", method = RequestMethod.GET)
		public String supprimerAideBDD(@RequestParam("idAide") Long pIdAide, ModelMap model, Authentication authentication) {

			// Récup de l'aide à supprimer

			aideService.supprimerAide(pIdAide);

			// Récup nouvelle liste et envoie vers vue

			model.addAttribute("attribut_liste_aide", aideService.findAll());
			model.addAttribute("attribut_personne_connecte", welcomeController.getInfosPersonneConnecte(authentication));

			return "redirect:/aide/listeAide";

		}//end supprimerAideBDD
		

		@InitBinder
		public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		}// END Init Binder
		
		
}//end classe
