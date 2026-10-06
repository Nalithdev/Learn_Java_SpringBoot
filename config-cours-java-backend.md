# Configuration — Cours Java Backend (Spring Boot · TDD)

> Ce fichier se place **à la racine** du dossier des cours. Donne-le à Claude au début de chaque séance
> pour reprendre au bon endroit, et mets à jour la section « Progression » à la fin de chaque séance.

---

## 1. Profil de l'apprenant

- **Langue des cours :** français
- **Niveau actuel :** bases de Java acquises (syntaxe, POO, collections, exceptions)
- **Nouveau palier :** développement backend — API REST avec **Spring Boot**
- **Objectif :** concevoir, coder, tester et déployer une API backend complète, au niveau attendu dans les offres d'emploi Java
- **Plus tard (optionnel) :** comparaison avec Jakarta EE une fois Spring Boot maîtrisé

## 2. Méthode de travail : TDD (Test-Driven Development)

### Répartition des rôles

| Claude | L'apprenant |
|---|---|
| Écrit l'énoncé du cours (`README.md`) | Lit l'énoncé et la documentation |
| Écrit **tous les tests unitaires** du cours | Écrit le **code de production** qui fait passer les tests |
| Fournit un fichier `DOCUMENTATION.md` avec les notions et liens nécessaires | Lance les tests, lit les échecs, corrige |
| Fournit le `pom.xml` et un squelette minimal si nécessaire | Soumet son code pour relecture |
| **Relit et valide** le code avant de passer au cours suivant | Corrige selon les retours de la relecture |

### Cycle de travail (Red → Green → Refactor)

