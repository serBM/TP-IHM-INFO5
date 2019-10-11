
# TP1 IHM Avancée - INFO5 Polytech - 2019-2020

Le but de ce premier TP est d'implémenter un Range Slider. Le sujet détaillé est disponible sur ce dépot Git.

**Deadline de remise du TP :**  Vendredi 18 octobre 2019 à 8h du matin.

**Membres du groupe :** VANDAL Jade, VINCENT Maxence, THOMAS Antoine


# Explications

Nous créons une fenêtre application qui va contenir 3 panels :
(1) Le nombre de biens immobiliers correspondants à nos critères de recherche
(2) Les ranges sliders permettant d'affiner les critères de recherche
(3) La liste des biens immobiliers correspondant aux critères de recherche

Pour le panel (2) : nous créons successivement 4 ranges sliders (distance à A, distance à B, prix, nombre de chambres)

Pour le panel (3) : nous affichons un par un dynamiquement les biens immobiliers

À la création de la fenêtre, une liste de biens immobiliers est générée avec des données aléatoires. Lorsque l'on modifie les valeurs des ranges sliders, on filtre dans cette liste les biens qui correspondent aux critères. On retourne alors cette liste dans le panel (3). 


## Éléments implémentés

Nous avons 3 packages qui implémentent le modèle MVC : `controller`, `model`, `views`.

  `RangeSlider` contient le Controller et la View du range slider.
L'application  `HomeFinder` importe le `RangeSlider` afin de l'afficher.
Le  `RangeSlider` importe Model.

L'affichage de notre application se trouve dans la classe `HomeFinder`.

Nous avons également un objet `Home` pour le bien immobilier avec son controller associé `HomeController`.

## Mode d'utilisation

**Pour lancer l'application il faut lancer le main du `HomeFinder`.**

Nous générons un nombre prédéfini (ici *10*) de biens immobiliers dont les informations sont tirées aléatoirement. Ce nombre (`nbHomes`) est défini dans la fonction `main` du `HomeFinder`.

Les attributs (minimum, maximum) des ranges sliders sont définis dans le `HomeFinder` au début (`aDistA, bDistA, aDistB, bDistB, aPrice, bPrice, aRooms, bRooms`).
