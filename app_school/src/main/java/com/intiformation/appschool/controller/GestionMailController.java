package com.intiformation.appschool.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.intiformation.appschool.modeles.Personnes;
/**
 * controller pour la gestion de mails
 * @author marle
 *
 */
@Controller
public class GestionMailController {
	
	//déclaration WelcomeController pour recup infos personne connectée
	@Autowired
	private WelcomeController welcomeController;
	
	public void setWelcomeController(WelcomeController welcomeController) {
		this.welcomeController = welcomeController;
	}	

	
	 @Autowired
	 private JavaMailSender mailSender;
	     
	 @RequestMapping(value="/sendEmail/do", method = RequestMethod.POST)
	 public String doSendEmail(HttpServletRequest request) {
	        
		 // takes input from e-mail form
	        String recipientAddress = request.getParameter("recipient");
	        String subject = request.getParameter("subject");
	        String message = request.getParameter("message");
	        String sender = request.getParameter("sender");
	         
	        // prints debug info
	        System.out.println("To: " + recipientAddress);
	        System.out.println("Subject: " + subject);
	        System.out.println("Message: " + message);
	        System.out.println("Sender: " + sender);

	        // creates a simple e-mail object
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipientAddress);
	        email.setSubject(subject);
	        email.setText(message);
	        email.setReplyTo(sender);
	         
	        // sends the e-mail
	        mailSender.send(email);
	         
	        // forwards to the view named "Result"
	        return "mail/results";
	        
	    }//end doSendEmail
	 
	 /**
	  * permet d'afficher le formulaire pour l'envoi d'un mail
	  * @return
	  */
	 @RequestMapping(value="/sendEmail/form", method=RequestMethod.GET)
	 public String afficherFormulaireMail(@RequestParam("destinator") String pMail, ModelMap model, Authentication authentication) {
			
			//1. renvoi du mail du destinataire vers la vue via l'objet model
			model.addAttribute("attribut_destinataire", pMail);
					
			//2. récup de la personne connectée et renvoi vers la vue
			Personnes personneConnecte = welcomeController.getInfosPersonneConnecte(authentication);
			model.addAttribute("attribut_personne_connecte", personneConnecte);
			
			//3. renvoi du nom logique de la vue
			return "mail/envoi-email";
			
	 }//end afficherFormulaireMail
}//end class
