Le projet battleship a pour but de cr�er un jeu qui reproduise le c�l�bre jeu de bataille naval, un jeu de plateau tr�s connu. Ici, toute la difficult� �tait de le coder. 
Gr�ce au diagramme de classe que nous a fourni notre professeur, nous avons pu impl�menter le jeu. La difficult� �tait qu�il devait y avoir trois modes de jeu�: 
un mode de jeu entre humain, un mode de jeu entre un humain et une machine et un mode de jeu entre machine. Tous ces modes de jeu fonctionnent. En mode HH, il est tr�s 
simple de jouer avec un ami. Il suffit de cr�er un fichier de placement de bateaux pour chaque utilisateur et r�gler un fichier de configuration pour la partie. Dans le 
mode HA, il suffit de cr�er, param�trer un fichier de configuration dans ce mode et un fichier de placement de bateau pour le joueur humain, le joueur automatique va cr�er 
sa propre flotte. Dans le mode AA, il suffit de cr�er un fichier de configuration et le joueur automatique va se charger du reste. Le joueur automatique a �t� source d�une 
grande attention dans mon travail. Premi�rement dans la m�thode ShipPlacement, je peux placer automatiquement et au hasard des bateaux de la flotte. Cependant, il doit y 
avoir 20% de square non-interdit c�est-�-dire ni libre ni � c�t� d�un bateau. Secondement, Dans newShot, j�ai essay� de faire une intelligence artificielle, elle fonctionne 
mais en revanche il y a une erreur que je n�ai pu r�soudre�: lorsque le bot trouve un bateau s�il ne le trouve pas � une extr�mit� du bateau en premier il part d�un c�t� et 
ne partira que vers ce c�t�: il boucle � l�infini. Le probl�me est qu�il faudrait que j��tudie une extr�mit� du bateau avec un carr� touch� pr�c�demment en extr�mit�. Mais 
je n�ai eu le temps de corriger ce d�faut, donc newShot fonctionne avec des valeurs au hasard cependant le bot ne peut pas tirer deux fois au m�me endroit.
Il y a deux fichiers�: un fichier de configuration qui d�clare les bateaux, le mode, et la taille de la grille, et un fichier de placement de bateau o� l�on place uniquement 
les bateaux. Attention pour le jar, le dossier data doit rester au niveau du fichier battleship_jar. 
La commande pour ex�cuter le jar est�: java -jar battleship.jar 
Ensuite le programme va vous demander toutes les informations n�cessaires. 
