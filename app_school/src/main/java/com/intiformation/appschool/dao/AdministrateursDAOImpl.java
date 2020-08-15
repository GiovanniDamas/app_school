package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Administrateurs;
import com.intiformation.appschool.modeles.Enseignants;

/**
 * Classe concrètes de la couche DAO </br>
 * Cette classe implémente l'interface IEnseignantDAO
 * 
 * @author giovanni
 *
 */
@Repository
@Transactional
public class AdministrateursDAOImpl implements IAdministrateursDAO {

	// Déclaration de la session Factory
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Déclaration du setter SessionFactory pour l'injection par modificateur
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addAdministrateur(Administrateurs pAdministrateur) {

		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			session.save(pAdministrateur);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de l'ajout de l'Administrateur dans la DAO ...");
			throw e;
		} // END CATCH

	}// END ADD

	@Override
	public void updateAdministrateur(Administrateurs pAdministrateur) {

		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			session.update(pAdministrateur);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la modification de l'Administrateur dans la DAO ...");
			throw e;
		} // END CATCH

	}// END UPDATE

	@Override
	public void deleteAdministrateur(Long pIdAdministrateur) {

		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			Administrateurs administrateurToDelete = getAdministrateurById(pIdAdministrateur);

			session.save(administrateurToDelete);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la suppression de l'Administrateur dans la DAO ...");
			throw e;
		} // END CATCH

	}// END DETELE

	@Override
	public Administrateurs getAdministrateurById(Long pIdAdministrateur) {

		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			Administrateurs administrateurToDelete = session.get(Administrateurs.class, pIdAdministrateur);

		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la recupération de l'Administrateur par son ID dans la DAO ...");
			throw e;
		} // END CATCH

		return null;
	}

	@Override
	public List<Administrateurs> getAllAdministrateur() {

		// recup de la session

		Session session = sessionFactory.getCurrentSession();

		try {
			
			Query getAllAdmin = session.createQuery("From Administrateurs");

			return getAllAdmin.list();

		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la récupération de la liste des Administrateurs dans la DAO ...");
			throw e;
		} // END CATCH

	}// END GET ALL;
	
	/*_________________________________________________________________________________________________________________*/
	@Override
	public Administrateurs getAdministrateurByIdentifiant(String pIdentifiant) {
		// recup de la session hibernate via la factory
		Session session = this.sessionFactory.getCurrentSession();
		
		try {

			Query<Administrateurs> queryGetAdminByIdentifiant = session.createQuery("From Administrateurs WHERE identifiant = :pIdentifiant");

			queryGetAdminByIdentifiant.setParameter("pIdentifiant", pIdentifiant);
			
			return queryGetAdminByIdentifiant.getSingleResult();

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de l'administrateur par son identifiant dans la DAO ...");
		}
		return null;

	}// END getAdministrateurByIdentifiant

}// END CLASS
