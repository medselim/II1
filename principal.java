package Automate;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



public class principal {

	public static void main(String[] args) {
		Automate a=new Automate();
		ChoixSaisie(a);
		a.AfficherGraph("Automate à l'état initial");
		//a.saisieFich("donneesaut.txt");
		ChoixTransformation(a);
		//System.out.println(a.getQ0());
		ChoixAffichage(a); 
	}
	
	public static void AffichageTab(Automate a){
		//Cette fonction n'est pas une méthode de la classe automate puisqu'on 
		//ne peut afficher sous forme d'un tableau qu'un automate deterministe
		String Q=new String();
		Q=a.getQ().toString().substring(1,a.getQ().toString().length()-1);  
		//System.out.println(Q);
	    String F=new String();
	    F=a.getF().toString().substring(1,a.getF().toString().length()-1);
	    //System.out.println(F);
	    String Alpha=new String();
	    Alpha=a.getAlphabet().toString().substring(1,a.getAlphabet().toString().length()-1);
	    //System.out.println(Alpha);
	    String donnes="La liste des états est: Q={"+Q+"}\n"+"L'alphabet est:{"+Alpha+"}\n"+"L'état initial est:"+a.getQ0()+"\nLa liste des états finaux est :F={"+F+"}";
	    ImageIcon img = new ImageIcon("/home/selim/Bureau/auto.png");
	    JOptionPane.showMessageDialog(null,donnes, "Les données du nouvel automate", JOptionPane.INFORMATION_MESSAGE,img);
	    tabAutomate t=new tabAutomate(a);
		t.setVisible(true);    
	}
	
	public static void ChoixSaisie(Automate a){
		  String[] tab = {"Interactivement", "A partir d'un fichier"};
		    int rang = JOptionPane.showOptionDialog(null,"Comment voulez-vous saisir votre automate ?","Saisie des Données de l'automate",
		    		JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,tab,tab[0]);
		  
		    switch(tab[rang]) 
		    {
		    	case "Interactivement":
		    		//les données saisies par la méthode interactive seront sauvegardées dans le fichier donneesaut2.txt
		    		a.saisieInterac("donneesaut2.txt");
		    		break;
		    	case "A partir d'un fichier":
		    		String FileR=new String();
		    		//saisie du nom du fichier
		    		FileR = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du fichier ", "Saisie du nom du fichier des données de l'automate", JOptionPane.QUESTION_MESSAGE);
		    		a.saisieFich(FileR);
		    		break;
		    }
	}
	public static void ChoixTransformation(Automate a){
		 String[] tab = {"DFA", "DFA minimal"};
		    int rang = JOptionPane.showOptionDialog(null,"Transformation en un :","Opération de Transformation",
		    		JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,tab,tab[0]);
		    
		    switch(tab[rang]) 
		    {
		    	case "DFA":
		    		a.determiniser();
		    		break;
		    	case "DFA minimal":
		    		a.determiniser();
		    		a.minimiser();
		    		break;
		    }
	}
	public static void ChoixAffichage(Automate a){
		String[] tab = {"Graphique", "Sous forme d'un tableau"};
	    int rang = JOptionPane.showOptionDialog(null,"Choisissez un mode d'affichage","Mode d'affichage",
	    		JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,tab,tab[0]);
	    
	    switch(tab[rang]) 
	    {
	    	case "Graphique":
	    		a.AfficherGraph("Automate à l'état final");
	    		break;
	    	case "Sous forme d'un tableau":
	    		AffichageTab(a);
	    		break;
	    		
	    }
	}
}
