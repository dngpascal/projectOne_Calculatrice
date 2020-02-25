/* - - - - - - - - - - - - - IMPORT SWING ET AWT - - - - - - - - - - - - - - - - - - */

import javax.swing.* ;
import javax.swing.border.Border;  
import javax.swing.BorderFactory ;
import java.awt.* ;
import java.awt.event.* ; 
import java.text.DecimalFormat ; 

/* - - - - - - - - - - - - - FONCTION MAIN - - - - - - - - - - - - - - - - - - - - - */

public class Calculatrice extends JFrame implements ActionListener
{  
	/* SET UP - VARIABLES, COMPONENTS, ETC... */
	
	// BOUTONS DU SOUS CLAVIER CHIFFRE 0-9, VIRGULE ET RESULTAT
	static JButton bouton1 = new JButton("1") ;
	static JButton bouton2 = new JButton("2") ;
	static JButton bouton3 = new JButton("3") ;
	static JButton bouton4 = new JButton("4") ;
	static JButton bouton5 = new JButton("5") ;
	static JButton bouton6 = new JButton("6") ;
	static JButton bouton7 = new JButton("7") ;
	static JButton bouton8 = new JButton("8") ;
	static JButton bouton9 = new JButton("9") ;
	static JButton bouton0 = new JButton("0") ;
	static JButton boutonVirgule = new JButton(".") ;  
	static JButton boutonResultat = new JButton("=") ;
	
	// BOUTONS DU SOUS CLAVIER OPERATIONS ET EFFACER
	 
	static JButton boutonEffacer = new JButton("C") ; 
	static JButton boutonSomme = new JButton("+") ;
	static JButton boutonSoustraction = new JButton("-") ;
	static JButton boutonMultiplication = new JButton("*") ;
	static JButton boutonDivision = new JButton("/") ;
	
	// MISE EN FORME 
	Border blackBorder = BorderFactory.createLineBorder(Color.black) ; 
	JLabel falseBox = new JLabel("   ") ;
		
	// VARIABLES AUTRES POUR LE CALCUL AREA
	
	JLabel calculArea = new JLabel("0") ;
	static int compteurOperation = 0 ;
	static float somme = 0 ;  
	static float sousSomme = 0 ; 
	static float valeurB = 0 ;
	static float valeurC = 0 ; 
	static boolean saisieEnCours = false ;
	static boolean nombreInteger = true ;
	static String operationAVenir = "+" ;
	static String operationEffectuee = "" ;
	static String operationFinProduit = "" ;
	static String textBouton = "" ;
	 
	/* - - - - - - - - - - - - - FONCTION MAIN - - - - - - - - - - - - - - - - - - - - */
	
	public static void main(String[] args) 
	{
		System.out.println("- - - - -") ;
		System.out.println(" ") ;
		System.out.println("DEBUT DU PROGRAMME") ;
		System.out.println("Somme : " + somme) ;
		System.out.println("CompteurOperation : " + compteurOperation) ;
		System.out.println(" ") ;
		System.out.println("- - - - -") ;
		Calculatrice calculatrice = new Calculatrice() ; 
	} 
	
	/* - - - - - - - - - - - - - CONSTRUCTEUR CALCULATRICE  - - - - - - - - - - - - - */ 
	
