Premier changement:
Nouveau noms pour la classe ainsi que l'interface.
Les anciens noms ne présentaient que la moitié de leur utilité.

Deuxième changement:
Implémentation des nouvelles signatures de fonctions dans l'interface. 

Troisième changement:
Nouveau constructeur implémentant les chemins vers symptoms.txt et result.out ainsi que l'initialisation de toutes les variables utilisées dans toutes la classe;
ainsi que l'implémentation des nouvelles fonctions dans la classe.

Quatrième changement:
Refactoring complet du AnalyticsCounter.java; celui-ci ne correspondait pas à la philosophie de programmation orientée objet et était devenu totalement inutile.
En effet, la majorité du code implémenté dans AnalyticsCounter.java a été découpé en fonction et déplacé dans ReadWriteSymptomsData et ISymptomsReaderWriter.
Il ne reste plus, dans AnalyticsCounter, que l'initialisation de la classe et l'appel des fonctions pour le rendu final dans result.out.
