package com.intiformation.appschool.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.intiformation.appschool.modeles.Matiere;

/**
 * <pre>
 * 	> Implémentation concrète de la couche DAO pour Matiere
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */
@Repository // déclaration de classe bean de Spring
public class MatiereDAOImpl implements IMatiereDAO {

	// _________________ PROPRIETES ___________________ //

	// Déclaration de la session factory d'hibernate
	@Autowired // Injection par type du bean sessionFactory dans cette propriété
	private SessionFactory sessionFactory;

	/**
	 * Setter de la session factory pour injection par modificateur de Spring
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}// end setter

	// _________________ METHODES ___________________ //

	/**
	 * Méthode pour ajouter une matière dans la database
	 * 
	 * @param pMatiere:
	 *            matière à ajouter
	 */
	@Transactional
	@Override
	public void add(Matiere pMatiere) {

		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.save(pMatiere);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (MatiereDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		} // end catch
	}// end add()

	/**
	 * Méthode pour modifier une matière dans la database
	 * 
	 * @param pMatiere: matière à modifier 
	 */
	@Transactional
	@Override
	public void update(Matiere pMatiere) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Modification dans la database via méthode update()
			session.update(pMatiere);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (MatiereDAOImpl) Erreur lors de la modification ...");
			throw e;
		} // end catch
	}// end update

	/**
	 * Méthode pour supprimer une matière dans la database
	 * 
	 * @param pId: Id de la matière à supprimer
	 */
	@Transactional
	@Override
	public void delete(Long pId) {

		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Récupération de la matière à modifier via son ID

			Matiere matiereToDelete = getById(pId);

			// 3. Suppression de la database via la méthode delete()
			session.delete(matiereToDelete);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (MatiereDAOImpl) Erreur lors de la suppression ...");
			throw e;
		} // end catch
	}// end delete

	/**
	 * Méthode pour récupérer une matière dans la database via son ID
	 * @param pIdMatière: ID de la matiere à récupérer
	 * @return: la matiere 
	 */
	@Transactional(readOnly = true)
	@Override
	public Matiere getById(Long pIdMatière) {

		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// 2. Récupéraition de la matière dans la database
			Matiere matiereToGet = session.find(Matiere.class, pIdMatière); 
			
			return matiereToGet; 
			
		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (MatiereDAOImpl) Erreur lors de la récupération par Id ...");
			throw e;
		}//end catch 
	}// end getById

	/**
	 * Méthode pour récupérer la liste de toutes les matières dans la database 
	 * 
	 * @return listeMatieresDB: la liste des matières
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Matiere> getAll() {
		
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query query = session.createQuery("FROM Matiere");

			// 3. Envoi, exécution, résultat
			List<Matiere> listeMatieresDB = query.list();

			return listeMatieresDB;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(" ... (EmployeDAOImpl) Erreur lors de la récupération de la liste des matières ...");
			throw e;
		} // end catch

	}//end getAll()
	
	
	// _________________ METHODES EN COURS D'IMPLEMENTATION ___________________ //

	
	@Override
	public List<Matiere> getMatiereParPromotion(Long iDPromotion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matiere> getMatieresParEnseignant(Long iDEnseignant) {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
