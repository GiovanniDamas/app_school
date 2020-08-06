package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.EtudiantCours;

/**
 * interface spécifique pour les étudiants des cours de la couche DAO
 * déclare les méthodes spécifiques aux étudiants_cours
 * hérite de IGenerique
 * @author marle
 *
 */
public interface IEtudiantCoursDAO extends IGenerique<EtudiantCours>{

	/**
	 * permet de récup la liste des EtudiantCours par cours de la bdd
	 * @param pIdCours : l'id du cours
	 * @return
	 */
	public List<EtudiantCours> afficherEtudiantCoursByCours(Long pIdCours);
	
	/**
	 * permet de récup la liste des EtudiantCours par étudiant de la bdd
	 * @param pIdEtudiant : l'id de l'étudiant
	 * @return
	 */
	public List<EtudiantCours> afficherEtudiantCoursByEtudiant(Long pIdEtudiant);
	
}//end interface
