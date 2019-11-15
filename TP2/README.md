
  

# TP2 IHM Avancée - INFO5 Polytech - 2019-2020

  

Le but de ce second TP est de réaliser un *menu circulaire* ou *marking menu*, widget qui n'est pas fourni par les boîtes à outils classiques.

Le sujet détaillé est disponible sur ce dépot Git.

  

**Deadline de remise du TP :** Vendredi 15 novembre 2019 à 10h du matin.

  

**Membres du groupe :** VANDAL Jade, VINCENT Maxence, THOMAS Antoine

  
  

# Explications

  

Notre TP est visualisable dans le répértoire **TP**.

Nous avons organisés notre code selon la méthode MVC. Le model est le classe Menu (calcul du placement de la souris dans le menu, dessin du menu), la view est la classe paint (affichage de l'interface de dessin) et le controller est la classe Controller.


## Éléments implémentés

 Nous sommes partis d'un code fourni qui consistait en une version d'un *Paint miniature* (une application de dessin).

Nous avons dans un premier temps implémenté plusieurs sortes de tracés (ellipse, trait à main levée, ligne droite, rectangle, rectangle arrondi). 
Ensuite, nous avons ajouté plusieurs choix de couleurs. La couleur noire étant la couleur par défaut au tout début.

Une sorte de tooltip a également été ajoutée afin de permettre à l'utilisateur de pouvoir visualiser quelle couleur et quelle forme sont choisies. Cette tooltip suit les mouvements de la souris. (elle est implémenté dans paint)
 
Pour le menu, dans paint, nous donnons au constructeur de menu la taille souhaité et les titres des choix. Le constructeur fait le reste. Ainsi, il est facilement modifiable et peut être exporté.
  

## Mode d'utilisation

  
Nous avons 2 modes de choix du menu différent et 1 mode expert.
L'utilisation du menu diffère entre un utilisateur souris et un utilisateur pad. Nous avons donc choisi d'ajouter un choix utilisateur selon son mode d'utilisation. Les modes pad et souris sont respectivement plus facilement utilisable avec soit un pad soit une souris.

Le **mode pad** (si le bouton souris n'est pas cliqué) : il faut faire un clic droit pour que le menu s'affiche. Il faut sortir du menu pour que le choix entre Couleurs et Formes soit validé.
Un nouveau menu s'ouvre (Couleur ou Forme) et il faut de nouveau sortir du menu pour valider son choix.

Le **mode souris** (si le bouton souris est cliqué) : il faut appuyer sur le bouton droit de la souris et dragguer dans le menu.Il faut sortir du menu pour que le choix entre Couleurs et Formes soit validé.
Un nouveau menu s'ouvre (Couleur ou Forme), et pour que le choix soit validé, il faut soit relacher le clic droit sur une selection, soit sortir du menu.

Le **mode expert** (si le bouton expert est cliqué) : il est combinable avec le mode pad et le mode souris. C'est un mode déstiné aux personnes qui connaissent par coeur les menus. Il n'y a donc plus aucun affichage de menus. L'utilisateur sait cependant toujours l'outil selectionné grâce au tooltip évoqué plus haut.
