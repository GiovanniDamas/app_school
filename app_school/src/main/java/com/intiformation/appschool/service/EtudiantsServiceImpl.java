package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IEtudiantsDAO;
import com.intiformation.appschool.modeles.Etudiants;

/**
 * Classe concrètes de la couche service pour les étudiants
 * Classe implémentant l'interface IEtudiantService
 * @author giovanni
 *
 */
@Service("etudiantsServiceBean")
public class EtudiantsServiceImpl implements IEtudiantsService {

	// Déclaration de la couceh DAO
	@Autowired
	private IEtudiantsDAO etudiantsDAO;
	
	/**
	 * Déclaration du setter de la couche DAO pour l'injection par modificateur </br>
	 * @return
	 */
	public void setEtudiantsDAO(IEtudiantsDAO etudiantsDAO) {
		this.etudiantsDAO = etudiantsDAO;
	}

	@Override
	public void ajouterEtudiant(Etudiants pEtudiant) {
		etudiantsDAO.addEtudiant(pEtudiant);
	}

	@Override
	public void modifierEtudiant(Etudiants pEtudiant) {
		etudiantsDAO.updateEtudiant(pEtudiant);
	}

	@Override
	public void suppEtudiant(int pIdEtudiant) {
		etudiantsDAO.deleteEtudiant(pIdEtudiant);
	}

	@Override
	public Etudiants findEtudiantById(int pIdEtudiant) {
		return etudiantsDAO.getEtudiantById(pIdEtudiant);
	}

	@Override
	public List<Etudiants> findAllEtudiant() {
		return etudiantsDAO.getAllEtudiant();
	}

}//END CLASS
