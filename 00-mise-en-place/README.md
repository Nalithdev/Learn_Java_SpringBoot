# Cours 00 — Mise en place

## Objectifs

À la fin de ce cours, tu sais :

1. Vérifier ton environnement (JDK, Git, Maven Wrapper)
2. Lire la structure d'un projet Spring Boot généré par Spring Initializr
3. Lancer les tests avec `mvnw test` et **lire un rapport d'échec**
4. Appliquer le cycle **Red → Green → Refactor** sur deux petites classes Java

> Pas encore de web ni de base de données ici : on prend le rythme du TDD sur du Java pur.

---

## Étape 1 — Vérifier l'environnement

Dans un terminal, depuis le dossier `00-mise-en-place` :

```bash
java -version
```

```bash
git --version
```

```bash
.\mvnw.cmd -v
```

- Tu dois avoir un **JDK 17 ou plus** (Spring Boot 4 exige Java 17 minimum).
- Tu n'as **pas besoin d'installer Maven** : le **Maven Wrapper** (`mvnw` / `mvnw.cmd`) télécharge automatiquement la bonne version au premier lancement.

**Question :** à ton avis, pourquoi un projet embarque-t-il son propre wrapper Maven plutôt que de compter sur le Maven installé sur la machine ?

## Étape 2 — Explorer la structure

Ce projet a été généré avec [Spring Initializr](https://start.spring.io) puis complété. Ouvre chaque élément et repère son rôle (voir `DOCUMENTATION.md`) :

```
00-mise-en-place/
├── pom.xml                         ← dépendances et configuration du build
├── mvnw, mvnw.cmd, .mvn/           ← Maven Wrapper
└── src/
    ├── main/java/fr/cours/miseenplace/
    │   ├── MiseEnPlaceApplication.java   ← point d'entrée Spring Boot
    │   ├── Salutation.java               ← À COMPLÉTER
    │   └── ValidateurIsbn.java           ← À COMPLÉTER
    ├── main/resources/application.properties
    └── test/java/fr/cours/miseenplace/
        ├── MiseEnPlaceApplicationTests.java  ← vérifie que Spring démarre
        ├── SalutationTest.java               ← tests fournis (lecture seule)
        └── ValidateurIsbnTest.java           ← tests fournis (lecture seule)
```

**Exercice bonus :** va sur [start.spring.io](https://start.spring.io), génère un projet Maven / Java 17 sans dépendance, et compare son `pom.xml` avec celui-ci.

## Étape 3 — Red 🔴

```bash
.\mvnw.cmd test
```

Résultat attendu : `contextLoads` passe, **tous les autres tests échouent** avec `UnsupportedOperationException: À implémenter`. C'est normal.

Prends le temps de lire le rapport : nom du test, message, ligne de la stack trace qui pointe vers **ton** code.

## Étape 4 — Green 🟢, test par test

Travaille **dans cet ordre**, en relançant les tests après chaque modification.

Astuce : pour ne lancer qu'une classe de test :

```bash
.\mvnw.cmd test -Dtest=SalutationTest
```

### A. `Salutation.saluer(String prenom)`

| # | Test | Comportement attendu |
|---|---|---|
| 1 | `saluePersonneParSonPrenom` | `"Ethan"` → `"Bonjour, Ethan !"` |
| 2 | `retireLesEspacesAutourDuPrenom` | `"  Ada  "` → `"Bonjour, Ada !"` |
| 3 | `salueUnInconnuQuandPrenomNull` | `null` → `"Bonjour, inconnu !"` |
| 4 | `salueUnInconnuQuandPrenomVide` | `""` ou `"   "` → `"Bonjour, inconnu !"` |
| 5 | `gardeLesEspacesInterieurs` | `"  Jean Pierre "` → `"Bonjour, Jean Pierre !"` *(ajouté après relecture)* |

### B. `ValidateurIsbn.estValide(String isbn)`

Un ISBN-13 identifie un livre (il nous resservira au cours 03 sur le CRUD de livres).

**Règle de la clé de contrôle :** on multiplie les 13 chiffres alternativement par **1** et **3** (le 1ᵉʳ par 1, le 2ᵉ par 3, le 3ᵉ par 1…), on additionne tout : l'ISBN est valide si la somme est un **multiple de 10**.

Exemple avec `9780306406157` :

```
chiffres :  9  7  8  0  3  0  6  4  0  6  1  5  7
poids    :  1  3  1  3  1  3  1  3  1  3  1  3  1
produits :  9 21  8  0  3  0  6 12  0 18  1 15  7   → somme = 100 ✅
```

| # | Test | Comportement attendu |
|---|---|---|
| 1 | `refuseIsbnNull` | `null` → `false` (pas d'exception !) |
| 2 | `refuseIsbnVide` | `""` → `false` |
| 3 | `refuseIsbnSansTreizeChiffres` | moins ou plus de 13 chiffres → `false` |
| 4 | `refuseIsbnAvecCaracteresInterdits` | lettre, espace… → `false` |
| 5 | `accepteIsbnValide` | clé correcte → `true` |
| 6 | `refuseIsbnAvecMauvaiseCle` | clé fausse → `false` |
| 7 | `accepteIsbnValideAvecTirets` | les tirets `-` sont ignorés (mais pas les espaces) |
| 8 | `accepteIsbnAvecCleZero` | somme des 12 premiers produits multiple de 10 → la clé vaut **0** *(ajouté après relecture)* |
| 9 | `refuseQuatorzeChiffresQuiTombentJuste` | 14 chiffres → `false`, même si le calcul « tombe juste » *(ajouté après relecture)* |
| 10 | `accepteIsbnAvecTiretsPlacesLibrement` | n'importe quel nombre de tirets, à n'importe quelle position *(ajouté après relecture)* |

## Étape 5 — Refactor 🔧

Une fois **tout au vert** :

- Relis ton code : les noms sont-ils clairs ? Y a-t-il des « nombres magiques » (13, 10, 3) qui mériteraient une constante ?
- Relance `.\mvnw.cmd test` après chaque amélioration : ça doit rester vert.

## Étape 6 — Soumettre

Quand `.\mvnw.cmd test` affiche `BUILD SUCCESS` :

1. Commit ton travail sur Git
2. Demande la relecture à Claude
3. Prépare-toi à **expliquer** tes choix (gestion du `null`, comment tu parcours les chiffres, etc.)

## Rappel des règles

- ❌ On ne modifie **jamais** un fichier de test
- ✅ Code **minimal** pour passer le test courant, puis on avance
- 📖 Un test rouge ? On lit le message **avant** de toucher au code
