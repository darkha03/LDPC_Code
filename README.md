# Codes LDPC (Low-Density Parity-Check)

## 📖 Introduction

Ce projet est une implémentation complète des **codes LDPC** (Low-Density Parity-Check), une classe de codes correcteurs d'erreurs linéaires très performants utilisés en télécommunications et en stockage de données. 

Les codes LDPC offrent une capacité de correction d'erreurs proche de la limite de Shannon, ce qui signifie qu'ils peuvent corriger des erreurs de manière très efficace, même avec des taux d'erreur élevés. Ce projet explore l'encodage et le décodage de messages en utilisant l'algorithme de passage de messages basé sur le **graphe de Tanner**.

### Cas d'usage
- 🛰️ Communications par satellite
- 📡 Télécommunications 5G
- 💾 Systèmes de stockage (disques durs, mémoire flash)
- 🔐 Canaux de transmission bruyants

---

## 🛠️ Stack Technologique

### Langage
- **Java** : Implémentation complète des algorithmes et structures de données

### Concepts et Algorithmes
- **Algèbre linéaire binaire** (opérations modulo 2)
- **Graphe de Tanner** : Représentation bipartite du code LDPC
- **Algorithme de passage de messages (Message Passing)** : Décodage itératif
- **Matrices de contrôle de parité (H)** et **matrices génératrices (G)**

### Structures de données
- Classes `Matrix` pour manipulations de matrices binaires
- Représentation par listes d'adjacence pour le graphe de Tanner

---

## ✨ Features

### 1. **Manipulation de Matrices Binaires**
   - Créer et charger des matrices binaires
   - Opérations mathématiques (addition, multiplication, transposition)
   - Échanges de lignes/colonnes
   - Affichage et sérialisation des matrices

### 2. **Génération de Codes LDPC**
   - Génération de matrice génératrice `G` à partir de la matrice de contrôle `H`
   - Encodage de messages en mots de code

### 3. **Graphe de Tanner**
   - Construction du graphe bipartite représentant le code
   - Représentation efficace par adjacence (nodes de contrôle et variables)
   - Identification des voisins pour chaque nœud

### 4. **Décodage Itératif**
   - Implémentation du décodage par passage de messages
   - Correction d'erreurs dans les mots de code reçus
   - Support de multiples itérations pour améliorer la convergence
   - Détection automatique quand tous les contrôles de parité sont satisfaits

### 5. **Données de Test**
   - Matrices LDPC pré-configurées de différentes tailles :
     - `matrix-15-20-3-4` : 15 contrôles de parité, 20 variables
     - `matrix-2000-6000-5-15` : 2000 x 6000 (large instance)
     - `Matrix-2048-6144-5-15` : 2048 x 6144 (très grande instance)

### 6. **Démonstration Complète**
   - Encodage d'un message
   - Injection d'erreurs
   - Calcul de syndrome
   - Décodage et récupération du message original

---

## 📁 Structure du Projet

```
LDPC-starter/
├── Main.java          # Point d'entrée avec démonstration
├── Matrix.java        # Classe pour manipuler matrices binaires
├── TGraph.java        # Implémentation du graphe de Tanner
└── data/              # Matrices LDPC pour les tests
    ├── matrix-15-20-3-4
    ├── matrix-2000-6000-5-15
    └── Matrix-2048-6144-5-15
```

---

## 🚀 Utilisation

```bash
# Compiler le projet
javac LDPC-starter/*.java

# Exécuter la démonstration
java -cp LDPC-starter Main
```

---

## 📚 Références

- **LDPC Codes** : Mackay & Neal, 1996
- **Graphe de Tanner** : Tanner, 1981
- **Décodage itératif** : Gallager, 1962