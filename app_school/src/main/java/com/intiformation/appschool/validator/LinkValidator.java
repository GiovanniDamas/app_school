package com.intiformation.appschool.validator;

import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;

@Component
public class LinkValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return EnseignantMatierePromotionLink.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotion.libelle", "required.libelle", "Le libellé de la promotion est obligatoire");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant.idPersonne", "required.idEnseignant", "La selection d'enseignant est obligatoire");
	//	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant", "required.enseignant", "L'enseignant est obligatoire");

	}

}
