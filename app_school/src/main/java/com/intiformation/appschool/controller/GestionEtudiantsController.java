package com.intiformation.appschool.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;
import com.intiformation.appschool.modeles.Promotion;
import com.intiformation.appschool.service.ICoursService;
import com.intiformation.appschool.service.IEtudiantCoursService;
import com.intiformation.appschool.service.IEtudiantsService;
import com.intiformation.appschool.validator.EtudiantValidator;
import com.intiformation.appschool.service.IPromotionService;

/**
 * @author giovanni
 *
 */
@Controller
public class GestionEtudiantsController {

	private static final String UPLOAD_DIRECTORY = "/Users/giovanni/Desktop/FormationJAVA/projet_app_school /app_school/app_school/src/main/webapp/resources/Images";

	private Etudiants etudiants;
	// Déclaration de la couche service Etudiants
	@Autowired
	private IEtudiantsService etudiantsService;

	/**
	 * Déclaration du setter de etudiantsService pour l'injection par modificateur
	 * </br>
	 * 
	 * @param etudiantService
	 */
	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}
	
	//déclartion couche service promotion
	@Autowired
	private IPromotionService promotionService;

	/**
	 * setter de promotionservice pour injection par modificateur
	 * @param promotionService
	 */
	public void setPromotionService(IPromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	//déclaration couche service de etudiantcours 
	@Autowired 
	private IEtudiantCoursService etudiantCoursService;

	/**
	 * setter de etudiantCoursService pour injection par modificateur
	 * @param etudiantCoursService
	 */
	public void setEtudiantCoursService(IEtudiantCoursService etudiantCoursService) {
		this.etudiantCoursService = etudiantCoursService;
	}
	
	// déclaration du service coursService
	@Autowired 
	private ICoursService coursService;

	/** 
	 * setter de coursService pour injection spring par modificateur
	 * @param coursService
	 */
	public void setCoursService(ICoursService coursService) {
		this.coursService = coursService;
	}
	

	// déclaration du validateur
	@Autowired
	private EtudiantValidator etudiantValidator;

	/**
	 * déclaration du setter du validator pour l'injection par modificateur
	 * 
	 * @param etudiantValidator
	 */
	public void setEtudiantValidator(EtudiantValidator etudiantValidator) {
		this.etudiantValidator = etudiantValidator;
	}

	/**
	 * Méthode permettant de récupérer la liste des etudiants via le service,
	 * Méthode appellée lors d'une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/listeEtudiants", method = RequestMethod.GET)
	public String recupererListeEtudiantBdd(ModelMap model) {

		// 1. recup de la liste des employés dans la bdd via le service

		List<Etudiants> listeEtudiantsBDD = etudiantsService.findAllEtudiant();

		// 2. renvoi de la liste vers la vue via l'objet model

		model.addAttribute("attribut_liste_etudiants", listeEtudiantsBDD);

		// 3. renvoie du nom logique de la vue

		return "personnels/listeEtudiants";

	}// END RECUP LISTE

	/**
	 * Méthode pour l'initialisation de l'ajout d'étudiant Cette méthode permet
	 * d'afficher le formulaire permettant l'ajout d'un nouvel Etudiant </br>
	 * Appelée via une requête HTTP de type GET
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/form-edit", method = RequestMethod.GET)
	public String afficherFormulaireEdition(@RequestParam("idPersonne") Long pIdEtudiant, ModelMap model) {

		if (pIdEtudiant == 0) {

			// définition d'un objet de commande

			Etudiants etudiant = new Etudiants();

			// Renvoi de l'objet vers la vue
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());
			model.addAttribute("attribut_etudiants", etudiant);
			model.addAttribute("idPersonne", pIdEtudiant);

		} else if (pIdEtudiant != 0) {

			// Récup de l'étudiant à récup

			Etudiants etudiantToUpdate = etudiantsService.findEtudiantById(pIdEtudiant);

			// Renvoi de l'objet vers la vue
			model.addAttribute("attribut_promotions", promotionService.trouverAllPromotions());
			model.addAttribute("attribut_etudiants", etudiantToUpdate);
			model.addAttribute("idPersonne", pIdEtudiant);
			model.addAttribute("photo", etudiantToUpdate.getPhoto());

		} // END IF ELSE IF

		return "personnels/formulaireEditionEtudiants";

	}// END METHODE

	/**
	 * Méthode permettant d'ajouter l'étudiant à la bdd après soumission du
	 * formulaire </br>
	 * Appelée via une requête HTTP de type POST
	 * 
	 * @param pEtudiant
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/gestionEtudiants/edit", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public String ajoutEtudiantBdd(@ModelAttribute("attribut_etudiants") Etudiants pEtudiant,
			@RequestParam(name = "file", required = false) MultipartFile file, HttpServletResponse httpServletResponse,
			ModelMap model, BindingResult result) {

		etudiantValidator.validate(pEtudiant, result);

		if (result.hasErrors()) {
			
			// renvoie du formulaire
			return "personnels/formulaireEditionEtudiants";

		} else if (pEtudiant.getIdPersonne() == null) {

			String nomPhoto = file.getOriginalFilename();

			pEtudiant.setPhoto(nomPhoto);

			String filePath = UPLOAD_DIRECTORY + nomPhoto;

			File dest = new File(filePath);

			try {
				file.transferTo(dest);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				return "File uploaded failed:" + nomPhoto;
			}

			// recup mdp
			String MDP = pEtudiant.getMotDePasse();

			// objet pour le cryptage
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

			// cryptage du mot de passe avec la méthode encode()
			String hashedMotDePasse = passwordEncoder.encode(MDP);

			// passage du mdp crypté
			pEtudiant.setMotDePasse(hashedMotDePasse);

			// passage du role
			pEtudiant.setRole("ROLE_ETUDIANT");

			// Ajout etudiant via couche service

			etudiantsService.ajouterEtudiant(pEtudiant);
			
			// ajout de l'étudiants dans la liste de présence des cours de sa promo
			Long idPromo = pEtudiant.getPromotion().getIdPromotion();
			List<Cours> listeCoursPromo = coursService.findCoursParPromotion(idPromo);
				
			if (listeCoursPromo != null ) {
				
				for (Cours cours : listeCoursPromo) {
					
					EtudiantCours etudiantCoursToAdd = new EtudiantCours();
					etudiantCoursToAdd.setCours(cours);
					etudiantCoursToAdd.setEtudiant(pEtudiant);
					etudiantCoursService.ajouterEtudiantCours(etudiantCoursToAdd);
					
				}//end for each
				
			}//end if

			// Recup nouvelle liste d'etudiant après ajout

			model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

			return "personnels/listeEtudiants";

		}

		if (pEtudiant.getIdPersonne() != 0) {

			if (file.isEmpty()) {

				pEtudiant.getPhoto();
				
				//vérification si modif de la promotion
				Etudiants etudiantUpdate = etudiantsService.findEtudiantById(pEtudiant.getIdPersonne());
				
				if (etudiantUpdate.getPromotion().equals(pEtudiant.getPromotion())){
					//pas de modif dans etudiantcours
				
				} else {
					
					// suppression des cours de l'ancienne promo de l'étudiant dans etudiantcours
					Long idAnciennePromo = etudiantUpdate.getPromotion().getIdPromotion();
					List<Cours> anciensCours = coursService.findCoursParPromotion(idAnciennePromo);
										
					if (anciensCours != null ) {
						for (Cours cours : anciensCours) {
							etudiantCoursService.supprimerEtudiantCours(etudiantCoursService.findIdEtudiantCours(pEtudiant.getIdPersonne(), cours.getIdCours()));
						}//end for each
						
					}//end if
								
					// ajout des cours de la nouvelle promo de l'étudiant dans etudiantcours
					Long idNouvellePromo = pEtudiant.getPromotion().getIdPromotion();
					List<Cours> listeCoursPromo = coursService.findCoursParPromotion(idNouvellePromo); 
					
					if (listeCoursPromo != null ) {
						
						for (Cours cours : listeCoursPromo) {
							
							EtudiantCours etudiantCoursToAdd = new EtudiantCours();
							etudiantCoursToAdd.setCours(cours);
							etudiantCoursToAdd.setEtudiant(pEtudiant);
							etudiantCoursService.ajouterEtudiantCours(etudiantCoursToAdd);

						}//end for each
					}//end if
					
				}// end else			

				// Modif etudiant via couche service

				etudiantsService.modifierEtudiant(pEtudiant);

				// Recup nouvelle liste d'etudiant après ajout

				model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

			} else {
				String nomPhoto = file.getOriginalFilename();

				pEtudiant.setPhoto(nomPhoto);

				String filePath = UPLOAD_DIRECTORY + nomPhoto;

				File dest = new File(filePath);

				try {
					file.transferTo(dest);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
					return "File uploaded failed:" + nomPhoto;
				}

				// recup mdp
				String MDP = pEtudiant.getMotDePasse();

				// objet pour le cryptage
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				// cryptage du mot de passe avec la méthode encode()
				String hashedMotDePasse = passwordEncoder.encode(MDP);

				// passage du mdp crypté
				pEtudiant.setMotDePasse(hashedMotDePasse);

				// passage du role
				pEtudiant.setRole("ROLE_ETUDIANT");

				// Modif etudiant via couche service

				etudiantsService.modifierEtudiant(pEtudiant);

				// Recup nouvelle liste d'etudiant après ajout

				model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

			} // END if

		} // END IF idPersonne !=0

		return "personnels/listeEtudiants";

	}// END METHODE

	/**
	 * Méthode permettant de supprimer l'étudiant de la bdd Appelée via une requête
	 * HTTP de type GET
	 * 
	 * @param pIdEtudiant
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gestionEtudiants/delete", method = RequestMethod.GET)
	public String supprimerEtudiantsBDD(@RequestParam("idPersonne") Long pIdEtudiant, ModelMap model) {

		// Récup de l'étudiant à supprimer

		etudiantsService.suppEtudiant(pIdEtudiant);

		// Récup nouvelle liste et envoie vers vue

		model.addAttribute("attribut_liste_etudiants", etudiantsService.findAllEtudiant());

		return "personnels/listeEtudiants";

	}// END SUPPRIMER


}// END CLASS
