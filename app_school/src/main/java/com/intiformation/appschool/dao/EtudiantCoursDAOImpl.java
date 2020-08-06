package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.EtudiantCours;

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
			Query<EtudiantCours> getByCoursQuery = session.createQuery("SELECT ec FROM EtudiantCours ec WHERE ec.cours = :pIdCours");
			
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
			Query<EtudiantCours> getByEtudiantQuery = session.createQuery("SELECT ec FROM EtudiantCours ec WHERE ec.etudiant = :pIdEtudiant");
			
			//passage de paramètre
			getByEtudiantQuery.setParameter("pEtudiant", pIdEtudiant);
			
			//envoi, execution et récup résultat
			return getByEtudiantQuery.getResultList();			
			
		} catch (HibernateException e) {
			
			System.out.println("... (EtudiantCoursDAOImpl) Erreur lors de la méthode afficherEtudiantCoursByEtudiant ...");
			throw e;
		
		}//end catch
	
	}//end afficherEtudiantCoursByEtudiant

}//end class
