package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Cours;
import com.intiformation.appschool.modeles.EtudiantCours;
import com.intiformation.appschool.modeles.Etudiants;

/**
 * implémentation concrète de la DAO pour les étudiants cours
 * implémente IEtudiantCoursDAO
 * @author marle
 *
 */
@Repository //bean spring couche DAO
public class EtudiantCoursDAOImpl implements IEtudiantCoursDAO{

	//déclaration de la sessionFactory
	@Autowired //injection par type du bean sessionFactory dans cette propriété
	private SessionFactory sessionFactory;
	
	/**
	 * setter de la sessionFactory pour l'injection par modificateur de spring
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * permet d'ajouter un etudiantCours à la bdd
	 */
	@Transactional
	@Override
	public void add(EtudiantCours pEtudiantCours) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//ajout dans la bdd
			session.save(pEtudiantCours);
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		
		}//end catch
		
	}//end add

	/**
	 * permet de modifier un etudiantCours à la bdd
	 */
	@Transactional
	@Override
	public void update(EtudiantCours pEtudiantCours) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//modif dans la bdd
			session.update(pEtudiantCours);;
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la modif ...");
			throw e;
		
		}//end catch	
		
	}//end update

	/**
	 * permet de supprimer un etudiantCours à la bdd
	 */
	@Transactional
	@Override
	public void delete(Long pIdEtudiantCours) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//récup de EtudiantCours à supprimer
			EtudiantCours etudiantCoursToDelete = getById(pIdEtudiantCours);
			
			//suppression dans la bdd
			session.delete(etudiantCoursToDelete);
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la suppression ...");
			throw e;
		
		}//end catch
		
	}//end delete

	/**
	 * permet de récupérer un etudiantCours par son id dans la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public EtudiantCours getById(Long pIdEtudiantCours) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//récup par l'id dans la bdd
			return session.find(EtudiantCours.class, pIdEtudiantCours);
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la récup par l'id ...");
			throw e;
		
		}//end catch

	}//end getById

	/**
	 * permet de récupérer l'ensemble des etudiantCours de la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> getAll() {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query getAllQuery = session.createQuery("FROM EtudiantCours");
			
			//envoi, execution et récup résultat
			return getAllQuery.list();
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode getAll ...");
			throw e;
		
		}//end catch
	
	}//end getAll

	/**
	 * permet de récupérer l'ensemble des etudiantCours par cours de la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> afficherEtudiantCoursByCours(Long pIdCours) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<EtudiantCours> getByCoursQuery = session.createQuery("SELECT ec FROM EtudiantCours ec WHERE ec.cours.idCours = :pIdCours");
			
			//passage de paramètre
			getByCoursQuery.setParameter("pIdCours", pIdCours);
						
			//envoi, execution et récup résultat
			return getByCoursQuery.getResultList();			
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode afficherEtudiantCoursByCours ...");
			throw e;
		
		}//end catch
	
	}//end afficherEtudiantCoursByCours

	/**
	 * permet de récupérer l'ensemble des etudiantCours par etudiant de la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> afficherEtudiantCoursByEtudiant(Long pIdEtudiant) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<EtudiantCours> getByEtudiantQuery = session.createQuery("SELECT ec FROM EtudiantCours ec WHERE ec.etudiant.idPersonne = :pIdEtudiant");
			
			//passage de paramètre
			getByEtudiantQuery.setParameter("pIdEtudiant", pIdEtudiant);
			
			//envoi, execution et récup résultat
			return getByEtudiantQuery.getResultList();			
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode afficherEtudiantCoursByEtudiant ...");
			throw e;
		
		}//end catch
	
	}//end afficherEtudiantCoursByEtudiant
	
	/**
	 * permet de récupérer l'ensemble des absences d'un etudiant dans la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> afficherAbsencesByEtudiant(Long pIdEtudiant) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<EtudiantCours> getAbsenceByEtudiantQuery = session.createQuery("SELECT ec FROM EtudiantCours ec WHERE ec.absence = true AND ec.etudiant.idPersonne = :pIdEtudiant");
			
			//passage de paramètre
			getAbsenceByEtudiantQuery.setParameter("pIdEtudiant", pIdEtudiant);
			
			//envoi, execution et récup résultat
			return getAbsenceByEtudiantQuery.getResultList();			
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode afficherAbsencesByEtudiant ...");
			throw e;
		
		}//end catch
	
	}//end afficherAbsencesByEtudiant
	
	/*________________________________________________________________________________________________________________________*/
	
	/**
	 * permet de récup la liste des absences de la bdd liées à un enseignant
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> afficherAbsencesByEnseignant(Long pIdEnseignant) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<EtudiantCours> getAbsenceByEnseignantQuery = session.createQuery("SELECT ec FROM EtudiantCours ec, "
																			+ "Cours c, "
																			+ "EnseignantMatierePromotionLink link, "
																			+ "Matiere m "
																			+ "WHERE ec.cours.idCours = c.idCours "
																			+ "AND c.matieres.idMatiere = m.idMatiere "
																			+ "AND link.matiere.idMatiere = m.idMatiere "
																			+ "AND link.enseignant.idPersonne = :pIdEnseignant "
																			+ "AND ec.absence = true");
			
			//passage de paramètre
			getAbsenceByEnseignantQuery.setParameter("pIdEnseignant", pIdEnseignant);
			
			//envoi, execution et récup résultat
			return getAbsenceByEnseignantQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherAbsenceEnseignant ...");
			throw e;
		
		}//end catch
	}//end afficherAbsencesByEnseignant
	
	/**
	 * permet de récup la liste des absences de la bdd liées à un enseignant
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> afficherEtudiantCoursByEnseignant(Long pIdEnseignant) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<EtudiantCours> getAbsenceByEnseignantQuery = session.createQuery("SELECT ec FROM EtudiantCours ec, "
																			+ "Cours c, "
																			+ "EnseignantMatierePromotionLink link, "
																			+ "Matiere m "
																			+ "WHERE ec.cours.idCours = c.idCours "
																			+ "AND c.matieres.idMatiere = m.idMatiere "
																			+ "AND link.matiere.idMatiere = m.idMatiere "
																			+ "AND link.enseignant.idPersonne = :pIdEnseignant");
			
			//passage de paramètre
			getAbsenceByEnseignantQuery.setParameter("pIdEnseignant", pIdEnseignant);
			
			//envoi, execution et récup résultat
			return getAbsenceByEnseignantQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherEtudiantCoursByEnseignant ...");
			throw e;
		
		}//end catch
	}//end afficherEtudiantCoursByEnseignant
	
	/**
	 * permet de récupérer l'ensemble des absences de la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EtudiantCours> getAllAbsences() {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query getAllAbsencesQuery = session.createQuery("FROM EtudiantCours ec WHERE ec.absence = true");
			
			//envoi, execution et récup résultat
			return getAllAbsencesQuery.list();
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode getAllAbsences ...");
			throw e;
		
		}//end catch
	
	}//end getAllAbsences
	
	/**
	 * permet de récupérer l'id d'un EtudiantCours à partir de l'étudiant et du cours
	 */
	@Transactional(readOnly = true)
	@Override
	public Long getIdEtudiantCours(Long pIdEtudiant, Long pIdCours) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Long> getIdEtudiantCoursQuery = session.createQuery("SELECT ec.idEtudiantCours FROM EtudiantCours ec WHERE ec.etudiant.idPersonne = :pIdEtudiant AND ec.cours.idCours = :pIdCours");
			
			//passage de paramètre
			getIdEtudiantCoursQuery.setParameter("pIdEtudiant", pIdEtudiant);
			getIdEtudiantCoursQuery.setParameter("pIdCours", pIdCours);
			
			//envoi, execution et récup résultat
			return getIdEtudiantCoursQuery.getSingleResult();
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode getIdEtudiantCours ...");
			throw e;
		
		}//end catch
	
	}//end getIdEtudiantCours

}//end class
