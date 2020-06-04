Le projet battleship a pour but de créer un jeu qui reproduise le célèbre jeu de bataille naval, un jeu de plateau très connu. Ici, toute la difficulté était de le coder. 
Grâce au diagramme de classe que nous a fourni notre professeur, nous avons pu implémenter le jeu. La difficulté était qu’il devait y avoir trois modes de jeu : 
un mode de jeu entre humain, un mode de jeu entre un humain et une machine et un mode de jeu entre machine. Tous ces modes de jeu fonctionnent. En mode HH, il est très 
simple de jouer avec un ami. Il suffit de créer un fichier de placement de bateaux pour chaque utilisateur et régler un fichier de configuration pour la partie. Dans le 
mode HA, il suffit de créer, paramétrer un fichier de configuration dans ce mode et un fichier de placement de bateau pour le joueur humain, le joueur automatique va créer 
sa propre flotte. Dans le mode AA, il suffit de créer un fichier de configuration et le joueur automatique va se charger du reste. Le joueur automatique a été source d’une 
grande attention dans mon travail. Premièrement dans la méthode ShipPlacement, je peux placer automatiquement et au hasard des bateaux de la flotte. Cependant, il doit y 
avoir 20% de square non-interdit c’est-à-dire ni libre ni à côté d’un bateau. Secondement, Dans newShot, j’ai essayé de faire une intelligence artificielle, elle fonctionne 
mais en revanche il y a une erreur que je n’ai pu résoudre : lorsque le bot trouve un bateau s’il ne le trouve pas à une extrémité du bateau en premier il part d’un côté et 
ne partira que vers ce côté : il boucle à l’infini. Le problème est qu’il faudrait que j’étudie une extrémité du bateau avec un carré touché précédemment en extrémité. Mais 
je n’ai eu le temps de corriger ce défaut, donc newShot fonctionne avec des valeurs au hasard cependant le bot ne peut pas tirer deux fois au même endroit.
Il y a deux fichiers : un fichier de configuration qui déclare les bateaux, le mode, et la taille de la grille, et un fichier de placement de bateau où l’on place uniquement 
les bateaux. Attention pour le jar, le dossier data doit rester au niveau du fichier battleship_jar. 
La commande pour exécuter le jar est : java -jar battleship.jar 
Ensuite le programme va vous demander toutes les informations nécessaires. 
