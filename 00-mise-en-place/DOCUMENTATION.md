# Documentation — Cours 00 : Mise en place

## 1. Les notions

### Le JDK
Le **Java Development Kit** contient le compilateur (`javac`) et la machine virtuelle (`java`). Spring Boot 4 demande **Java 17 minimum** ; les versions LTS (support long terme) actuelles sont 17, 21 et 25.

### Maven
Outil de **build** : il télécharge les dépendances, compile, lance les tests et produit le `.jar`. Tout est décrit dans le `pom.xml`.

Les **phases** principales s'enchaînent dans l'ordre :

| Phase | Ce qu'elle fait |
|---|---|
| `compile` | compile `src/main/java` |
| `test` | compile `src/test/java` et lance les tests |
| `package` | crée le `.jar` dans `target/` |
| `clean` | supprime le dossier `target/` |

Lancer `test` exécute aussi `compile` avant. Combinaison fréquente : `.\mvnw.cmd clean test`.

### Le Maven Wrapper (`mvnw`)
Script qui télécharge **la version de Maven fixée par le projet** (dans `.mvn/wrapper/maven-wrapper.properties`). Tout le monde — toi, un collègue, le serveur d'intégration continue — utilise ainsi exactement la même version.
- Windows : `.\mvnw.cmd ...`
- Linux / macOS / Git Bash : `./mvnw ...`

### Le `pom.xml`
- `<parent>` `spring-boot-starter-parent` : hérite d'une configuration par défaut et **fixe les versions** de toutes les bibliothèques compatibles (tu n'écris pas de `<version>` sur les dépendances Spring).
- `<dependencies>` : les **starters** regroupent plusieurs bibliothèques cohérentes.
  - `spring-boot-starter` : le cœur de Spring Boot
  - `spring-boot-starter-test` (scope `test`) : JUnit 5, AssertJ, Mockito, Spring Test
- `spring-boot-maven-plugin` : permet de créer un `.jar` exécutable et de lancer l'appli (`.\mvnw.cmd spring-boot:run`).

### La structure standard Maven
| Dossier | Contenu |
|---|---|
| `src/main/java` | code de production |
| `src/main/resources` | configuration (`application.properties`), fichiers statiques |
| `src/test/java` | tests (même package que la classe testée) |
| `target/` | fichiers générés — **jamais versionné** |

### `@SpringBootApplication`
Annotation posée sur la classe principale. Elle active la configuration automatique et le scan des composants **dans son package et ses sous-packages** — d'où l'importance de ranger toutes tes classes sous `fr.cours.miseenplace`.

### Le TDD
On écrit (ici : on reçoit) le test **avant** le code. Cycle :
1. 🔴 **Red** : le test échoue
2. 🟢 **Green** : code minimal pour le faire passer
3. 🔧 **Refactor** : on améliore sans changer le comportement, les tests servent de filet de sécurité

## 2. Outils utiles pour ce cours

### JUnit 5
| Élément | Rôle |
|---|---|
| `@Test` | marque une méthode de test |
| `@DisplayName("...")` | nom lisible dans le rapport |
| `@ParameterizedTest` + `@ValueSource(strings = {...})` | lance le même test avec plusieurs valeurs |

### AssertJ
Assertions lisibles, en chaîne :
```java
assertThat(resultat).isEqualTo("Bonjour, Ada !");
assertThat(estValide).isTrue();
```

### Méthodes Java dont tu auras probablement besoin
| Méthode | Exemple |
|---|---|
| `String.strip()` / `trim()` | `"  Ada ".strip()` → `"Ada"` |
| `String.isBlank()` | `"   ".isBlank()` → `true` |
| `String.replace(...)` | retirer un caractère |
| `String.length()`, `charAt(i)` | parcourir une chaîne |
| `Character.isDigit(c)` | `'7'` → `true` |
| `Character.getNumericValue(c)` ou `c - '0'` | `'7'` → `7` |
| `String.matches(regex)` | `"123".matches("\\d+")` → `true` |
| Opérateur `%` | `100 % 10` → `0` |

## 3. Liens

- Spring Boot — Getting started : https://docs.spring.io/spring-boot/tutorial/first-application/index.html
- Spring Initializr : https://start.spring.io
- Structure d'un projet Spring Boot : https://docs.spring.io/spring-boot/reference/using/structuring-your-code.html
- Maven en 5 minutes : https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
- Maven Wrapper : https://maven.apache.org/tools/wrapper/
- Guide utilisateur JUnit 5 : https://junit.org/junit5/docs/current/user-guide/
- AssertJ : https://assertj.github.io/doc/
- Tests paramétrés (Baeldung) : https://www.baeldung.com/parameterized-tests-junit-5
- ISBN-13 (Wikipédia) : https://fr.wikipedia.org/wiki/International_Standard_Book_Number

## 4. Pièges fréquents

- **`NullPointerException`** : appeler `prenom.strip()` alors que `prenom` est `null`. Teste le `null` **en premier**.
- **`'7'` n'est pas `7`** : un `char` est un code de caractère (`'7'` vaut 55). Convertis-le avant de calculer.
- **Index de départ** : le 1ᵉʳ chiffre est à l'index **0** et a le poids **1** → les index **pairs** ont le poids 1.
- **Lancer `mvn` au lieu de `mvnw`** : Maven n'est pas installé sur ta machine, utilise le wrapper.
- **Lancer la commande depuis le mauvais dossier** : `mvnw` doit être lancé depuis `00-mise-en-place/` (là où se trouve le `pom.xml`).
- **Classe dans le mauvais package** : le `package` en haut du fichier doit correspondre au dossier.
