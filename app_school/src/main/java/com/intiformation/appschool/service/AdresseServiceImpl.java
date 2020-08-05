package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IAdresseDAO;
import com.intiformation.appschool.dao.IEtudiantsDAO;
import com.intiformation.appschool.modeles.Adresse;

/**
 * Implémentation concrète de la couche service pour les adresses.
 * 
 * @author gabri
 *
 */
@Service("adressesServiceBean")
public class AdresseServiceImpl implements IAdresseService{

	
	// Déclaration de la couceh DAO
	@Autowired
	private IAdresseDAO adresseDAO;
	
	/**
	 * Déclaration du setter de la couche DAO pour l'injection par modificateur </br>
	 * @return
	 */	
	public void setAdresseDAO(IAdresseDAO adresseDAO) {
		this.adresseDAO = adresseDAO;
	}



	@Override
	public void ajouterAdresse(Adresse pAdresse) {
		adresseDAO.add(pAdresse);
	}//end ajouterAdresse

	
	@Override
	public void modifierAdresse(Adresse pAdresse) {
		adresseDAO.update(pAdresse);
	}//end modifierAdresse

	
	@Override
	public void supprimerAdresse(Long pIdAdresse) {
		adresseDAO.delete(pIdAdresse);
	}//end supprimerAdresse

	
	@Override
	public Adresse findAdresseById(Long pIdAdresse) {
		return adresseDAO.getById(pIdAdresse);
	}//end findAdresseById

	
	@Override
	public List<Adresse> findAll() {
		return adresseDAO.getAll();
	}//end findAll

	
	@Override
	public List<Adresse> findAdresseByPersonne(Long pIdPersonne) {
		return adresseDAO.getAdressesByPersonne(pIdPersonne);
	}//end findAdresseByPersonne

		
}//end class
