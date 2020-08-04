package com.intiformation.appschool.dao;

import java.util.List;


/**
 * Interface générale des méthodes utilisées dans la DAO
 * @author hannahlevardon
 *
 */
public interface IGenerique<T> {
	
	
	/**
	 * Méthode pour ajouter n'importe quel type d'objet
	 * @param t
	 */
	public void add(T t);	
	
	/**
	 * Méthode pour modifier n'importe quel type d'objet
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * Méthode pour supprimer n'importe quel type d'objet
	 * @param pId
	 */
	public void delete(Long pId);
	
	/**
	 * Méthode pour retrouver n'importe quel type d'objet par son Id
	 * @param pId
	 * @return
	 */
	public T getById(Long pId);
	
	
	/**
	 * Méthode pour afficher la liste  n'importe quel type d'objet
	 * @return
	 */
	public List<T> getAll(); 
	

}//end interface
