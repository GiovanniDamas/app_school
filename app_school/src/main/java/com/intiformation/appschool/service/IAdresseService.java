package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.Adresse;

/**
 * Interface pour la couche service pour les adresses
 * @author gabri
 *
 */
public interface IAdresseService {

	
	public void ajouterAdresse(Adresse pAdresse);
	
	public void modifierAdresse(Adresse pAdresse);
	
	public void supprimerAdresse(Long pIdAdresse);
	
	public Adresse findAdresseById(Long pIdAdresse);
	
	public List<Adresse> findAll();
	
	public List<Adresse> findAdresseByPersonne(Long pIdPersonne);
	
}//end interface
