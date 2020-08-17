package com.intiformation.appschool.dao;

import com.intiformation.appschool.modeles.Aide;

public interface IAideDAO extends IGenerique<Aide> {
	
	/*_____________Methodes spécifiques________________*/

	public Aide getByUrl(String pUrl);
	
}//end interface
