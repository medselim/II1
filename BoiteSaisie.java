package Automate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.io.*;
public class BoiteSaisie extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField Q, Alpha,q0,F,Delta;
	 private JLabel QLabel,AlphaLabel,q0Label,FLabel,DeltaLabel;
	
	public BoiteSaisie(){
	    super(new JFrame(),"Saisir les données de votre automate",true);
	  }
	public void initBoite(String str){
	    
	    this.setSize(700, 450);
	    this.setLocationRelativeTo(null);
	    this.setResizable(true);
	    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		//Construction des boites de saisie de chaque donnée de l'automate
		Font police = new Font("Tahoma", Font.CENTER_BASELINE, 13);
		Font police2=new Font("Tahoma",Font.BOLD,15);
		//La liste des états
	    JPanel panQ = new JPanel();
	    panQ.setBackground(Color.lightGray);
	    panQ.setPreferredSize(new Dimension(300,120));
	    Q = new JTextField("{}");
	    Q.setPreferredSize(new Dimension(200,30));
	    Q.setFont(police);
	    panQ.setBorder(BorderFactory.createTitledBorder("Liste des états"));
	    QLabel = new JLabel("Q :");
	    QLabel.setFont(police2);
	    panQ.add(QLabel,BorderLayout.WEST);
	    panQ.add(Q,BorderLayout.EAST);
	    //L'Alphabet de l'automate
	    JPanel panAlpha = new JPanel();
	    panAlpha.setBackground(Color.lightGray);
	    panAlpha.setPreferredSize(new Dimension(300,120));
	    panAlpha.setBorder(BorderFactory.createTitledBorder("L'alphabet de l'automate"));
	    AlphaLabel = new JLabel("Alpha : ");
	    AlphaLabel.setFont(police2);
	    Alpha = new JTextField("{}");
	    Alpha.setPreferredSize(new Dimension(200,30));
	    Alpha.setFont(police);
	    panAlpha.add(AlphaLabel);
	    panAlpha.add(Alpha);
	    //L'état initial
	    
	    JPanel panq0 = new JPanel();
	    panq0.setBackground(Color.lightGray);
	    panq0.setPreferredSize(new Dimension(300,120));
	    panq0.setBorder(BorderFactory.createTitledBorder("L'état initial"));
	    q0Label = new JLabel("L'état initial: ");
	    q0Label.setFont(police2);
	    q0 = new JTextField();
	    q0.setPreferredSize(new Dimension(100,30));
	    q0.setFont(police);
	    panq0.add(q0Label);
	    panq0.add(q0);
	    
	    //la liste des états finaux
	    JPanel panF = new JPanel();
	    panF.setBackground(Color.lightGray);
	    panF.setPreferredSize(new Dimension(300,120));
	    F = new JTextField("{}");
	    F.setPreferredSize(new Dimension(200,30));
	    F.setFont(police);
	    panF.setBorder(BorderFactory.createTitledBorder("Liste des états finaux"));
	    FLabel = new JLabel("F :");
	    FLabel.setFont(police2);
	    panF.add(FLabel,BorderLayout.WEST);
	    panF.add(F,BorderLayout.EAST);
	    
	    //la fonction de transition
	    JPanel panDelta = new JPanel();
	    panDelta.setBackground(Color.lightGray);
	    panDelta.setPreferredSize(new Dimension(600,120));
	    panDelta.setBorder(BorderFactory.createTitledBorder("La fonction de transition"));
	    DeltaLabel = new JLabel("Delta : ");
	    DeltaLabel.setFont(police2);
	    Delta = new JTextField("{}");
	    Delta.setPreferredSize(new Dimension(350,30));
	    Delta.setFont(police);
	    panDelta.add(DeltaLabel);
	    panDelta.add(Delta);
	    
	    //ajouter toutes ces boites de saisie à fenêtre
	    JPanel content=new JPanel();
	    content.add(panQ);
	    content.add(panAlpha);
	    content.add(panq0);
	    content.add(panF);
	    content.add(panDelta);
	    JPanel control = new JPanel();
	    JButton okBouton = new JButton("OK");
	    okBouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0){
	    		
	    		
	    		
	    		BufferedWriter bufferedWriter = null;
	    		
	    		try {
	    			// Assume default encoding.
	    			FileWriter fileWriter = new FileWriter(str);
	    			// Always wrap FileWriter in BufferedWriter.
	    			bufferedWriter = new BufferedWriter(fileWriter);
	    			// Note that write() does not automatically
	    			// append a newline character.
	    			bufferedWriter.write("Q="+Q.getText());
	    			bufferedWriter.newLine();
	    			bufferedWriter.write("Alphabet="+Alpha.getText());
	    			bufferedWriter.newLine();
	    			bufferedWriter.write("etat initial="+q0.getText());
	    			bufferedWriter.newLine();
	    			bufferedWriter.write("F="+F.getText());
	    			bufferedWriter.newLine();
	    			bufferedWriter.write("delta="+Delta.getText());
	    		} catch (IOException ex) {
	    			
	    			System.out.println("Erreur d'écriture dans le fichier '" + str + "'");
	    			
	    		}finally {
	    			
	    			try {
	    				if (bufferedWriter != null) {
	    					bufferedWriter.close();
	    				}
	    			} catch (IOException e) {
	    			}
	    		}
	    		//System.out.println(Q.getText());
	    		//System.out.println(Alpha.getText());
	    		setVisible(false);
	    	}
	    });
	    JButton cancelBouton = new JButton("Annuler");
	    cancelBouton.addActionListener(new ActionListener(){
	      public void actionPerformed(ActionEvent arg0) {
	        setVisible(false);
	        
	      }      
	    });

	    control.add(okBouton);
	    control.add(cancelBouton);
	    this.getContentPane().add(content, BorderLayout.CENTER);
	    this.getContentPane().add(control, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	
	

}
