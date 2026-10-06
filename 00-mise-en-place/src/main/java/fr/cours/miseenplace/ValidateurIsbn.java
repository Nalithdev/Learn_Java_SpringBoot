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
			System.out.println(isbn);
		}

		if (isbn.length() < 13 || !isbn.matches("\\d+") || isbn.length() > 13){
			return false;
		}

		for (int i=0; i < isbn.length()-1; i++){
			if (i%2 == 0){
				keyControl += Character.getNumericValue(isbn.charAt(i))*1;				
			} else {
				keyControl += Character.getNumericValue(isbn.charAt(i))*3;
			}
			if (i == isbn.length()-2){
				lastCharId = i + 1;
			}
		}
		System.out.println(keyControl%10);

		if (keyControl%10 == 0 && keyControl%10 == Character.getNumericValue(isbn.charAt(lastCharId))){
			return true;

		}
		if(10-(keyControl%10) == Character.getNumericValue(isbn.charAt(lastCharId))){
			return true;

		}
		else {
			return false;
		}
	}

}