	public Calculatrice()
	{
		// MISE EN PLACE DU JFRAME 
		this.setTitle("Calculatrice") ;
		this.setSize(340, 250);
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		this.setResizable(false) ;   
		
		// MISE EN FORME DU SOUS CLAVIER CHIFFRES, VIRGULE ET RESULTAT
		Box b1 = Box.createHorizontalBox() ;
		
		b1.add(bouton1) ;
		b1.add(bouton2) ;
		b1.add(bouton3) ; 
		
		Box b2 = Box.createHorizontalBox() ;
		
		b2.add(bouton4) ;
		b2.add(bouton5) ;
		b2.add(bouton6) ; 
		
		Box b3 = Box.createHorizontalBox() ;
		
		b3.add(bouton7) ;
		b3.add(bouton8) ;
		b3.add(bouton9) ;
		
		Box b4 = Box.createHorizontalBox() ;
		
		b4.add(bouton0) ;
		b4.add(boutonVirgule) ;
		b4.add(boutonResultat) ;
		
		Box b5 = Box.createVerticalBox() ;
		
		b5.add(b1) ; 
		b5.add(b2) ; 
		b5.add(b3) ;
		b5.add(b4) ;
		
		// MISE EN FORME DU SOUS CLAVIER OPERATIONS ET EFFACER
		
		Box b6 = Box.createVerticalBox() ; 
		
		boutonEffacer.setForeground(Color.red) ;
		b6.add(boutonEffacer) ;
		b6.add(boutonSomme) ;
		b6.add(boutonSoustraction) ;
		b6.add(boutonMultiplication) ;
		b6.add(boutonDivision) ; 
		
		// MISE EN FORME DU CLAVIER SANS LE JLABEL : FUSION CHIFFRES + OPERATIONS
		
		Box b7 = Box.createHorizontalBox() ; 
		JPanel clavierPanel = new JPanel() ; 
		clavierPanel.add(b5) ; 
		clavierPanel.add(b6) ; 
		clavierPanel.setBorder(blackBorder) ;
		b7.add(clavierPanel) ; 
		
		
		// MISE EN FORME DU JLABEL  
		
		Box b8 = Box.createHorizontalBox() ;  
		JPanel calculAreaPanel = new JPanel() ;
		calculAreaPanel.add(calculArea) ;  
		calculAreaPanel.setBorder(blackBorder) ; 
		b8.add(calculAreaPanel) ;
		
		Box b9 = Box.createHorizontalBox(); 
		JPanel falsePanel = new JPanel() ; 
		falsePanel.add(falseBox) ;
		b9.add(falsePanel) ;
		  
		// MISE EN FORME DU CLAVIER DANS SON ENSEMBLE AVEC LE JLABEL
		
		Box b10 = Box.createVerticalBox() ;
		b10.add(b8) ;
		b10.add(b9) ; 
		b10.add(b7) ;
		 
		// FINALISATION DU JFRAME - SET VISIBLE ET SET CONTENT PANE
		JPanel clavier = new JPanel() ; 
		clavier.add(b10) ;   
		this.setContentPane(clavier) ;
		this.setVisible(true) ;
		
		/* - - - - - - - - MISE EN PLACE DES ACTIONS LISTENER  - - - - -  - - - - */ 
	 
		// CLASSE BOUTON CHIFFRE LISTENER 
		bouton1.addActionListener(new BoutonChiffreListener()) ;
		bouton2.addActionListener(new BoutonChiffreListener()) ;
		bouton3.addActionListener(new BoutonChiffreListener()) ;
		bouton4.addActionListener(new BoutonChiffreListener()) ;
		bouton5.addActionListener(new BoutonChiffreListener()) ;
		bouton6.addActionListener(new BoutonChiffreListener()) ;
		bouton7.addActionListener(new BoutonChiffreListener()) ;
		bouton8.addActionListener(new BoutonChiffreListener()) ;
		bouton9.addActionListener(new BoutonChiffreListener()) ;
		bouton0.addActionListener(new BoutonChiffreListener()) ; 
		
		// CLASSE BOUTON OPERATION LISTENER 
		
		boutonSomme.addActionListener(new BoutonOperationListener()) ;
		boutonSoustraction.addActionListener(new BoutonOperationListener()) ;
		boutonMultiplication.addActionListener(new BoutonOperationListener()) ;
		boutonDivision.addActionListener(new BoutonOperationListener()) ;
		
		// AUTRES LISTENERS : BOUTON RESULTAT, EFFACER ET VIRGULE
		
		boutonResultat.addActionListener(this) ;
		boutonEffacer.addActionListener(this) ; 
		boutonVirgule.addActionListener(this) ;
	}  
	
	/* - - - - - - ACTIONS PERFORMED : BOUTONS RESULTAT, EFFACER ET VIRGULE - - - - - */ 
	
