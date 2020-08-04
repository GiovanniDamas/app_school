package com.intiformation.appschool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intiformation.appschool.dao.IAdministrateursDAO;
import com.intiformation.appschool.modeles.Administrateurs;

/**
 * Classe concrètes de la couche service pour les administrateurs
 * Classe implémentant l'interface IAdministrateursService
 * @author giovanni
 *
 */
@Service("administrateursServiceBean")
public class AdministrateursServiceImpl implements IAdministrateursService{
	
	//Déclaration de la couche DAO 
	private IAdministrateursDAO adminDAO;
	
	/**
	 * Déclaration du setter de la couche DAO pour l'injection par modificateur </br>
	 * @return
	 */
	public void setAdminDAO(IAdministrateursDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Override
	public void ajouterAdministrateur(Administrateurs pAdministrateur) {
		adminDAO.addAdministrateur(pAdministrateur);
	}

	@Override
	public void modifierAdministrateur(Administrateurs pAdministrateur) {
		adminDAO.updateAdministrateur(pAdministrateur);
	}

	@Override
	public void suppAdministrateur(int pIdAdministrateur) {
		adminDAO.deleteAdministrateur(pIdAdministrateur);
	}

	@Override
	public Administrateurs findAdministrateurById(int pIdAdministrateur) {
		return adminDAO.getAdministrateurById(pIdAdministrateur);
	}

	@Override
	public List<Administrateurs> findAllAdministrateur() {
		return adminDAO.getAllAdministrateur();
	}

}//END CLASS
