package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IEnseignantsDAO;
import com.intiformation.appschool.modeles.Enseignants;

/**
 * Classe concrètes de la couche service pour les enseignants
 * Classe implémentant l'interface IEnseignantsService
 * @author giovanni
 *
 */
@Service("enseignantsServiceBean")
public class EnseignantsServiceImpl implements IEnseignantsService{
	
	//Déclaration de la couche DAO
	private IEnseignantsDAO enseignantsDAO;
	
	/**
	 * Déclaration du setter de la couche DAO pour l'injection par modificateur </br>
	 * @return
	 */
	public void setEnseignantsDAO(IEnseignantsDAO enseignantsDAO) {
		this.enseignantsDAO = enseignantsDAO;
	}

	
	@Override
	public void ajouterEnseignant(Enseignants pEnseignant) {
		enseignantsDAO.addEnseignant(pEnseignant);
	}

	@Override
	public void modifierEnseignant(Enseignants pEnseignant) {
		enseignantsDAO.updateEnseignant(pEnseignant);
	}

	@Override
	public void suppEnseignant(int pIdEnseignant) {
		enseignantsDAO.deleteEnseignant(pIdEnseignant);
	}

	@Override
	public Enseignants findEnseignantById(int pIdEnseignant) {
		return enseignantsDAO.getEnseignantById(pIdEnseignant);
	}

	@Override
	public List<Enseignants> findAllEnseignant() {
		return enseignantsDAO.getAllEnseignant();
	}

}//END CLASS
