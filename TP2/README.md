
  

# TP2 IHM Avancée - INFO5 Polytech - 2019-2020

  

Le but de ce second TP est de réaliser un *menu circulaire* ou *marking menu*, widget qui n'est pas fourni par les boîtes à outils classiques.

Le sujet détaillé est disponible sur ce dépot Git.

  

**Deadline de remise du TP :** Vendredi 15 novembre 2019 à 10h du matin.

  

**Membres du groupe :** VANDAL Jade, VINCENT Maxence, THOMAS Antoine

  
  

# Explications

  

Notre TP est visualisable dans le répértoire **TP**.

La classe principale contenant notre main est : `paint.java`

  
  

## Éléments implémentés

 Nous sommes partis d'un code fourni qui consistait en une version d'un *Paint miniature* (une application de dessin).

Nous avons dans un premier temps implémenté plusieurs sortes de tracés (ellipse, trait à main levée, ligne droite, rectangle, rectangle arrondi). 
Ensuite, nous avons ajouté plusieurs choix de couleurs. La couleur noire étant la couleur par défaut au tout début.

Une sorte de tooltip a également été ajoutée afin de permettre à l'utilisateur de pouvoir visualiser quelle couleur et quelle forme sont choisies. Cette tooltip suit les mouvements de la souris.
  
  

## Mode d'utilisation

  

Afin d'ouvrir notre menu, il faut effectuer un **clic droit**.

Le menu s'ouvre alors, donnant à l'utilisateur le choix entre `Couleurs` et `Formes`. 

Pour sélectionner l'un ou l'autre, il faut passer la souris sur l'endroit sélectionné. Il n'est pas nécessaire de cliquer pour sélectionner, un simple trait dans la direction de l'item à sélectionner suffit. Pour sortir du menu, il faut passer la souris sur `Back`. 

Pour dessiner, il faut effectuer un clic gauche et rester appuyer le temps de dessiner. Enfin, relâcher pour terminer de dessiner.