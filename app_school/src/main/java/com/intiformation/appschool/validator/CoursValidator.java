package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.Cours;

/**
 * implémentation d'un validateur spring mvc
 * @author marle
 *
 */
@Component
public class CoursValidator implements Validator {

	/**
	 * permet de définir l'instance à valider
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Cours.class.isAssignableFrom(clazz);
	}

	/**
	 * implémentation de la logique de validation
	 * @param objetAValider :objet à valider
	 * @errors : pour gestion erreurs de validation
	 */
	@Override
	public void validate(Object objetAValider, Errors errors) {
		
		//1. validation du champ 'libelle'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "le champ est obligatoire");
	
		//2. validation du champ 'date'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "required.date", "le champ est obligatoire");
		
		//3. validation du champ 'duree'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duree", "required.duree", "le champ est obligatoire");
		
		//4. validation du champ 'description'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "le champ est obligatoire");
		
		//5. validation du champ 'matiere'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matieres.idMatiere", "required.matiere", "le champ est obligatoire");

		//6. validation du champ 'promotion'
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotions.idPromotion", "required.promotion", "le champ est obligatoire");
		
	}//end validate
	
}//end class
