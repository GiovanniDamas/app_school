package com.intiformation.appschool.service;

import java.util.List;

import com.intiformation.appschool.modeles.EnseignantMatierePromotionLink;

/**
 * Interface spécifique de la couche service pour la classe EnseignantMatierePromotionLink
 *  
 * @author hannahlevardon
 *
 */
public interface IEnseignantMatierePromotionLinkService {
	
	/**
	 * Méthode pour ajouter un lien entre Enseignant, Matiere et Promotion à la database
	 * @param pLink
	 */
	public void ajouterLink(EnseignantMatierePromotionLink pLink);	
	
	/**
	 * Méthode pour modifier un lien entre Enseignant, Matiere et Promotion dans la database
	 * @param pLink
	 */
	public void modifierLink(EnseignantMatierePromotionLink pLink);
	
	/**
	 * Méthode pour supprimer un lien entre Enseignant, Matiere et Promotion de la database
	 * @param pIdLink
	 */
	public void supprimerLink(Long pIdLink);
	
	/**
	 * Méthode pour retrouver un lien entre Enseignant, Matiere et Promotion par son Id
	 * @param pIdLink
	 * @return EnseignantMatierePromotionLink
	 */
	public EnseignantMatierePromotionLink trouverLinkId(Long pIdLink);
	
	
	/**
	 * Méthode pour afficher la liste des liens de la database
	 * @return Liste des un lien entre Enseignant, Matiere et Promotion
	 */
	public List<EnseignantMatierePromotionLink> trouverAllLinks();
	
	
	/**
	 * Méthode pour afficher la liste des liens liés à l'id d'une promotion de la database
	 * @return Liste des un lien entre Enseignant, Matiere et Promotion
	 */
	public List<EnseignantMatierePromotionLink> trouverlinkViaIdPromo(Long pIdPromotion);
	
	/**
	 * Méthode pour afficher la liste des liens liés à l'id d'une matiere de la database
	 * @return Liste des un lien entre Enseignant, Matiere et Promotion
	 */
	public List<EnseignantMatierePromotionLink> trouverlinkViaIdMatiere(Long pIdMatiere);

	

}//end interface
