package com.intiformation.appschool.dao;

import java.util.Date;
import java.util.List;

import com.intiformation.appschool.modeles.Cours;

/**
 * interface spécifique pour les cours de la couche DAO
 * déclare les méthodes spécifiques aux cours
 * hérite de IGenerique
 * @author marle
 *
 */
public interface ICoursDAO extends IGenerique<Cours> {

	/**
	 * permet de récup la liste des cours de la bdd par matière
	 * @param pIdMatiere : l'id de la matière
	 * @return
	 */
	public List<Cours> afficherCoursParMatiere(Long pIdMatiere);
	
	/**
	 * permet de récup la liste des cours de la bdd par promotion
	 * @param pIdPromotion : l'id de la promotion
	 * @return
	 */
	public List<Cours> afficherCoursParPromotion(Long pIdPromotion);
	
	/**
	 * permet de récup la liste des cours de la bdd par date
	 * @param pDate : la date
	 * @return
	 */
	public List<Cours> afficherCoursParDate(Date pDate);

	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant
	 * @param pIdEnseignant : l'enseignant
	 * @return
	 */
	public List<Cours> afficherCoursEnseignant(Long pIdEnseignant);

	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant
	 * @param pIdEtudiant : l'étudiant
	 * @return
	 */
	public List<Cours> afficherCoursEtudiant(Long pIdEtudiant);

	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant à une date donnée
	 */
	public List<Cours> afficherCoursEnseignantByDate(Long pIdEnseignant, Date pDate);
	
}//end interface
