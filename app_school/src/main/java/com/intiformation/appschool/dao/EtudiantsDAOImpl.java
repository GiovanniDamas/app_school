package com.intiformation.appschool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intiformation.appschool.modeles.Etudiants;

/**
 * Classe concrètes implémentant l'interface IPersonne de la couche DAO </br>
 * 
 * @author giovanni
 *
 */
@Repository
public class EtudiantsDAOImpl implements IEtudiantsDAO {

	// Déclaration de la session Factory
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Déclaration du setter SessionFactory pour l'injection par modificateur
	 * 
	 * @return
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void addEtudiant(Etudiants pEtudiant) {

		// recup de la session

		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(pEtudiant);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de l'ajout de l'Etudiant dans la DAO ...");
			throw e;
		} // END CATCH

	}// END ADD ETUDIANT

	@Override
	@Transactional
	public void updateEtudiant(Etudiants pEtudiant) {

		// recup de la session

		Session session = sessionFactory.getCurrentSession();

		try {
			session.update(pEtudiant);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la modification de l'Etudiant dans la DAO ...");
			throw e;
		} // END CATCH

	}// END UPDATE

	@Override
	@Transactional
	public void deleteEtudiant(Long pIdEtudiant) {

		// recup de la session

		Session session = sessionFactory.getCurrentSession();

		try {
			// Récup de l'étudiant à supprimer

			Etudiants etudiantToDelete = getEtudiantById(pIdEtudiant);

			session.delete(etudiantToDelete);
		} catch (HibernateException e) {

			System.out.println("... Erreur lors de la suppression de l'Etudiant dans la DAO ...");
			throw e;
		} // END CATCH

	}// END DELETE

	@Override
	@Transactional(readOnly = true)
	public Etudiants getEtudiantById(Long pIdEtudiant) {

		// recup de la session hibernate via la factory
		Session session = this.sessionFactory.getCurrentSession();

		try {

			Etudiants etudiant = session.get(Etudiants.class, pIdEtudiant);

			return etudiant;

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de l'Etudiant par son ID dans la DAO ...");
		}
		return null;

	}// END GET BY ID

	@Override
	@Transactional(readOnly = true)
	public List<Etudiants> getAllEtudiant() {

		// recup de la session hibernate via la factory
		Session session = this.sessionFactory.getCurrentSession();

		try {

			// création requete HQL
			Query queryGetAll = session.createQuery("From Etudiants");
			
			// envoi, execution, resultat
			List<Etudiants> listeEtudiantsBDD = queryGetAll.list();
			
			return listeEtudiantsBDD;

		} catch (Exception e) {
			System.out.println("... Erreur lors de la récupération de la liste des Etudiants dans la DAO ...");
		}
		return null;

	}// END GET ALL

}// END CLASS