1. **Red** — lancer `mvn test` : les tests échouent (normal, le code n'existe pas encore).
2. **Green** — écrire le code **minimal** pour faire passer un test, puis le suivant.
3. **Refactor** — une fois au vert, améliorer la lisibilité sans casser les tests.
4. Répéter test par test, dans l'ordre indiqué dans le `README.md` du cours.

### Règles

1. **Les tests sont en lecture seule :** l'apprenant ne modifie jamais un test pour le faire passer. Si un test semble faux, on en discute avec Claude.
2. **Pas de solution toute faite :** Claude donne des indices, pose des questions, pointe la documentation — il n'écrit pas le code de production à la place de l'apprenant.
3. **Squelette dégressif :** dans les premiers cours, Claude fournit les signatures (classes, méthodes vides) pour que les tests compilent ; au fil des cours, l'apprenant crée lui-même les classes à partir de l'énoncé.
4. **Comprendre les échecs :** quand un test échoue, on lit le message d'erreur et la stack trace avant de toucher au code.
5. **Validation obligatoire :** un cours est terminé seulement quand **tous les tests passent** et que Claude a **validé la relecture**.

### Critères de relecture (validation par Claude)

- [ ] `mvn test` passe entièrement, sans test modifié ni désactivé
- [ ] Le code respecte l'architecture demandée (controller → service → repository)
- [ ] Nommage clair (classes, méthodes, variables) et conventions Java
- [ ] Pas de code mort, de duplication évidente ou de `System.out.println` oublié
- [ ] Gestion correcte des cas d'erreur (null, ressource introuvable, données invalides)
- [ ] L'apprenant sait **expliquer** son code et les choix faits

## 3. Organisation des dossiers

Chaque cours est un **projet Maven autonome**, rangé dans un dossier enfant de la racine :

```
cours-java-backend/                  ← racine
├── config-cours-java-backend.md     ← ce fichier
├── 00-mise-en-place/
│   ├── README.md                    ← énoncé, objectifs, ordre des tests
│   ├── DOCUMENTATION.md             ← notions + liens vers la doc officielle
│   ├── pom.xml
│   └── src/
│       ├── main/java/...            ← code de l'apprenant
│       └── test/java/...            ← tests unitaires fournis par Claude
├── 01-hello-rest/
│   └── ...
└── 02-.../
```

**Convention de nommage :** `NN-nom-du-cours` (numéro sur deux chiffres, nom en minuscules avec tirets).

### Contenu du `DOCUMENTATION.md` de chaque cours

1. Les notions à comprendre, expliquées simplement
2. Les annotations / classes / méthodes utiles pour le cours
3. Les liens vers la documentation officielle (Spring, Baeldung, JUnit, etc.)
4. Les pièges fréquents

## 4. Stack technique

| Élément | Choix | Remarque |
|---|---|---|
| JDK | **Java 17** (installé) — passer à 21 ou 25 (LTS) recommandé | Spring Boot 4 exige Java 17 minimum |
| Framework | **Spring Boot 4.1.1** | Dernière version stable sur start.spring.io (oct. 2026) |
| Build | **Maven** via Maven Wrapper (`mvnw.cmd`) | Un `pom.xml` par cours ; Maven non installé globalement |
| Base de données | **H2** (début) → **PostgreSQL** (ensuite) | |
| Tests | **JUnit 5**, **AssertJ**, **Mockito** | Inclus dans `spring-boot-starter-test` |
| Tests Spring | `@WebMvcTest`, `@DataJpaTest`, MockMvc | Tests ciblés d'une couche |
| Outils d'appel API | `curl`, Postman ou Bruno | Pour tester l'API « à la main » |
| IDE | *(à préciser)* | IntelliJ IDEA recommandé |

## 5. Parcours des cours

| # | Dossier | Notions clés | Type de tests |
|---|---|---|---|
| 00 | `00-mise-en-place` | JDK, Maven, Spring Initializr, structure d'un projet, lancer `mvn test` | JUnit 5 simple |
| 01 | `01-hello-rest` | HTTP (verbes, codes), `@RestController`, `@GetMapping`, `@PathVariable`, `@RequestParam`, JSON | `@WebMvcTest` + MockMvc |
| 02 | `02-couche-service` | Logique métier, injection de dépendances, `@Service`, interfaces | JUnit + Mockito (pur unitaire) |
| 03 | `03-crud-livres` | CRUD complet (POST/PUT/DELETE), `ResponseEntity`, codes 200/201/204/404 | `@WebMvcTest` + Mockito |
| 04 | `04-validation-erreurs` | Bean Validation (`@Valid`, `@NotBlank`…), `@RestControllerAdvice`, `ProblemDetail` | `@WebMvcTest` |
| 05 | `05-persistance-jpa` | Spring Data JPA, entités, repositories, H2 | `@DataJpaTest` |
| 06 | `06-relations-dto` | Relations JPA, DTO, mapping, pagination (gestion de commandes clients) | Unitaires + `@DataJpaTest` |
| 07 | `07-postgresql-profils` | PostgreSQL, `application.yml`, profils Spring | Tests d'intégration (Testcontainers) |
| 08 | `08-securite` | Spring Security, authentification JWT, rôles | `@WebMvcTest` + sécurité |
| 09 | `09-projet-final` | Projet complet au choix, Docker, documentation OpenAPI | Tous types |

## 6. Format d'une séance

1. Rappel de la séance précédente (2-3 questions)
2. Claude présente le cours : énoncé + documentation + tests
3. L'apprenant code en TDD (Red → Green → Refactor)
4. L'apprenant soumet son code → relecture par Claude
5. Corrections éventuelles → validation
6. Récap : 3 à 5 points clés

## 7. Progression

- **Cours en cours :** 00 — Mise en place
- **Statut :** ✅ validé le 2026-10-06 (25/25) — derniers points de style à intégrer au commit final
- **Tests passants :** 25 / 25
- **Notions maîtrisées :** cycle Red/Green, lecture d'un rapport Maven, `matches()` porte sur toute la chaîne, cas limites (clé = 0, longueur exacte), `isBlank()` / `trim()`, lecture d'une erreur de compilation
- **Points à revoir :** simplifier (`return condition;` au lieu de if/else true/false, pas de `if` avant un `replace`), déclarer les variables au plus près de leur usage, nettoyer les commentaires du squelette ; a tendance à reporter le ménage du Refactor
- **Dernière séance :** 2026-10-06 (relecture du cours 00)
