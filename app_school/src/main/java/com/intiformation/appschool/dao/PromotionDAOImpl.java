package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Matiere;
import com.intiformation.appschool.modeles.Promotion;

/**
 * <pre>
 * 	> Implémentation concrète de la couche DAO pour Matiere
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */
@Repository // Déclaration de la classe comme bean de Spring
public class PromotionDAOImpl implements IPromotionDAO {

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
	 * Méthode pour ajouter une promotion dans la database
	 * 
	 * @param pPromotion:
	 *            promotion à ajouter
	 */
	@Transactional
	@Override
	public void add(Promotion pPromotion) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.save(pPromotion);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (PromotionDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		} // end catch
	}// end add()

	/**
	 * Méthode pour modifier une promotion dans la database
	 * 
	 * @param pPromotion:
	 *            promotion à modifier
	 */
	@Transactional
	@Override
	public void update(Promotion pPromotion) {

		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Modification dans la database via méthode update()
			session.update(pPromotion);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (PromotionDAOImpl) Erreur lors de la modification ...");
			throw e;
		} // end catch
	}// end update()

	/**
	 * Méthode pour supprimer une promotion dans la database
	 * 
	 * @param pId:
	 *            Id de la promotion à supprimer
	 */
	@Transactional
	@Override
	public void delete(Long pIdPromotion) {

		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Récupération de la matière à modifier via son ID

			Promotion promotionToDelete = getById(pIdPromotion);

			// 3. Suppression de la database via la méthode delete()
			session.delete(promotionToDelete);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (PromotionDAOImpl) Erreur lors de la suppression ...");
			throw e;
		} // end catch

	}// end delete()

	/**
	 * Méthode pour récupérer une matière dans la database via son ID
	 * 
	 * @param pIdPromotion: ID de la promotion à récupérer
	 * @return promoToGet : la promotion 
	 */
	@Transactional(readOnly = true)
	@Override
	public Promotion getById(Long pIdPromotion) {

		// 1. Récupération de la session
				Session session = sessionFactory.getCurrentSession();
				
				try {
					// 2. Récupéraition de la matière dans la database
					Promotion promoToGet = session.find(Promotion.class, pIdPromotion); 
					return promoToGet; 
					
				} catch (HibernateException e) {
					// En cas d'erreur:
					System.out.println("... (PromotionDAOImpl) Erreur lors de la récupération par Id ...");
					throw e;
				}//end catch
	}// end getById()

	/**
	 * Méthode pour récupérer la liste de toutes les matières dans la database 
	 * 
	 * @return listePromotionsDB: la liste des promotions
	 */
	@Transactional(readOnly = true)
	@Override
	public List<Promotion> getAll() {
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query query = session.createQuery("FROM Promotion");

			// 3. Envoi, exécution, résultat
			List<Promotion> listePromotionsDB = query.list();

			return listePromotionsDB;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(" ... (EmployeDAOImpl) Erreur lors de la récupération de la liste des matières ...");
			throw e;
		} // end catch		
	}//end getAll()

}// end classe
