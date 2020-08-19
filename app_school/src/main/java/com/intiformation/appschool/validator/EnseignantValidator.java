package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.Enseignants;
import com.intiformation.appschool.modeles.Etudiants;

/**
 * Validator pour les les formulaires des personnels : Administrateurs et Enseignants 
 * @author giovanni
 *
 */
@Component
public class EnseignantValidator implements Validator {

	/**
	 * permet de définir l'instance à valider
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Enseignants.class.isAssignableFrom(clazz);
	}

	/**
	 * implémentation de la logique de validation
	 * @param objetAValider :objet à valider
	 * @errors : pour gestion erreurs de validation
	 */
	@Override
	public void validate(Object Objet, Errors errors) {
		
		// validation du champ nom		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "required.nom", "Ce Champ est obligatoire");
		
		// validation du champ prenom
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "required.prenom", "Ce Champ est obligatoire");
		
		// validation du champ email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Ce Champ est obligatoire");
		
		// validation du champ identifiant
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identifiant", "required.identifiant", "Ce Champ est obligatoire");
		
		// validation du champ mdp
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "required.mdp", "Ce Champ est obligatoire");

	}//END METHODE


}
