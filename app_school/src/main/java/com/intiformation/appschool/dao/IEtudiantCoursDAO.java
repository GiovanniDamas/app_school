package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;

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

	/**
	 * permet de récupérer l'ensemble des absences d'un etudiant dans la bdd
	 */
	public List<EtudiantCours> afficherAbsencesByEtudiant(Long pIdEtudiant);
	
	/*________________________________________________________________________________________________________________________*/
	
	/**
	 * permet de récup la liste des présneces de la bdd liées à un enseignant
	 */
	public List<EtudiantCours> afficherEtudiantCoursByEnseignant(Long pIdEnseignant);

	/**
	 * permet de récup la liste des absences de la bdd liées à un enseignant
	 */
	public List<EtudiantCours> afficherAbsencesByEnseignant(Long pIdEnseignant);

	/**
	 * permet de récup la liste des absences de la bdd 
	 */
	public List<EtudiantCours> getAllAbsences();

	/**
	 * permet de récupérer l'id d'un EtudiantCours à partir de l'étudiant et du cours
	 */
	public Long getIdEtudiantCours(Long pIdEtudiant, Long pIdCours);
	
}//end interface
