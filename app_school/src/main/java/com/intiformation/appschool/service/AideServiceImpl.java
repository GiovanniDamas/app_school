package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IAideDAO;
import com.intiformation.appschool.modeles.Aide;

/**
 * Implémentation concrète de la couche service pour l'aide
 * 
 * @author gabri
 *
 */
@Service("aideServiceBean")
public class AideServiceImpl implements IAideService{

	@Autowired
	private IAideDAO aideDAO;
	
	/**
	 * Déclaration du setter de la couche DAO pour l'injection par modificateur </br>
	 * @return
	 */	
	public void setAideDAO(IAideDAO aideDAO) {
		this.aideDAO = aideDAO;
	}
	
	

	@Override
	public void ajouterAide(Aide pAide) {
		aideDAO.add(pAide);
	}//end ajouterAide

	@Override
	public void modifierAide(Aide pAide) {
		aideDAO.update(pAide);
	}//end modifierAide

	@Override
	public void supprimerAide(Long pIdAide) {
		aideDAO.delete(pIdAide);
	}//end supprimerAide

	@Override
	public Aide findAideById(Long pIdAide) {
		return aideDAO.getById(pIdAide);
	}//end findAideById

	@Override
	public List<Aide> findAll() {
		return aideDAO.getAll();
	}//end findAll



	@Override
	public Aide findAideByURL(String pURL) {
		return aideDAO.getByURL(pURL);
	}
	
	

}//end class
