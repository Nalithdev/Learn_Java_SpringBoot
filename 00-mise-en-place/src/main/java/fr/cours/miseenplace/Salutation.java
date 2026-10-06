package fr.cours.miseenplace;

/**
 * Construit des messages de bienvenue.
 * <p>
 * Squelette fourni : remplace le contenu de la méthode par ton implémentation.
 */
public class Salutation {

	public String saluer(String prenom) {
		String personName = "";

		if (prenom == null || prenom.isBlank()){
			personName = "inconnu";
		} else {
			personName = prenom;
		}
		return ("Bonjour, " + personName.trim() + " !");
	}

}
