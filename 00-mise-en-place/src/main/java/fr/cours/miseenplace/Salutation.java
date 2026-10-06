package fr.cours.miseenplace;

/**
 * Construit des messages de bienvenue.
 * <p>
 * Squelette fourni : remplace le contenu de la méthode par ton implémentation.
 */
public class Salutation {

	public String saluer(String prenom) {
		String nameWithoutSpace = "";

		if (prenom == null || prenom.matches("\\s+") || prenom.equals("")){
			nameWithoutSpace = "inconnu";
		} else {
			nameWithoutSpace = prenom;
		}
		nameWithoutSpace = nameWithoutSpace.trim();
		return ("Bonjour, " + nameWithoutSpace + " !");
	}

}
