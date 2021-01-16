# Inversion-de-Contole-et-Injection-de-dependence
Principe d'injection de dépendence et d'inversion de contrôle avec Spring
# Objectif : Créer une application qui soit fermée à la modification et être ouverte à l'extension
L'application consite à créer un calculateur qui permet d'effectuer les opérations suivantes : 
[-] L'addition
[-] La soustraction
[-] La division 
[-] Le produit 
de deux nombres en respectant le principe d'injection de dépendence, inversionn de contrôle et couplage faibe.
Pour faire cela, on a plusieurs approches :
[Approche par programmation sans Framwork ]
[Approche par fichier de configuration avec spring]
[Approche par annotation avec spring]

Dans cette application, on a élaboré les approches citées ci dessu 
Comme je viens de le dire , l'application doit être ouverte à l'extension et être fermée à la modification : pour la prémière version de 
l'application , j'ai seulement implémenté l'addition ce qui signifie que si je veux implémenter les 3 autres opérations je n'ai pas bésoin de 
modifier le code  que j'ai déjà écrit : je vais juste ajouter les autres fonctionnalités