	public void actionPerformed(ActionEvent arg0) 	
	{  
		// - - - - - - - - - - - - - - - BOUTON RESULTAT - - - - - - - - - - - - - - - 
		
		if (arg0.getSource() == boutonResultat)
		{  
			// Réalisation de l'opération précédente
			System.out.println(" ") ;
			System.out.println("BOUTON =") ;
			System.out.println("Résultat final demandé") ;
			
			operationEffectuee = operationAVenir ; 
			 
			switch (operationEffectuee)
			{
				case "+" :
					System.out.println("Somme provisoire avant opération précédente : " + somme) ;
					System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
					if (textBouton == "" && somme == 0 && compteurOperation == 0)
					{  
						valeurB = 0 ;
						somme = 0 ;   
					}  
					else if (compteurOperation == 0)
					{
						valeurB = Float.valueOf(textBouton) ;
						somme = valeurB ;
					} 
					else if (compteurOperation >= 1)
					{
						valeurB = Float.valueOf(textBouton) ; 
						somme = somme + valeurB ;
					}
					System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;    
					break ;
					
				case "-" : 
					valeurB = Float.valueOf(textBouton) ; 
					System.out.println("Somme provisoire avant opération précédente : " + somme) ;
					System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
					System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ; 
					somme = somme - valeurB ;
					break ;
					
				case "*" : 
					if (compteurOperation <=1)
					{
						System.out.println("Produit avant opération précédente : " + valeurC) ;
						System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
						valeurB = Float.valueOf(textBouton) ; 
						System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;   
						somme = valeurC * valeurB ;
						break ; 
					}
					else
					{ 
						if (operationFinProduit == "+")
						{
							valeurB = Float.valueOf(textBouton) ; 
							System.out.println(" ") ;
							System.out.println("OPERATION PRECEDENTE") ;
							System.out.println("Somme provisoire avant opération précédente : " + somme) ;
							System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
							System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
							System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;  
							sousSomme = sousSomme * valeurB ;
							
							System.out.println(" ") ;
							System.out.println("RESULTAT FINAL") ;
							System.out.println("Somme provisoire avant opération finale : " + somme) ;
							System.out.println("Nature de l'opération finale : " + operationFinProduit) ;
							System.out.println("Produit provisoire avant l'opération finale : " + sousSomme) ;
							somme = somme + sousSomme;  
							break ;
						}
						else if (operationFinProduit == "-")
						{
							valeurB = Float.valueOf(textBouton) ; 
							System.out.println(" ") ;
							System.out.println("OPERATION PRECEDENTE") ;
							System.out.println("Somme provisoire avant opération précédente : " + somme) ;
							System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
							System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
							System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;
							sousSomme = sousSomme * valeurB ;
							
							System.out.println(" ") ;
							System.out.println("RESULTAT FINAL") ;
							System.out.println("Somme provisoire avant opération finale : " + somme) ;
							System.out.println("Nature de l'opération finale : " + operationFinProduit) ; 
							System.out.println("Produit provisoire avant l'opération finale : " + sousSomme) ;
							somme = somme - sousSomme ; 
							break ; 
						} 
					} 
				case "/" :	
					valeurB = Float.valueOf(textBouton) ; 
					
					if (valeurB == 0)
					{ 
						System.out.println("Test") ;
						somme = 0 ;
						break ; 
					}
					else 
					{
						if (compteurOperation <=1)
						{
							valeurB = Float.valueOf(textBouton) ; 
							System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;  
							System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
							System.out.println("Produit avant opération précédente : " + valeurC) ; 
							somme = valeurC / valeurB ; 
							break ; 
						}
						else
						{
							if (operationFinProduit == "+")
							{ ; 
								System.out.println("Somme provisoire avant opération précédente : " + somme) ;
								System.out.println("Nature de l'opération en fin de produit : " + operationFinProduit) ;
								System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
								System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
								System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;   
								sousSomme = sousSomme / valeurB ;  
								somme = somme + sousSomme ;  
								break ;
							}
							else if (operationFinProduit == "-")
							{  
								System.out.println("Somme provisoire avant opération précédente : " + somme) ;
								System.out.println("Nature de l'opération en fin de produit : " + operationFinProduit) ;  
								System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
								System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
								System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;   
								sousSomme = sousSomme / valeurB ; 
								somme = somme - sousSomme ;  
								break ;
							}   
						} 
					}
			}  
			
			// Affichage integer versus float
			if (valeurB == 0 && operationEffectuee == "/")
			{
				calculArea.setText("Erreur") ; 
				System.out.println("Résultat impossible, division par zéro.") ;
			}
			else 
			{
				if (nombreInteger == true)
				{
					
					calculArea.setText(String.valueOf((int)somme)) ;
				}
				else 
				{
					DecimalFormat nf = new DecimalFormat("0.##") ;
					String sommeNF = nf.format(somme) ;
					calculArea.setText(sommeNF) ;
				}  
			}
			
			System.out.println("=>") ;
			System.out.println("Somme finale : " + somme) ;
			System.out.println(" ") ;
			System.out.println("- - - - -") ; 
			nombreInteger = true ; 
			textBouton = "0" ; 
			sousSomme = 0 ;
			operationAVenir = "=" ;
			
		}
		
		// - - - - - - - - - - - - - - - BOUTON EFFACER - - - - - - - - - - - - - - -  
		
		if (arg0.getSource() == boutonEffacer)
		{
			compteurOperation = 0 ;
			somme = 0 ; 
			saisieEnCours = false ; 
			textBouton = "" ;
			valeurB = 0 ; 
			operationAVenir = "+" ;
			calculArea.setText("0") ;
			System.out.println("- - - - -") ; 
			System.out.println(" ") ;
			System.out.println("Somme remise à zéro : " + somme) ;
			System.out.println("Compteur Operation remis à zéro : " + compteurOperation) ;
			System.out.println(" ") ;
			System.out.println("- - - - -") ; 
			System.out.println("- - - - -") ; 
		}
		
		// - - - - - - - - - - - - - - - BOUTON VIRGULE - - - - - - - - - - - - - - -  
		
		if (arg0.getSource() == boutonVirgule)
		{
			nombreInteger = false ;
			
			if (textBouton == "" && somme == 0)
			{ 
				somme = 0 ; 
				textBouton = "0." ;
				valeurB = 0 ;
			}
			else if (textBouton == "")
			{ 
				textBouton = "0." ; 
				valeurB = 0 ;
			} 
			else
			{
				textBouton = textBouton + "." ; 
				valeurB = Float.valueOf(textBouton) ; 
			}
			
		calculArea.setText(textBouton) ;
		System.out.println(" ") ;
		System.out.println("BOUTON .") ;
		System.out.println(" ") ;
		System.out.println("Nombre saisi pour le moment : " + textBouton) ;
		System.out.println(" ") ;
		System.out.println("- - - - -") ;
		
		} 
	}
	
