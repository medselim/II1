package Automate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFrame;




public class Automate {
	private ArrayList<String> Q=new ArrayList<String> (); //liste des états 
	private ArrayList<Character> Alphabet=new ArrayList<Character> ();   //liste de caractères
	private String q0;//état initial
	private ArrayList<String> F=new ArrayList<String> ();//liste des états finaux
    //la fonction de transition sera représentée par une liste d'arcs
	private ArrayList<arc> delta;
	public Automate(){
		
	}
	public Automate(ArrayList<String> Q1,ArrayList<Character> Alpha1,String q00,ArrayList<String> F1,ArrayList<arc> delta1){
		Q=Q1;
		Alphabet=Alpha1;
		q0=q00;
		F=F1;
		delta=delta1;
	}
	public ArrayList<String> getQ() {
		return Q;
	}
	public void setQ(ArrayList<String> q) {
		Q = q;
	}
	public ArrayList<Character> getAlphabet() {
		return Alphabet;
	}
	public void setAlphabet(ArrayList<Character> alphabet) {
		Alphabet = alphabet;
	}
	public String getQ0() {
		return q0;
	}
	public void setQ0(String q0) {
		this.q0 = q0;
	}
	public ArrayList<String> getF() {
		return F;
	}
	public void setF(ArrayList<String> f) {
		F = f;
	}
	public ArrayList<arc> getDelta() {
		return delta;
	}
	public void setDelta(ArrayList<arc> delta) {
		this.delta = delta;
	}
	
