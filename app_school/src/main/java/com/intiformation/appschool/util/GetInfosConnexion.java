package com.intiformation.appschool.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import com.intiformation.appschool.modeles.Personnes;
import com.intiformation.appschool.service.IAdministrateursService;
import com.intiformation.appschool.service.IEnseignantsService;
import com.intiformation.appschool.service.IEtudiantsService;

/**
 * permet de récupérer les infos de la personne connectée
 * @author marle
 *
 */
public class GetInfosConnexion {

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
	
}//end class
