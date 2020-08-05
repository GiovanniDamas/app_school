package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Aide;

/**
 * Interface de la couche service pour l'aide
 *  
 * @author gabri
 *
 */
public interface IAideService {

	public void ajouterAide(Aide pAide);
	
	public void modifierAide(Aide pAide);
	
	public void supprimerAide(Long pIdAide);
	
	public Aide findAideById(Long pIdAide);
	
	public List<Aide> findAll();
	
	
}//end interface
