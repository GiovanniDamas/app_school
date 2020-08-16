package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;

/**
 * <pre>
 * 	> Implémentation concrète de la couche DAO pour EnseignantMatierePromotionLink
 * 
 * </pre>
 * 
 * @author hannahlevardon
 *
 */

@Repository // déclaration de classe bean de Spring
public class EnseignantMatierePromotionLinkDAOImpl implements IEnseignantMatierePromotionLinkDAO {

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
	 * Méthode pour ajouter un lien entre matière, enseignant et promotion dans la
	 * database
	 * 
	 * @param pLink:
	 *            lien à ajouter
	 */
	@Transactional
	@Override
	public void add(EnseignantMatierePromotionLink pLink) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.save(pLink);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		} // end catch
	}// end add()

	/**
	 * Méthode pour modifier un lien entre matière, enseignant et promotion dans la
	 * database
	 * 
	 * @param pLink:
	 *            lien à modifier
	 */
	@Transactional
	@Override
	public void update(EnseignantMatierePromotionLink pLink) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.update(pLink);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de la modification ...");
			throw e;
		} // end catch
	}// end update()

	/**
	 * Méthode pour supprimer un lien entre matière, enseignant et promotion dans la
	 * database
	 * 
	 * @param pIdLink:
	 *            Id du lien à supprimer
	 */
	@Transactional
	@Override
	public void delete(Long pIdLink) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Récupération de la matière à modifier via son ID

			EnseignantMatierePromotionLink linkToDelete = getById(pIdLink);

			// 3. Suppression de la database via la méthode delete()
			session.delete(linkToDelete);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de la suppression ...");
			throw e;
		} // end catch
	}// end delete()

	/**
	 * Méthode pour récupérer un lien entre matière, enseignant et promotion dans la
	 * database via son ID
	 * 
	 * @param pIdLink:
	 *            ID de la matiere à récupérer
	 * @return: la lien à récupérer
	 */
	@Transactional(readOnly = true)
	@Override
	public EnseignantMatierePromotionLink getById(Long pIdLink) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Récupéraition de la matière dans la database
			EnseignantMatierePromotionLink linkToGet = session.find(EnseignantMatierePromotionLink.class, pIdLink);

			return linkToGet;

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de la récupération par Id ...");
			throw e;
		} // end catch
	}// end getById()

	/**
	 * Méthode pour récupérer la liste de toutes les liens dans la database
	 * 
	 * @return listeLinksDB: la liste des matières
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EnseignantMatierePromotionLink> getAll() {
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query<EnseignantMatierePromotionLink> query = session.createQuery("FROM EnseignantMatierePromotionLink");

			// 3. Envoi, exécution, résultat
			List<EnseignantMatierePromotionLink> listeLinksDB = query.list();

			return listeLinksDB;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(
					" ... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de la récupération de la liste des links ...");
			throw e;
		} // end catch
	}// end getAll()

	/**
	 * Méthode pour récupérer la liste des liens contenant l'id de l'enseignant
	 * 
	 * @return listeMatieresDB: la liste des matières
	 */
	@Transactional(readOnly = true)
	@Override
	public List<EnseignantMatierePromotionLink> getByIdEnseignant(Long pIdPromotion) {
		try {
			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query<EnseignantMatierePromotionLink> query = session
					.createQuery("SELECT l FROM EnseignantMatierePromotionLink l "
							+ "WHERE l.promotion.idPromotion = :pIdPromotion");

			// 3. Passage de paramètres
			
			query.setParameter("pIdPromotion", pIdPromotion);


			// 4. Envoi, exécution, résultat
			return query.getResultList();
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(
					" ... (EnseignantMatierePromotionLinkDAOImpl) Erreur lors de la récupération de la liste des links par Id des professeurs ...");
			throw e;
		} // end catch
	}// end getByIdEnseignant

}// end classe
