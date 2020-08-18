package com.intiformation.appschool.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;


/**
 * controller pour l'envoi de mail
 * @author marle
 *
 */
@Controller
public class GestionMailController {

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

	//___ déclaration du service de etudiant avec setter pour injection spring
	@Autowired //injection par modificateur
	private IEtudiantsService etudiantsService;

	public void setEtudiantsService(IEtudiantsService etudiantsService) {
		this.etudiantsService = etudiantsService;
	}
	
	private Personnes personneConnecte;
	
	/**
	 * méthode qui permet de récupérer les informations de la personne connectée
	 * @param authentication
	 * @return
	 */
	public Personnes getInfosPersonneConnecte(Authentication authentication) {
				
		if(authentication == null) {
			
			personneConnecte = null;
			
		} else if (authentication != null) {
			
			if (authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {
				
				//1. cas d'un admin : récupération de l'administrateur connecté
				personneConnecte = administrateursService.findAdministrateurByIdentifiant(authentication.getName());
		
			} else if (authentication.getAuthorities().toString().contains("ROLE_ENSEIGNANT")) {
				
				//1. cas d'un enseignant : récupération de l'enseignant connecté
				personneConnecte = enseignantsService.findEnseignantByIdentifiant(authentication.getName());
				
			} else if (authentication.getAuthorities().toString().contains("ROLE_ETUDIANT")) {
				
				//1. cas d'un etudiant : récupération de l'eutidnat connecté
				personneConnecte = etudiantsService.findEtudiantByIdentifiant(authentication.getName());
			}//end else if
			
		}//end else if
		
		return personneConnecte;
		
	}//end getInfosPersonneConnecte
	
	 @Autowired
	 private JavaMailSender mailSender;
	     
	 @RequestMapping(value="/sendEmail.do", method = RequestMethod.POST)
	 public String doSendEmail(HttpServletRequest request) {
	        
		 // takes input from e-mail form
	        String recipientAddress = request.getParameter("recipient");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");
	         
	        // prints debug info
	        System.out.println("To: " + recipientAddress);
	        System.out.println("Subject: " + subject);
	        System.out.println("Message: " + message);
	         
	        // creates a simple e-mail object
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message);
	         
	        // sends the e-mail
	        mailSender.send(email);
	         
	        // forwards to the view named "Result"
	        return "Result";
	        
	    }//end doSendEmail
	 
	 /**
	  * permet d'afficher le formulaire pour l'envoi d'un mail
	  * @return
	  */
	 @RequestMapping(value="gestionMail/send", method=RequestMethod.POST)
	 public String afficherFormulaireMail(@RequestParam("destinator") String pMail, ModelMap model, Authentication authentication) {
			
			//1. renvoi du mail du destinataire vers la vue via l'objet model
			model.addAttribute("attribut_destinataire", pMail);
					
			//2. récup de la personne connectée et renvoi vers la vue
			Personnes personneConnecte = getInfosPersonneConnecte(authentication);
			model.addAttribute("attribut_personne_connecte", personneConnecte);
			
			//3. renvoi du nom logique de la vue
			return "EmailForm";
			
	 }//end afficherFormulaireMail
	 
	 
	 
	    
}//end class
