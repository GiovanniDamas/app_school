package com.intiformation.appschool.dao;

import java.util.List;

import com.intiformation.appschool.modeles.Adresse;
import com.intiformation.appschool.modeles.Personnes;

public interface IAdresseDAO extends IGenerique<Adresse>{

	/*_____________Methodes spécifiques________________*/

	public List<Adresse> getAdressesByPersonne(Personnes pPersonne);
	
}//end interface
