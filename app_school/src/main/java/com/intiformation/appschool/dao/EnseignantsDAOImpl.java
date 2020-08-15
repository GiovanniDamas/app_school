package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class EnseignantsDAOImpl implements IEnseignantsDAO {

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
	public void addEnseignant(Enseignants pEnseignant) {
		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			session.save(pEnseignant);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de l'ajout de l'Enseignant dans la DAO ...");
			throw e;
		} // END CATCH
	}// END ADD

	@Override
	public void updateEnseignant(Enseignants pEnseignant) {
		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			session.update(pEnseignant);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la modification de l'Enseignant dans la DAO ...");
			throw e;
		} // END CATCH

	}// END UPDATE

	@Override
	public void deleteEnseignant(Long pIdEnseignant) {
		try {
			// recup de la session

			Session session = sessionFactory.getCurrentSession();

			// Récup de l'étudiant à supprimer

			Enseignants enseignantToDelete = getEnseignantById(pIdEnseignant);

			session.delete(enseignantToDelete);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la suppression de l'Enseignant dans la DAO ...");
			throw e;
		} // END CATCH

	}// END DELETE

	@Override
	public Enseignants getEnseignantById(Long pIdEnseignant) {

		try {
			// recup de la session hibernate via la factory

			Session session = this.sessionFactory.getCurrentSession();

			Enseignants enseignant = session.get(Enseignants.class, pIdEnseignant);

			return enseignant;

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de l'Enseignant par son ID dans la DAO ...");
		}
		return null;

	}// END GET BY ID

	@Override
	public List<Enseignants> getAllEnseignant() {
		// recup de la session hibernate via la factory
		Session session = this.sessionFactory.getCurrentSession();
		
		try {

			Query getAllEnseignant = session.createQuery("From Enseignants");

			return getAllEnseignant.list();

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de la liste des Enseignants dans la DAO ...");
		}
		return null;

	}// END GET ALL
	
	/*_________________________________________________________________________________________________________________*/
	@Override
	public Enseignants getEnseignantByIdentifiant(String pIdentifiant) {
		// recup de la session hibernate via la factory
		Session session = this.sessionFactory.getCurrentSession();
		
		try {

			Query<Enseignants> queryGetEnseignantByIdentifiant = session.createQuery("From Enseignants WHERE identifiant = :pIdentifiant");

			queryGetEnseignantByIdentifiant.setParameter("pIdentifiant", pIdentifiant);
			
			return queryGetEnseignantByIdentifiant.getSingleResult();

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de la liste de l'enseignant par son identifiant dans la DAO ...");
		}
		return null;

	}// END getEnseignantByIdentifiant

}// END CLASS
