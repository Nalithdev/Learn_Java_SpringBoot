package fr.cours.miseenplace;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Salutation")
class SalutationTest {

	private final Salutation salutation = new Salutation();

	@Test
	@DisplayName("1. salue une personne par son prénom")
	void saluePersonneParSonPrenom() {
		assertThat(salutation.saluer("Ethan")).isEqualTo("Bonjour, Ethan !");
	}

	@Test
	@DisplayName("2. retire les espaces autour du prénom")
	void retireLesEspacesAutourDuPrenom() {
		assertThat(salutation.saluer("  Ada  ")).isEqualTo("Bonjour, Ada !");
	}

	@Test
	@DisplayName("3. salue un inconnu quand le prénom est null")
	void salueUnInconnuQuandPrenomNull() {
		assertThat(salutation.saluer(null)).isEqualTo("Bonjour, inconnu !");
	}

	@Test
	@DisplayName("4. salue un inconnu quand le prénom est vide ou ne contient que des espaces")
	void salueUnInconnuQuandPrenomVide() {
		assertThat(salutation.saluer("")).isEqualTo("Bonjour, inconnu !");
		assertThat(salutation.saluer("   ")).isEqualTo("Bonjour, inconnu !");
	}

	@Test
	@DisplayName("5. garde les espaces à l'intérieur d'un prénom composé")
	void gardeLesEspacesInterieurs() {
		assertThat(salutation.saluer("  Jean Pierre ")).isEqualTo("Bonjour, Jean Pierre !");
	}

}
