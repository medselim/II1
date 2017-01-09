package Automate;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class tabAutomate extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public tabAutomate(Automate a){
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("La fonction de transition");
	    
	    this.setSize(500, 200);

	    //Les données du tableau
	    
	    String[][] data=new String[a.getQ().size()][a.getAlphabet().size()+1];
	    for (int i=0 ; i<a.getQ().size(); i++)
	        data[i]=new String[a.getAlphabet().size()+1];
	    for(int i=0;i<a.getQ().size();i++){
	    	for(int j=0;j<a.getAlphabet().size()+1;j++){
	    		if(j==0){
	    			data[i][j]=a.getQ().get(i);
	    		}
	    		else{
	    			data[i][j]=image(a.getQ().get(i),a.getAlphabet().get(j-1),a);
	    		}
	    	}
	    }
	    
	    
	    

	    //Les titres des colonnes
	    String[]  title=new String[a.getAlphabet().size()+1] ;
	    title[0]="Q-Alphabet";
	    for(int i=1;i<=a.getAlphabet().size();i++){
	    	title[i]=a.getAlphabet().get(i-1).toString();
	    }
	    JTable tableau = new JTable(data, title);
	    this.getContentPane().add(new JScrollPane(tableau));
	    this.setLocationRelativeTo(null);  
	    
	}
	private static String image(String ch,char c,Automate a){
		 String str=new String();
		 for(arc ar:a.getDelta()){
			if (ar.getIn().equals(ch)&& ar.getVal()==c){
				str=ar.getFin();
				return str;
			}
		}
		return "";
		
	}

}
