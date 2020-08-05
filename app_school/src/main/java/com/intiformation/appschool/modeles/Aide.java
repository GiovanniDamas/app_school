package com.intiformation.appschool.modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Modele de donnée pour l'aide du site (pages d'aide)
 * i.e. pages associées à l'aide et leur contenu
 * @author gabri
 *
 */
@Entity
@Table(name="aide")
public class Aide {

	
	/*______________________props_____________________________*/
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aide")
	private Long idAide;
	
	@Column(name="url_page")
	private String urlPage;
	
	@Column(name="contenu")
	private String contenu;
	
	/*______________________props_____________________________*/
	public Aide() {
	}//end ctor vide

	
	public Aide(String urlPage, String contenu) {
		this.urlPage = urlPage;
		this.contenu = contenu;
	}

	public Aide(Long idAide, String urlPage, String contenu) {
		this.idAide = idAide;
		this.urlPage = urlPage;
		this.contenu = contenu;
	}


	/*______________________getters/setters_____________________________*/

	public Long getIdAide() {
		return idAide;
	}

	public void setIdAide(Long idAide) {
		this.idAide = idAide;
	}

	public String getUrlPage() {
		return urlPage;
	}

	public void setUrlPage(String urlPage) {
		this.urlPage = urlPage;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
	
	
}//end classe
