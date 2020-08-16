package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.Promotion;

/**
 * Implémentation validateur spring pour l'objet de type Promotion
 * 
 * @author hannahlevardon
 *
 */
@Component
public class PromotionValidator implements Validator {

	/**
	 * Définition de l'objet à valider
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Promotion.class.isAssignableFrom(clazz);
	}// end supports

	/**
	 * Implémentation logique de la validation
	 * @param target: objet à valider
	 * @eris: gestion des erreurs de validation
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Le libellé de la promotion est obligatoire");

	}//end validate()

}// end class
