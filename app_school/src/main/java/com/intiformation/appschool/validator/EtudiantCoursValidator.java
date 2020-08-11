package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;

/**
 * implémentation d'un validateur spring mvc
 * @author marle
 *
 */
@Component
public class EtudiantCoursValidator implements Validator {
	
	/**
	 * permet de définir l'instance à valider
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return EtudiantCours.class.isAssignableFrom(clazz);
	}

	/**
	 * implémentation de la logique de validation
	 * @param objetAValider :objet à valider
	 * @errors : pour gestion erreurs de validation
	 */
	@Override
	public void validate(Object objetAValider, Errors errors) {
		
		//1. validation du champ 'etudiant'
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant.idEtudiant", "required.etudiant", "le champ est obligatoire");
	
		//2. validation du champ 'cours'
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours.idCours", "required.cours", "le champ est obligatoire");
		
	}//end validate
}//end class
