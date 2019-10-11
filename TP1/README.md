
# TP1 IHM Avancée - INFO5 Polytech - 2019-2020

Le but de ce premier TP est d'implémenter un Range Slider. Le sujet détaillé est disponible sur ce dépot Git.

**Deadline de remise du TP :**  Vendredi 18 octobre 2019 à 8h du matin.

**Membres du groupe :** VANDAL Jade, VINCENT Maxence, THOMAS Antoine


# Explications

Nous créons une fenêtre application qui va contenir 4 panels :
(1) Le nombre de biens immobiliers correspondants à nos critères de recherche
(2) Les ranges sliders permettant d'affiner les critères de recherche
(3) La liste des biens immobiliers correspondant aux critères de recherche
(4) Une carte affichant le point A, le point B et les biens immobiliers par rapport à ces points.

Pour le panel (2) : nous créons successivement 4 ranges sliders (distance à A, distance à B, prix, nombre de chambres)

Pour le panel (3) : nous affichons un par un dynamiquement les biens immobiliers

Pour le panel (4) : nous affichons chaque bien immobilier correspondant aux critères de recherche ainsi que les points A et B servant de repère.

À la création de la fenêtre, une liste de biens immobiliers est générée avec des données aléatoires. Lorsque l'on modifie les valeurs des ranges sliders, on filtre dans cette liste les biens qui correspondent aux critères grâce aux ranges sliders (2). On "efface" alors les panels et on les re affiche avec la liste mise à jour dans les panels (3) et (4). 


## Éléments implémentés

Nous avons 3 packages qui implémentent le modèle MVC : `controller`, `model`, `views`.

`RangeSlider` contient le Controller et la View du range slider.
L'application  `HomeFinder` importe le `RangeSlider` afin de l'afficher.
Le  `RangeSlider` importe Model.
`Map` est une View permettant d'afficher la carte.

L'affichage de notre application se trouve dans la classe `HomeFinder`.

Nous avons également un objet `Home` pour le bien immobilier avec son controller associé `HomeController`.

## Mode d'utilisation

**Pour lancer l'application il faut lancer le main du `HomeFinder`.**

Nous générons un nombre prédéfini (ici *10*) de biens immobiliers dont les informations sont tirées aléatoirement. Ce nombre (`nbHomes`) est défini dans la fonction `main` du `HomeFinder`.

Les attributs (minimum, maximum) des ranges sliders sont définis dans le `HomeFinder` au début (`aDistA, bDistA, aDistB, bDistB, aPrice, bPrice, aRooms, bRooms`).

Les coordonnées des points A et B ont été choisis au préalable, de façon à ce qu'ils soient suffisamment proches pour satisfaire la condition : distance entre A et le bien < 200 et distance entre B et le bien < 200. Nous étions partis sur un nombre égal à 30 (comme dans l'énoncé du TP) mais étant donné que la carte fait une taille de 400x400, nous avons du adapter ce nombre.