	public void determiniser(){
		 ArrayList<ArrayList<String>> Qn=new ArrayList<ArrayList<String>> (); //liste des états 
		 ArrayList<String> q0n=new ArrayList<String>();//état initial
		 ArrayList<ArrayList<String>> Fn=new ArrayList<ArrayList<String>> ();//liste des états finaux
	     ArrayList<arc2> deltan=new ArrayList<arc2>();
	     q0n.add(q0);
	     for(arc ar:delta){
	    	 if(ar.getIn().equals(q0)&&ar.getVal()=='E'&&!q0n.contains(ar.getVal()))
	    		 q0n.add(ar.getFin());
	     }
	     Qn.add(q0n);
	     //Iterator<ArrayList<String>> it=Qn.iterator();
	     int i=0;
	     int n=Qn.size();
	     while(i<n){
	    	 ArrayList<String> qn=Qn.get(i);
	    	 
	    	 for(char c:Alphabet){
	    		 ArrayList<String> qnn=new ArrayList<String>();
	    		 for(arc ar:delta){
	    			 if(qn.contains(ar.getIn())&&Q.contains(ar.getFin())&&ar.getVal()==c){
	    				 if(!qnn.contains(ar.getFin()))
	    				 {qnn.add(ar.getFin());}
	    			 }	 
	    		 }
	    		 if(qnn.size()!=0){
	    			 for(arc ar:delta){
	    				 if(qnn.contains(ar.getIn())&&ar.getVal()=='E'&&!qnn.contains(ar.getFin()))
	    					 qnn.add(ar.getFin());
	    			 }
	    		 }
	    		 arc2 a1=new arc2(qn,c,qnn);
	    		 if(!deltan.contains(a1))
	    		 {deltan.add(a1);}
	    		 if(!Qn.contains(qnn))
	    		 {Qn.add(qnn);n++;}
    			 
	    	 }
	    	 i++;
	     }
		//nouvelle liste d'états finaux
	     
	     for(ArrayList<String> qn:Qn){
	    	 
	    		 if(!InterVide(F,qn)){
	    			 Fn.add(qn);
	    		 }
	    	 
	     }
	    ArrayList<String> Qf=new ArrayList<String> (); //liste des états 
	 	ArrayList<String> Ff=new ArrayList<String> ();//liste des états finaux
	    ArrayList<arc> deltaf=new ArrayList<arc>(); //nouvelle fonction de transition
	    
	    for(ArrayList<String> l:Qn){
	    	Qf.add(l.toString());
	    }
	    Q=Qf;
	    for(ArrayList<String> l:Fn){
	    	Ff.add(l.toString());
	    }
		F=Ff;
		for(arc2 el:deltan){
			deltaf.add(new arc(el.getIn().toString(),el.getVal(),el.getFin().toString()));
			
		}
		delta=deltaf;
		q0=q0n.toString();
		
	}
	public void minimiser(){
		ArrayList<ArrayList<String>> P=new ArrayList<ArrayList<String>>();
		ArrayList<String> L1 = new ArrayList<String>() ;
		for(String str : Q)
		{
			if(!F.contains(str))
			{
				L1.add(str) ;
			}
		}
		ArrayList<String> L2 = new ArrayList<String>() ;
		L2 = F ;
		P.add(L1) ;	//L1 list des états non finaux 
		P.add(L2) ; //L2 liste des états finaux
		ArrayList<ArrayList<String>> Pn=new ArrayList<ArrayList<String>>();
		int n=2,j=0;
		do {j=0;//n est le nombre de groupes de P 
			Pn=P;
			for(char c:Alphabet){
				do {
					ArrayList<String> G=P.get(j);
					//remplacer G dans P par les nouveaux sous groupes 
					ArrayList<String> tab[]=new ArrayList[P.size()];
					for(int i=0;i<P.size();i++){
						tab[i]=new ArrayList<String>();
					}
					for(String et:G){
						for(int i=0;i<P.size();i++){
							if(P.get(i).contains(image(et,c))){
								tab[i].add(et);
							}
						}
					}
					int k=n;
					for(int i=0;i<k;i++){
						int p=0;
						if(tab[i].size()!=0) {
							P.add(j+p,tab[i]);
							p++;
							n++;
						}
					}
					P.remove(G);
					n--;
					j++;
				}while(j<n);
			}
			
		}while(P!=Pn);
		
		ArrayList<String> Fmin=new ArrayList<String> ();	
		for(ArrayList<String> list : P)
		{
			for(String str : list)
			{
				if (F.contains(str))
					Fmin.add(list.toString()) ;
				
			}
		}
		F=Fmin ;
		
		
	//preparation de la nouvelle delta
		ArrayList<arc> deltamin = new ArrayList<arc>() ;
		for(ArrayList<String> list : P)
		{
			for(char c : Alphabet)
			{
				for(ArrayList<String> list2 : P)
				{
					if(list2.contains(image(list.get(0),c)))
					{
						arc ar = new arc(list.toString(),c,list2.toString()) ;
						deltamin.add(ar) ;
					}
				}
			}
		}
		delta=deltamin ;
		
		//preparation de la nouvelle liste d'états
		ArrayList<String> Qmin =new ArrayList<String>() ;
		for(ArrayList<String> list : P)
		{
			Qmin.add(list.toString() );
			
		}
		Q=Qmin ;
		
		q0=Q.get(0);
		
		
	};
	public  void saisieFich(String str){
		ArrayList<String> Q1=new ArrayList<String>();
		ArrayList<String> F1=new ArrayList<String>();
		ArrayList<Character> Alpha1=new ArrayList<Character>();
		String q00=new String();
		ArrayList<arc> delta1=new ArrayList<arc>();
		String nomfich=str;
		String line=null;
		BufferedReader buf= null;
		try {
			FileReader fr=new FileReader(nomfich);
			buf=new BufferedReader(fr);
			while((line=buf.readLine())!=null){//System.out.println(line);
				String ch=new String();
				String[] tab;
				switch(line.charAt(0)){
				case 'Q':
					ch=line.substring(3,line.length()-1);
					tab=ch.split(",");
					
					for(String s:tab){
						Q1.add(s);
						//System.out.println(s);
					}
					break;
				case 'F':
					 ch=line.substring(3,line.length()-1);
					 tab=ch.split(",");
					
					for(String s:tab){
						F1.add(s);
						//System.out.println(s);
					}
					break;
				case 'A':
					ch=line.substring(10,line.length()-1);
					 tab=ch.split(",");
					
					for(String s:tab){
						Alpha1.add(s.charAt(0));
						//System.out.println(s);
					}
					break;
				case 'e':
					 q00=line.substring(13);
					 //System.out.println(q00);
					 break;
				case 'd':
					
					ch=line.substring(7, line.length()-1);
					String[] tab2;
					tab2=ch.split(";");
					for(String s:tab2){
						//System.out.println(s);
						String ch3=s.substring(1,s.length()-1);
						String[] tab3;
						tab3=ch3.split(",");
						delta1.add(new arc(tab3[0],tab3[1].charAt(0),tab3[2]));
						
					}
					break;
				default:
					System.out.println("verifier la synthaxe de votre fichier");
				}
			}
		} catch (FileNotFoundException ex){
			System.out.println("impossible d'ouvrir le fichier '"+nomfich+"'");
			
		} catch(IOException ex){
			System.out.println("erreur dans la lecture du fichier '"+nomfich+"'");
		} finally {
			try {
				if(buf!=null){
					buf.close();
				}
			} catch (IOException e){
				
			}
			Q=Q1;
			F=F1;
			Alphabet=Alpha1;
			delta=delta1;
			q0=q00;	
		}
		
		
		
		}
	public void saisieInterac(String FileR){
		BoiteSaisie b=new BoiteSaisie();
		b.initBoite(FileR);
		this.saisieFich(FileR);
	}
	public void AfficherGraph(String message){
		AutoGraph ag = new AutoGraph(this,message);
	    ag.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    ag.setSize(700, 500);
	    ag.setVisible(true);
	}
	
	
private static Boolean InterVide(ArrayList<String> l1,ArrayList<String> l2){
		ArrayList<String> l3=new ArrayList<String>();
		for(String a:l1){
			if(l2.contains(a)){
				l3.add(a);
			}
			
		}
		if(l3.isEmpty()) return true;
		else return false;
	}
private  void remplacer (ArrayList<String> G,ArrayList<ArrayList<String>> P,char c,int n){
	ArrayList<String> tab[]=new ArrayList[P.size()];
	for(int i=0;i<P.size();i++){
		tab[i]=new ArrayList<String>();
	}
	for(String et:G){
		for(int i=0;i<P.size();i++){
			if(P.get(i).contains(image(et,c))){
				tab[i].add(et);
			}
		}
	}
	for(int i=0;i<P.size();i++){
		if(tab[i].size()!=0) {
			P.add(0,tab[i]);
			n++;
		}
	}
	P.remove(G);
}
private  String image(String et,char c){
	for(arc ar:delta){
		if (ar.getIn().equals(et)&&ar.getVal()==c){
			return ar.getFin();
		}
		
	}
	return "";
}
	
}
