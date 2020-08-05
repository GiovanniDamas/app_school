package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Adresse;

public interface IAdresseDAO extends IGenerique<Adresse>{

	/*_____________Methodes spécifiques________________*/

	public List<Adresse> getAdressesByPersonne(Long pIdPersonne);
	
}//end interface
