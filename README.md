# projectOne_Calculatrice
OpenClassRoom Java Class - TP5 Calculatrice

Premier programme Java - Repository GitHub créé le : 
- 25 Février 2020, 10h03 
- Ho Chi Minh, Vietnam 
- (US Consulate - American Center) 

[Créer une calculatrice qui :]
- permet de faire des opérations à la suite (différentes natures d'opérations possibles)
- qui affiche dans le JLabel la somme (provisoire et/ou finale) uniquement quand l'opération en cours n'est  
-- ni une division  
-- ni une multiplication
- utilisation de JFrame, JPanel, BoxLayout, JButton
- utilisation de la classe ActionListener, de la méthode ActionPerformed
 
[Quelques règles de gestion :]
- [quand on commence un produit (multiplication ou division)]
-- on retient l'opération précédente (addition, ou soustraction) = variable operationFinProduit
-- on traite le produit tant qu'il y a des multiplications, divisions
-- à la fin du traitement du produit, on reprend la somme provisoire avant ouverture du produit et on : 
--- additionne le produit à la somme provisoire si operationFinProduit = "+"
--- ou on soustrait le produit à la somme provisoire si operationFinProduit = "-"
    
- [quand deux opérations se suivent,] on retient la dernière (simulation du comportement calculatrice sur MAC)
-- exemple, si je fais "-" "-" : je ne le transforme pas en "+"
-- exemple, si je fais "*" "-" : je ne fais pas de multiplication par un négatif mais une soustraction
-- etc.

