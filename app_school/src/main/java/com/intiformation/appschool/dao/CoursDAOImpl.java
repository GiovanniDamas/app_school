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
	
	
	/*__________________________________________________________________________________________________________________*/
	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursEnseignant(Long pIdEnseignant) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getCoursByEnseignantQuery = session.createQuery("SELECT c FROM Cours c, "
																			+ "EnseignantMatierePromotionLink link, "
																			+ "Matiere m "
																			+ "WHERE c.matieres.idMatiere = m.idMatiere "
																			+ "AND link.matiere.idMatiere = m.idMatiere "
																			+ "AND link.enseignant.idPersonne = :pIdEnseignant");
			
			//passage de paramètre
			getCoursByEnseignantQuery.setParameter("pIdEnseignant", pIdEnseignant);
			
			//envoi, execution et récup résultat
			return getCoursByEnseignantQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEnseignant ...");
			throw e;
		
		}//end catch
	}//end afficherCoursEnseignant
	
	
	/**
	 * permet de récup la liste des cours de la bdd d'un etudiant
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursEtudiant(Long pIdEtudiant) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getCoursByEtudiantQuery = session.createQuery("SELECT c FROM Cours c, "
																			+ "Etudiants e, "
																			+ "Promotion p "
																			+ "WHERE c.promotions.idPromotion = p.idPromotion "
																			+ "AND p.idPromotion = e.promotion.idPromotion "
																			+ "AND e.idPersonne = :pIdEtudiant");
			
			//passage de paramètre
			getCoursByEtudiantQuery.setParameter("pIdEtudiant", pIdEtudiant);
			
			//envoi, execution et récup résultat
			return getCoursByEtudiantQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEtudiant ...");
			throw e;
		
		}//end catch
	}//end afficherCoursEtudiant
	
	/**
	 * permet de récup la liste des cours de la bdd d'un enseignant à une date donnée
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Cours> afficherCoursEnseignantByDate(Long pIdEnseignant, Date pDate) {
		
		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
		
		try {
			
			//construction requête HQL
			Query<Cours> getCoursEnseignantByDateQuery = session.createQuery("SELECT c FROM Cours c, "
																			+ "EnseignantMatierePromotionLink link, "
																			+ "Matiere m "
																			+ "WHERE c.matieres.idMatiere = m.idMatiere "
																			+ "AND link.matiere.idMatiere = m.idMatiere "
																			+ "AND link.enseignant.idPersonne = :pIdEnseignant "
																			+ "AND c.date = :pDate");
			
			//passage de paramètre
			getCoursEnseignantByDateQuery.setParameter("pIdEnseignant", pIdEnseignant);
			getCoursEnseignantByDateQuery.setParameter("pDate", pDate);
			
			//envoi, execution et récup résultat
			return getCoursEnseignantByDateQuery.getResultList();
			
		} catch (HibernateException e) {
			
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEnseignantByDate ...");
			throw e;
		
		}//end catch
	}//end afficherCoursEnseignantByDate

	/**
	 * permet de récup la liste des cours de la bdd par matière associés à une personne (enseignant)
	 * @param pIdMatiere : l'id de la matière
	 * @return
	 */
	@Override
	public List<Cours> afficherCoursEnseignantMatiere(Long pIdEnseignant, Long pIdMatiere) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
				
		try {
					
			//construction requête HQL
			Query<Cours> getCoursEnseignantByMatiereQuery = session.createQuery("SELECT c FROM Cours c, "
																					+ "EnseignantMatierePromotionLink link, "
																					+ "Matiere m "
																					+ "WHERE c.matieres.idMatiere = m.idMatiere "
																					+ "AND link.matiere.idMatiere = m.idMatiere "
																					+ "AND link.enseignant.idPersonne = :pIdEnseignant "
																					+ "AND m.idMatiere = :pIdMatiere");
					
			//passage de paramètre
			getCoursEnseignantByMatiereQuery.setParameter("pIdMatiere", pIdMatiere);
			getCoursEnseignantByMatiereQuery.setParameter("pIdEnseignant", pIdEnseignant);
					
			//envoi, execution et récup résultat
			return getCoursEnseignantByMatiereQuery.getResultList();
					
		} catch (HibernateException e) {
					
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEnseignantMatiere ...");
			throw e;
				
		}//end catch
	
	}//end afficherCoursEnseignantMatiere
	
	/**
	 * permet de récup la liste des cours de la bdd par matière associés à une personne (etudiant)
	 * @param pIdMatiere : l'id de la matière
	 * @return
	 */
	@Override
	public List<Cours> afficherCoursEtudiantMatiere(Long pIdEtudiant, Long pIdMatiere) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
				
		try {
					
			//construction requête HQL
			Query<Cours> getCoursEtudiantByMatiereQuery = session.createQuery("SELECT c FROM Cours c, "
																						+ "EnseignantMatierePromotionLink link, "
																						+ "Promotion p "
																						+ "WHERE c.promotions.idPromotion = p.idPromotion "
																						+ "AND link.promotion.idPromotion = p.idPromotion "
																						+ "AND link.etudiant.idPersonne = :pIdEtudiant "
																						+ "AND c.matieres.idMatiere = :pIdMatiere");
			//passage de paramètre
			getCoursEtudiantByMatiereQuery.setParameter("pIdMatiere", pIdMatiere);
			getCoursEtudiantByMatiereQuery.setParameter("pIdEtudiant", pIdEtudiant);
					
			//envoi, execution et récup résultat
			return getCoursEtudiantByMatiereQuery.getResultList();
					
		} catch (HibernateException e) {
					
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEtudiantMatiere ...");
			throw e;
				
		}//end catch
	
	}//end afficherCoursEtudiantMatiere
	
	/**
	 * permet de récup la liste des cours de la bdd par promotion associés à une personne (enseignant)
	 * @param pIdPromotion : l'id de la promotion
	 * @return
	 */
	@Override
	public List<Cours> afficherCoursEnseignantByPromotion(Long pIdEnseignant, Long pIdPromotion) {

		//récup de la session d'hibernate
		Session session = this.sessionFactory.getCurrentSession();
				
		try {
					
			//construction requête HQL
			Query<Cours> getCoursEnseignantByPromoQuery = session.createQuery("SELECT c FROM Cours c, "
																					+ "EnseignantMatierePromotionLink link, "
																					+ "Matiere m "
																					+ "WHERE c.matieres.idMatiere = m.idMatiere "
																					+ "AND link.matiere.idMatiere = m.idMatiere "
																					+ "AND link.enseignant.idPersonne = :pIdEnseignant "
																					+ "AND c.promotions.idPromotion = :pIdPromotion");
					
			//passage de paramètre
			getCoursEnseignantByPromoQuery.setParameter("pIdPromotion", pIdPromotion);
			getCoursEnseignantByPromoQuery.setParameter("pIdEnseignant", pIdEnseignant);
					
			//envoi, execution et récup résultat
			return getCoursEnseignantByPromoQuery.getResultList();
					
		} catch (HibernateException e) {
					
			System.out.println("... (CoursDAOImpl) Erreur lors de la méthode afficherCoursEnseignantByPromotion ...");
			throw e;
				
		}//end catch
	
	}//end afficherCoursEnseignantByPromotion

}//end class
