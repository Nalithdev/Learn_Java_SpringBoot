package fr.cours.miseenplace;

/**
 * Construit des messages de bienvenue.
 * <p>
 * Squelette fourni : remplace le contenu de la méthode par ton implémentation.
 */
public class Salutation {

	public String saluer(String prenom) {

		if (prenom == null || prenom.matches("\\s+") || prenom.equals("")){
			prenom = "inconnu";
		} 
		String nameWithoutSpace = prenom.replaceAll("\\s+", "");
		return ("Bonjour, " + nameWithoutSpace + " !");
	}

}
