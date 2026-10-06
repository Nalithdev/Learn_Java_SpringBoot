package fr.cours.miseenplace;

/**
 * Vérifie qu'un ISBN-13 est valide.
 * <p>
 * Squelette fourni : remplace le contenu de la méthode par ton implémentation.
 */
public class ValidateurIsbn {

	public boolean estValide(String isbn) {
		int keyControl = 0;
		int lastCharId = 0;
		if (isbn == null || isbn.equals("")){
			return false;
		}

		if (isbn.contains("-")){
			isbn = isbn.replaceAll("-", "");
		}

		if (isbn.length() != 13 || !isbn.matches("\\d+")){
			return false;
		}

		for (int i=0; i < isbn.length()-1; i++){
			if (i%2 == 0){
				keyControl += Character.getNumericValue(isbn.charAt(i))*1;				
			} else {
				keyControl += Character.getNumericValue(isbn.charAt(i))*3;
			}
		}
		lastCharId = isbn.length()-1;

		int finalKeyControl = keyControl%10 ;
		if (finalKeyControl != 0 ){
			finalKeyControl = 10 - finalKeyControl;
		}
		if(finalKeyControl == Character.getNumericValue(isbn.charAt(lastCharId))){
			return true;

		}
		else {
			return false;
		}
	}

}
