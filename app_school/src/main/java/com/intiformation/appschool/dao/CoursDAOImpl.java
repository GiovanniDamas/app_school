package com.intiformation.appschool.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Cours;

/**
 * implémentation concrète de la DAO pour les cours
 * implémente ICoursDAO
 * @author marle
 *
 */
@Repository //bean spring couche DAO
public class CoursDAOImpl implements ICoursDAO{

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
	 * permet d'ajouter un cours à la bdd
	 */
	@Transactional
	@Override
	public void add(Cours pCours) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//ajout dans la bdd
			session.save(pCours);
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		
		}//end catch
		
	}//end add

	/**
	 * permet de modifier un cours dans la bdd
	 */
	@Transactional
	@Override
	public void update(Cours pCours) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//modif dans la bdd
			session.update(pCours);
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la modification ...");
			throw e;
		
		}//end catch

	}//end update

	/**
	 * permet de supprimer un cours dans la bdd
	 */
	@Transactional
	@Override
	public void delete(Long pIdCours) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//récup du cours à supprimer
			Cours coursToDelete = getById(pIdCours);
			
			//suppression cours dans la bdd
			session.delete(coursToDelete);
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la suppression ...");
			throw e;
		
		}//end catch		
	}//end delete

	/**
	 * permet de récuperer un cours par son id dans la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public Cours getById(Long pIdCours) {
	
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//récup du cours par son id 
			return session.find(Cours.class, pIdCours);
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la récup par l'id ...");
			throw e;
		
		}//end catch			
	}//end getById

	/**
	 * permet de récupérer la liste de tous les cours dans la bdd
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> getAll() {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query getAllQuery = session.createQuery("FROM Cours");
			
			//envoi, execution et récup résultat
			return getAllQuery.list();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode getAll ...");
			throw e;
		
		}//end catch
		
	}//end getAll

	/**
	 * permet de récup la liste des cours de la bdd par matière
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursParMatiere(Long pIdMatiere) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getByMatiereQuery = session.createQuery("SELECT c FROM Cours c WHERE c.matieres.idMatiere = :pMatiereId");
			
			//passage de paramètre
			getByMatiereQuery.setParameter("pMatiereId", pIdMatiere);
			
			//envoi, execution et récup résultat
			return getByMatiereQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursParMatiere ...");
			throw e;
		
		}//end catch
	}//end afficherCoursParMatiere

	/**
	 * permet de récup la liste des cours de la bdd par promotion
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursParPromotion(Long pIdPromotion) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getByPromotionQuery = session.createQuery("SELECT c FROM Cours c WHERE c.promotions.idPromotion = :pPromotionId");
			
			//passage de paramètre
			getByPromotionQuery.setParameter("pPromotionId", pIdPromotion);
			
			//envoi, execution et récup résultat
			return getByPromotionQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursParPromotion ...");
			throw e;
		
		}//end catch
		
	}//end afficherCoursParPromotion

	/**
	 * permet de récup la liste des cours de la bdd par date
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursParDate(Date pDate) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getByDateQuery = session.createQuery("SELECT c FROM Cours c WHERE c.date = :pDate");
			
			//passage de paramètre
			getByDateQuery.setParameter("pDate", pDate);
			
			//envoi, execution et récup résultat
			return getByDateQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursParDate ...");
			throw e;
		
		}//end catch
		
	}//end afficherCoursParDate

}//end class
