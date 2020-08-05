package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Adresse;
import com.intiformation.appschool.modeles.Aide;

/**
 * Implementation DAO pour les adresses
 * @author gabri
 *
 */
@Repository
public class AdresseDAOImpl implements IAdresseDAO {

	
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
	
	
	@Transactional
	@Override
	public void add(Adresse pAdresse) {
		
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.save(pAdresse);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AdresseDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		} // end catch
		
	}//end add

	
	@Transactional
	@Override
	public void update(Adresse pAdresse) {
		
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.update(pAdresse);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AdresseDAOImpl) Erreur la modif ...");
			throw e;
		} // end catch
		
	}//end update

	@Transactional
	@Override
	public void delete(Long pId) {
		
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Recup de l'adresse a supprimer avec getById()
			Adresse adresseToDel = getById(pId);
			
			// 3. suppression de l'adresse par son id et la methode delete()
			session.delete(adresseToDel);
			
			
		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AdresseDAOImpl) Erreur la suppression ...");
			throw e;
		} // end catch
		
	}//end delete

	
	@Transactional
	@Override
	public Adresse getById(Long pIdAdresse) {
		
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// 2. Récupéraition de la matière dans la database
			Adresse adresseToGet = session.find(Adresse.class, pIdAdresse); 
			return adresseToGet; 
			
		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AdresseDAOImpl) Erreur lors de la récupération par Id ...");
			throw e;
		}//end catch
		
	}// end getById()
	
	

	@Transactional
	@Override
	public List<Adresse> getAll() {
		
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query query = session.createQuery("FROM Adresse");

			// 3. Envoi, exécution, résultat
			List<Adresse> listAdresseBDD = query.list();

			return listAdresseBDD;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(" ... (AdresseDAOImpl) Erreur lors de la récupération de la liste des aides ...");
			throw e;
		} // end catch	
		
	}//end getAll

	@Transactional
	@Override
	public List<Adresse> getAdressesByPersonne(Long pIdPersonne) {
	
		
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query query = session.createQuery("SELECT P.adresses FROM Personnes P");

			// 3. Envoi, exécution, résultat
			List<Adresse> listAdresseBDD = query.list();

			return listAdresseBDD;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(" ... (AdresseDAOImpl) Erreur lors de la récupération de la liste des adresses par personne ...");
			throw e;
		} // end catch			
		
		return null;
	}//end getAdresseByPersonne

}//end class
