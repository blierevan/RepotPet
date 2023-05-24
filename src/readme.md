Nom: Blier
Prénom: Evan

Pour cloner le projet Git
1-Ouvrez JetBrains 
2-Cliquez sur "Check out from Version Control" ou "Get from VCS" dans l'écran de démarrage de JetBrains, ou accédez à "File" -> "New" -> "Project from Version Control".
3-Sélectionnez Git parmi les options disponibles.
4-Copiez l'URL du projet Git que vous souhaitez cloner.
5-Collez l'URL dans le champ "URL" de la fenêtre de clonage.
6-Choisissez le répertoire de destination où vous souhaitez enregistrer le projet cloné sur votre ordinateur.
7-Cliquez sur "Clone" 

Configurer le projet dans JetBrains
Assurez-vous que vous avez correctement configuré le SDK approprié pour votre projet. 
Sinon si nécessaire, vous pouvez le faire en accédant à "File" -> "Project Structure" et 
en sélectionnant le SDK approprié dans les paramètres du projet.

Connecter le projet à la base de données

1-Dans votre projet, recherchez les fichiers de configuration qui gèrent les paramètres de la base de données. 
Dans ce cas si c'est le fichier persistence.xml
2-Modifiez les fichiers de configuration pour inclure les informations de connexion à votre base de données.
Il faut modifier cette ligne de code <property name="javax.persistence.jdbc.url" value="jdbc:mariadb://localhost:3306/petstore" />
Si vous utiliser mysql il faut changer mariadb par mysql
Puis si vous utiliser un autre port il faut changer 3306 de même pour l'URL
3-Ensuite il faut créer une BDD avec le nom qui correspond à ce que vous avez mis.
4-Depuis le main il faut lancer le projet et si tous célà fonctionne la BDD se remplira automatiquement.