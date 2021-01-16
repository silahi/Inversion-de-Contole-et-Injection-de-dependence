# Principe d'Inversion de contrôle et d'Injection de dépendence 

## Objectif 
Créer une application qui utilise l'inversion de contrôle et L'injection de dépendence par  Programmation pure (Sans Framworks) 
et en utilisant un Framwork [Spring] (Par fichier de config et par annotation)

- Utilisation d'un couplage faible
- Programmation par interface
- L'application doit être fermée à la modification et être uverte à l'extension

## Description
L'application consite à réaliser un petit calculateur numérique qui permet de faire les 4 opérations suivantes : 
- Addition
- Soustraction
- Multiplication
- Division

Note : Nous avons implémenté seulement la fonction d'addition dans ce tutoriel. les 3 autres opérations restantes sont laisées aux lecteurs
Vous pouvez cloner le projet et implémenter les autres fonctionnalités. Vous n'aurez donc pas bésoin de modifier le code existant , Vous 
aurez juste à ajouter les fonctionnalités que vous voulez.

 
### Description des classes et Injerfaces
- **INombre** : Interface qui contient deux methodes pour l'initialisation des deux nombres qui seront utilisés pour faire l'opération
- **NombreImpl** : Classe qui implémente l'interface INombre 
- **IRunner** : Interface qui contient une methode run pour le démarrage de l'application
- **RunnerImpl**: Implémentation de l'interface IRunner
- **IAddition** : Interface qui contient la methode somme pour l'opération d'addition
- **AdditionImpl** : Implémentation de l'interface IAddition

## Plan 
Nous allons voir en prémier lieu


 

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/). 
