package com.intiformation.appschool.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Aide;
import com.intiformation.appschool.modeles.Promotion;


/**
 * DAO pour l'aide
 * @author gabri
 *
 */
@Repository // Déclaration de la classe comme bean de Spring
public class AideDAOImpl  implements IAideDAO{

	/*_________________props___________________*/

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
	
	
	/*_________________meths___________________*/
	@Transactional
	@Override
	public void add(Aide pAide ) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Ajout dans la database via méthode save()
			session.save(pAide);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AideDAOImpl) Erreur lors de l'ajout ...");
			throw e;
		} // end catch
		
	}//end add
	
	

	@Transactional
	@Override
	public void update(Aide pAide) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Modification dans la database via méthode update()
			session.update(pAide);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AideDAOImpl) Erreur lors de la modification ...");
			throw e;
		} // end catch
	}// end update()

	
	@Transactional
	@Override
	public void delete(Long pIdAide) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		try {
			// 2. Récupération de la matière à modifier via son ID

			Aide aideToDelete = getById(pIdAide);

			// 3. Suppression de la database via la méthode delete()
			session.delete(aideToDelete);

		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AideDAOImpl) Erreur lors de la suppression ...");
			throw e;
		} // end catch

	}// end delete()

	@Transactional
	@Override
	public Aide getById(Long pIdAide) {
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();
		
		try {
			// 2. Récupéraition de la matière dans la database
			Aide aideToGet = session.find(Aide.class, pIdAide); 
			return aideToGet; 
			
		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AideDAOImpl) Erreur lors de la récupération par Id ...");
			throw e;
		}//end catch
		
	}// end getById()

	
	@Transactional
	@Override
	public List<Aide> getAll() {
		
		try {

			// 1. Récupération de la session
			Session session = sessionFactory.getCurrentSession();

			// 2. Définition de la requete HQL à envoyer
			Query query = session.createQuery("FROM Aide");

			// 3. Envoi, exécution, résultat
			List<Aide> listAideBDD = query.list();

			return listAideBDD;
		} catch (HibernateException e) {

			// En cas d'erreur:
			System.out.println(" ... (AideDAOImpl) Erreur lors de la récupération de la liste des aides ...");
			throw e;
		} // end catch		
		
	}//end getAll()

	@Transactional
	@Override
	public Aide getByURL(String pUrl) {
		
		// 1. Récupération de la session
		Session session = sessionFactory.getCurrentSession();

		// 2. Définition de la requete HQL à envoyer
		Query query = session.createQuery("SELECT a FROM Aide a WHERE a.urlPage LIKE concat('%', :pUrl,'%') ");		

		query.setParameter("pUrl", pUrl);
		
		try {
			// 3. Envoi, exécution, résultat
			
			List<Aide> aidesURL =  query.list();
			if(aidesURL.size()==0) {
				return null;
			}else {
				return aidesURL.get(0); 
			}
			
			
			
		} catch (HibernateException e) {
			// En cas d'erreur:
			System.out.println("... (AideDAOImpl) Erreur lors de la récupération par Id ...");
			throw e;
		}//end catch
				
	}//end getByUrl

	
}//end class
