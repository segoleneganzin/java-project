# java-project

Application bureau en Java dans le cadre du BTS Services informatiques aux organisations, option Solutions Logicielles et Applications Métier <br>

## Thématique fictive :

Les piscines municipales de Vannes vont changer de logiciel afin de générer directement des codes permettant d'accéder aux bassins. <br>
Ces codes pourront être achetés sur des bornes situées à la piscine, sans aucune inscription. <br>
Nous ne traitons pas les problématiques de paiement en ligne, mais ce processus est simulé.  <br>

La borne permettra de générer des codes pour obtenir :
    • une leçon individuelle de nage sur un des créneaux libres
    • un abonnement solo 10 entrées valable 10 mois
    • un abonnement duo 10 entrées valable 1 an
    
Les administrateurs authentifiés pourront définir :
    • le tarif
    • le nombre de places
    • la durée de validité des titres de vente


La configuration proposée est la plus générique/modulable possible.<br>

N'importe qui pourra tester la validité d'un code et voir son contenu actuel.

## Langages et technologies utilisés

<ul>
  <li>Java</li>
  <li>JavaFX</li>
  <li>Scene Builder</li>
  <li>Microsoft SQL Server</li>
  <li>IDE Eclipse</li>
  <li>Notion</li>
</ul>

## Version

Version 1

## Github

https://github.com/segoleneganzin/java-project

## Installation du projet :
### Prérequis :
<ul>
  <li>JavaSE-13</li>
  <li>Un IDE Java (Eclipse)</li>
  <li>JavaFX sdk 19</li>
  <li>Scene Builder</li>
</ul>

### Mise en place :
Il faut importer le projet zippé « projet_Bureau_Java » dans l’IDE Java (Eclipse).

### Mise en place de la base de données sur Microsoft SQL Server Management Studio
#### Mise en place d'une connexion si nécessaire 
Dans l’onglet sécurité > Connexions, il faut créer une nouvelle connexion s’il n’en existe pas déjà.<br>
Il faut cocher « Authentification SQL Server » et compléter les informations.<br>
Il faut ensuite se rendre dans « Mappage d’utilisateur » dans le menu de gauche.<br>
Puis sélectionner la base de données que l’on souhaite, cela pourra être modifié un fois les scripts du projet importés.<br>
Il faut ensuite se connecter avec les identifiants générés et importer les scripts.<br>
Modifier les droits d’accès à la base de données :<br>
Il faut se rendre dans les propriétés > Mappage d’utilisateur et cocher la base de données souhaitée, en cochant le statut « db_owner » pour avoir tous les droits dessus.

## Modification dans le projet Java
Il faut modifier le fichier « connexion.java » dans le package « dao » afin de mettre en relation l’application avec la base de données Microsoft SQL Server.<br>
Dans run configuration, mettre le bon path du javafx sdk, dans « arguments ».<br>
Vérifier que la version de Java soit la bonne dans le buildpath du projet  : JavaSE-13<br>

## Lancer le projet
Depuis l’IDE il faut « runner » le projet "as java application".

## Contributeurs

Développée par Rémi Cousin, Lionel Delaby, Ségolène Ganzin, Killian Posseme.