	/* - - - - - ACTIONS PERFORMED: CLASSE INTERNE "BOUTON CHIFFRE LISTENER" - - - - */ 
	
	class BoutonChiffreListener implements ActionListener
	{   
		public void actionPerformed(ActionEvent e) 
		{
			saisieEnCours = true ;  
			
			while (saisieEnCours == true) 
			{ 
				JButton copieBoutonChiffre = (JButton) e.getSource();   
				textBouton = textBouton + copieBoutonChiffre.getText(); 
				System.out.println(" ") ;
				System.out.println("BOUTON " + copieBoutonChiffre.getText()) ; 
				System.out.println(" ") ;
				System.out.println("Nombre saisi pour le moment : " + textBouton) ;   
				System.out.println(" ") ;
				System.out.println("- - - - -") ;
				calculArea.setText(textBouton) ;
				saisieEnCours = false ; 
			}
		}
	}
	 
	/* - - - - ACTIONS PERFORMED: CLASSE INTERNE "BOUTON OPERATION LISTENER" - - - */
	
	class BoutonOperationListener implements ActionListener
	{   
		public void actionPerformed(ActionEvent e) 
		{
			saisieEnCours = false ;     
			JButton copieBoutonChiffre = (JButton) e.getSource();   
			String boutonClic = copieBoutonChiffre.getText();
			System.out.println(" ") ;
			compteurOperation = compteurOperation + 1 ;
			System.out.println("BOUTON " + boutonClic + " // OPERATION " + compteurOperation) ;   
			System.out.println(" ") ;
			System.out.println("OPERATION PRECEDENTE") ;
			 
			// GESTION DE CAS PARTICULIERS
			// Cas 1 : lancement de la calculatrice, j'appuie de suite sur une opération
			// Cas 2 : j'appuie deux fois de suite sur un bouton de type opération
			
			if (textBouton == "" && somme == 0 && compteurOperation == 0)
			{ 
				somme = 0 ; 
				textBouton = "0" ;
				valeurB = 0 ;
			}
			else if (textBouton == "")
			{ 
				textBouton = "0" ; 
				valeurB = 0 ;
			} 
 			
			// - - - - - - - - - - - - - - - BOUTON SOMME - - - - - - - - - - - - - - - - - -
			
			if (e.getSource() == boutonSomme)
			{ 
				// Réalisation de l'opération précédente
				operationEffectuee = operationAVenir ; 
				System.out.println("Somme provisoire avant opération précédente : " + somme) ; 
				valeurB = Float.valueOf(textBouton) ;  
				JButton copieBoutonOperation = (JButton) e.getSource();
				operationAVenir = copieBoutonOperation.getText();
				
				switch (operationEffectuee)
				{
					case "+" :
						System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
						System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;
						System.out.println("=>") ;
						somme = somme + valeurB ;
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ;
						// Préparation de l'opération à venir
						System.out.println(" ") ;
						System.out.println("OPERATION A VENIR") ; 
						System.out.println("Somme provisoire avant l'opération à venir : " + somme) ;  
						System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
						break ;
						
					case "-" :  
						System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
						System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;
						somme = somme - valeurB ;
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ;
						System.out.println(" ") ;
						System.out.println("OPERATION A VENIR") ; 
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ;  
						System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
						break ;
						
					case "*" :  
						System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
						System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
						System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;
						sousSomme = sousSomme * valeurB ; 
						System.out.println("=>") ;
						System.out.println("Produit provisoire après opération précédente : " + sousSomme) ;
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ;
						System.out.println(" ") ;
						System.out.println("OPERATION A VENIR") ;
						System.out.println("Somme provisoire avant opération à venir : " + somme) ;
						 
						if (operationFinProduit == "+")
						{
							System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
							System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
							somme = somme + sousSomme ;
							System.out.println("=>") ;
							System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
						}
						else if (operationFinProduit == "-")
						{
							System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
							System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
							somme = somme - sousSomme ;
							System.out.println("=>") ;
							System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
						} 
						operationFinProduit = "" ;
						sousSomme = 0 ;
						System.out.println("Produit provisoire après opération à venir : " + sousSomme) ;
						System.out.println("Nature de l'opréation à venir : " + operationAVenir) ;
						valeurC = 0 ; 
						break ;
						
					case "/" :	
						if (valeurB == 0)
						{
							calculArea.setText("Erreur") ;
							somme = 0 ;
							break ; 
						}
						else 
						{ 
							System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
							System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
							System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ; 
							sousSomme = sousSomme / valeurB ; 
							System.out.println("Produit provisoire après opération précédente : " + sousSomme) ; 
							valeurC = 0 ;
							System.out.println(" ") ;
							System.out.println("OPERATION A VENIR") ; 
							System.out.println("Somme provisoire avant l'opération à venir : " + somme) ; 
							
							if (operationFinProduit == "+")
							{
								System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
								System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
								somme = somme + sousSomme ;
								System.out.println("=>") ;
								System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
							}
							else if (operationFinProduit == "-")
							{
								System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
								System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
								somme = somme - sousSomme ;
								System.out.println("=>") ;
								System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
							}  
							
							sousSomme = 0 ;
							operationFinProduit = "" ;
							System.out.println("Produit provisoire après opération à venir : " + sousSomme) ;
							System.out.println("Nature de l'opération à venir : " + operationAVenir) ; 
							break;
						}
				} 
				
				// Réinitialisation du nombre à opérer à la prochaine opération
				valeurB = 0 ;
			}
			
			// - - - - - - - - - - - - - - - BOUTON SOUSTRACTION - - - - - - - - - - - - - - - - - -
			
			else if (e.getSource() == boutonSoustraction)
			{ 
				if (compteurOperation == 0 && textBouton == "" && somme == 0)
				{
					textBouton = "-0" ;  
				} 
				
				valeurB = Float.valueOf(textBouton) ;
				
				
				// Réalisation de l'opération précédente
				operationEffectuee = operationAVenir ;  
				
				JButton copieBoutonOperation = (JButton) e.getSource();
				operationAVenir = copieBoutonOperation.getText();
				
				switch (operationEffectuee)
				{
					case "+" :
						System.out.println("Somme provisoire avant opération précédente : " + somme) ;
						System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
						System.out.println("Nombre à opérer pour l'opération précédente : " + valeurB) ;
						somme = somme + valeurB ;
						System.out.println("=>") ;
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ; 
						
						System.out.println("") ;
						System.out.println("OPERATION A VENIR") ; 
						System.out.println("Somme provisoire avant l'opération à venir : " + somme) ;  
						System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
						break ;
					case "-" :  
						 System.out.println("Somme provisoire avant opération précédente : " + somme) ;
						 System.out.println("Nature de l'opération précédente : " + operationEffectuee) ;
						 System.out.println("Nombre à opérer pour l'opération précédente : " + valeurB) ;
						 somme = somme - valeurB ;
						 System.out.println("=>") ;
						 System.out.println("Somme provisoire après l'opération précédente : " + somme) ;
							
						 System.out.println(" ") ;
						 System.out.println("OPERATION A VENIR") ; 
						 System.out.println("Somme provisoire après l'opération précédente : " + somme) ;  
						 System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
						 break ; 
					case "*" : 
						operationEffectuee = operationFinProduit ;
						operationFinProduit = "" ; 
						sousSomme = sousSomme * valeurB ; 
						System.out.println("Sous-somme provisoire après opération précédente : " + sousSomme) ;
						somme = somme - sousSomme ;  
						sousSomme = 0 ;
						valeurC = 0 ;
						System.out.println(" ") ;
						System.out.println("OPERATION A VENIR") ; 
						System.out.println("Somme provisoire après l'opération précédente : " + somme) ;  
						System.out.println("Produit provisoire après l'opération précédente : " + sousSomme) ;
						System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
						break ;  
					case "/" :	
						if (valeurB == 0)
						{
							calculArea.setText("Erreur") ;
							somme = 0 ;
							break ; 
						}
						else 
						{
							System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
							System.out.println("Nature de l'opération précédente : " + operationEffectuee) ; 
							System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ; 
							sousSomme = sousSomme / valeurB ; 
							System.out.println("Produit provisoire après opération précédente : " + sousSomme) ; 
							valeurC = 0 ;
							System.out.println(" ") ;
							System.out.println("OPERATION A VENIR") ; 
							System.out.println("Somme provisoire avant l'opération à venir : " + somme) ; 
							
							if (operationFinProduit == "+")
							{
								System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
								System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
								somme = somme + sousSomme ;
								System.out.println("=>") ;
								System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
							}
							else if (operationFinProduit == "-")
							{
								System.out.println("Nature de l'opération à venir : " + operationFinProduit) ;
								System.out.println("Produit provisoire avant opération à venir : " + sousSomme) ;
								somme = somme - sousSomme ;
								System.out.println("=>") ;
								System.out.println("Somme provisoire après l'opération à venir : " + somme) ; 
							}  
							
							sousSomme = 0 ;
							operationFinProduit = "" ;
							System.out.println("Produit provisoire après opération à venir : " + sousSomme) ;
							System.out.println("Nature de l'opération à venir : " + operationAVenir) ; 
							break;
						}
				}      
				 
				// Réinitialisation du nombre à opérer à la prochaine opération
				valeurB = 0 ;  
			}
			
			// - - - - - - - - - - - - - - - BOUTON MULTIPLICATION  - - - - - - - - - - - - - - - - - -
			
			else if (e.getSource() == boutonMultiplication)
			{
				nombreInteger = false ;
				System.out.println("Somme provisoire avant opération précédente : " + somme) ;
				System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
				System.out.println("Nature de l'opération précédente : " + operationAVenir) ;
				valeurB = Float.valueOf(textBouton) ; 
				System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ; 
				
				
				operationEffectuee = operationAVenir ;
				JButton copieBoutonOperation = (JButton) e.getSource();
				operationAVenir = copieBoutonOperation.getText();
				 
				switch (operationEffectuee)
				{
					case "+" : 
						System.out.println("Nouveau produit qui sera à additionner à la somme provisoire.") ;
						valeurC = valeurB ; 
						operationFinProduit = operationEffectuee ;
						operationEffectuee = "" ;
						sousSomme = valeurC ;
						operationAVenir = "*" ; 
						break ;
						
					case "-" :  
						System.out.println("Nouveau produit qui sera à soustraire à la somme provisoire.") ;
						valeurC = valeurB ; 
						operationFinProduit = operationEffectuee ;
						operationEffectuee = "" ;
						sousSomme = valeurC ;
						operationAVenir = "*" ;  
						break ;
					case "*" :  
							valeurC = valeurB ;
							operationEffectuee = operationAVenir ; 
							sousSomme = sousSomme * valeurB ;   
							break ;   
					case "/" :	
						if (valeurB == 0)
						{
							calculArea.setText("Erreur") ;
							somme = 0 ;
							break ; 
						}
						else 
						{
							operationEffectuee = operationAVenir ; 
							sousSomme = sousSomme / valeurB ;    
							break;
						} 
				} 
				
				System.out.println(" ") ;
				System.out.println("OPERATION A VENIR") ; 
				System.out.println("Somme provisoire après l'opération précédente : " + somme) ; 
				System.out.println("Produit provisoire après l'opération précédente : " + sousSomme) ;
				System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
				
				// Réinitialisation du nombre à opérer à la prochaine opération
				valeurB = 0 ;	 
			}  
			
			// - - - - - - - - - - - - - - - BOUTON DIVISION - - - - - - - - - - - - - - - - - -
			
			else if (e.getSource() == boutonDivision)
			{
				nombreInteger = false ;
				System.out.println("Somme provisoire avant opération précédente : " + somme) ;
				System.out.println("Produit provisoire avant opération précédente : " + sousSomme) ;
				System.out.println("Nature de l'opération précédente : " + operationAVenir) ;
				valeurB = Float.valueOf(textBouton) ; 
				System.out.println("Nombre à opérer pour la précédente opération : " + valeurB) ;
				
				// Priorité des opérations 
				operationEffectuee = operationAVenir ;
				JButton copieBoutonOperation = (JButton) e.getSource();
				operationAVenir = copieBoutonOperation.getText();
				
				switch (operationEffectuee)
				{
					case "+" :
						System.out.println("Nouveau produit qui sera à additionner à la somme provisoire.") ;
						valeurC = valeurB ; 
						operationFinProduit = operationEffectuee ; 
						sousSomme = valeurC ;
						operationAVenir = "/" ;
						break ;
					case "-" : 
						operationFinProduit = operationEffectuee ; 
						System.out.println("Nouveau produit qui sera à soustraire à la somme provisoire.") ;
						valeurC = valeurB ;  
						sousSomme = valeurC ;
						operationAVenir = "/" ;
						break ;
					case "*" : 
						if (valeurB == 0)
						{
							calculArea.setText("Erreur") ;
							somme = 0 ;
							break ; 
						}
						else 
						{
							System.out.println("Test2") ;
							operationEffectuee = operationAVenir ; 
							sousSomme = sousSomme * valeurB ;       
							JButton copyBoutonOperation = (JButton) e.getSource();
							operationAVenir = copyBoutonOperation.getText();
							valeurC = sousSomme ; 
							break ; 
						} 
					case "/" :	 
						valeurC = valeurB ; 
						operationEffectuee = operationAVenir ; 
						sousSomme = sousSomme / valeurB ;        
						JButton copiBoutonOperation = (JButton) e.getSource();
						operationAVenir = copiBoutonOperation.getText(); 
						break ; 
				}		 
					 
				System.out.println(" ") ;
				System.out.println("OPERATION A VENIR") ; 
				System.out.println("Somme provisoire avant l'opération à venir : " + somme) ; 
				System.out.println("Produit provisoire avant l'opération à venir : " + sousSomme) ;
				System.out.println("Nature de l'opération à venir : " + operationAVenir) ;
				
				// Réinitialisation du nombre à opérer à la prochaine opération
				valeurB = 0 ;	 
			}  
			
			// - - - - - - - - ADDITION, SOUSTRACTION, MULTIPLICATION, DIVISION  - - - - - - - - - - - - - -
			
			if (compteurOperation <= 1) 
			{
				calculArea.setText(textBouton);
			}
			else
			{ 
				if (nombreInteger == true) 
				{ 
					calculArea.setText(String.valueOf((int)somme));
					
				}
				else 
				{ 
					DecimalFormat nf = new DecimalFormat("0.##") ;
					String sommeNF = nf.format(somme) ;
					calculArea.setText(sommeNF) ;
				} 
			}
	 
			System.out.println(" ") ; 
			System.out.println("- - - - -") ;
			
			textBouton = "" ;  
			 
		}
	} 

/* - - - - - - - - - - - -- - - - FIN DU PROGRAMME - - - - - - - - - - - - - - - - - - - - - - - */

}








