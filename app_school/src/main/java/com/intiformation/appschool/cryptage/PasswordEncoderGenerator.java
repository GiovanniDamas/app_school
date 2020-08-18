package com.intiformation.appschool.cryptage;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * permet de crypter le mot de passe.<br/>
 * utilisation de la classe spring security "BcryptPasswordEncoder" pour le cryptage. 
 * @author giovanni
 *
 */
public class PasswordEncoderGenerator {

	private static final String MOT_DE_PASSE = "admin";
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		
		// objet pour le  cryptage
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// cryptage du mot de passe avec la méthode encode()
		String hashedMotDePasse = passwordEncoder.encode(MOT_DE_PASSE);
		
		// affichage du mot de passe crypté 
		System.out.println("mot de pass crypté = " + hashedMotDePasse);
		
		
	}//end main 
	
}//end class
