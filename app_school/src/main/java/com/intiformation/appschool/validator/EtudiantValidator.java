package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.Etudiants;

/**
 * Validator pour le formulaire des etudiants 
 * @author giovanni
 *
 */
@Component
public class EtudiantValidator implements Validator {

	/**
	 * permet de définir l'instance à valider
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Etudiants.class.isAssignableFrom(clazz);
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
		
		// validation du champ date naissance		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDeNaissance", "required.dateN", "Ce Champ est obligatoire");
		
		// validation du champ email
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Ce Champ est obligatoire");
		
		// validation du champ identifiant
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "identifiant", "required.identifiant", "Ce Champ est obligatoire");
		
		// validation du champ mdp
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "required.mdp", "Ce Champ est obligatoire");
		

	}//END METHODE

}//END CLASS
