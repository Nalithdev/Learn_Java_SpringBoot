package fr.cours.miseenplace;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("ValidateurIsbn")
class ValidateurIsbnTest {

	private final ValidateurIsbn validateur = new ValidateurIsbn();

	@Test
	@DisplayName("1. refuse un ISBN null")
	void refuseIsbnNull() {
		assertThat(validateur.estValide(null)).isFalse();
	}

	@Test
	@DisplayName("2. refuse un ISBN vide")
	void refuseIsbnVide() {
		assertThat(validateur.estValide("")).isFalse();
	}

	@ParameterizedTest(name = "\"{0}\" n''a pas 13 chiffres")
	@ValueSource(strings = { "978030640615", "97803064061577", "123" })
	@DisplayName("3. refuse un ISBN qui n'a pas exactement 13 chiffres")
	void refuseIsbnSansTreizeChiffres(String isbn) {
		assertThat(validateur.estValide(isbn)).isFalse();
	}

	@ParameterizedTest(name = "\"{0}\" contient autre chose que des chiffres")
	@ValueSource(strings = { "978030640615X", "978O306406157", "978 0306406157" })
	@DisplayName("4. refuse un ISBN qui contient autre chose que des chiffres")
	void refuseIsbnAvecCaracteresInterdits(String isbn) {
		assertThat(validateur.estValide(isbn)).isFalse();
	}

	@ParameterizedTest(name = "\"{0}\" est valide")
	@ValueSource(strings = { "9780306406157", "9782070612758" })
	@DisplayName("5. accepte un ISBN de 13 chiffres dont la clé de contrôle est correcte")
	void accepteIsbnValide(String isbn) {
		assertThat(validateur.estValide(isbn)).isTrue();
	}

	@ParameterizedTest(name = "\"{0}\" a une mauvaise clé")
	@ValueSource(strings = { "9780306406158", "9782070612750" })
	@DisplayName("6. refuse un ISBN dont la clé de contrôle est fausse")
	void refuseIsbnAvecMauvaiseCle(String isbn) {
		assertThat(validateur.estValide(isbn)).isFalse();
	}

	@ParameterizedTest(name = "\"{0}\" est valide malgré les tirets")
	@ValueSource(strings = { "978-0-306-40615-7", "978-2-07-061275-8" })
	@DisplayName("7. accepte un ISBN valide écrit avec des tirets")
	void accepteIsbnValideAvecTirets(String isbn) {
		assertThat(validateur.estValide(isbn)).isTrue();
	}

	@Test
	@DisplayName("8. accepte un ISBN valide dont la clé de contrôle vaut 0")
	void accepteIsbnAvecCleZero() {
		// somme des 12 premiers produits = 100 → clé = 0
		assertThat(validateur.estValide("9782266111560")).isTrue();
	}

	@Test
	@DisplayName("9. refuse un ISBN de 14 chiffres, même si les 14 chiffres « tombent juste »")
	void refuseQuatorzeChiffresQuiTombentJuste() {
		assertThat(validateur.estValide("97803064061534")).isFalse();
	}

	@ParameterizedTest(name = "\"{0}\" est valide quel que soit le placement des tirets")
	@ValueSource(strings = { "978-0306406157", "9780-306-406-157", "978-0-306-40615-7" })
	@DisplayName("10. accepte un ISBN valide quel que soit le nombre et la position des tirets")
	void accepteIsbnAvecTiretsPlacesLibrement(String isbn) {
		assertThat(validateur.estValide(isbn)).isTrue();
	}

}
