package Automate;

import java.util.ArrayList;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

public class AutoGraph extends JFrame {
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AutoGraph(Automate a,String msg) {
		super(msg);
		
	    mxGraph graph = new mxGraph();
	    Object parent = graph.getDefaultParent();
	 
	    graph.getModel().beginUpdate();
	    try {
	     
	      ArrayList<arc> fonc=a.getDelta();
	      Object[] tab=new Object[a.getQ().size()];
	      for(int i=0;i<a.getQ().size();i++){
	    	  if(a.getF().contains(a.getQ().get(i)))
	    		  tab[i]=graph.insertVertex(parent, null,a.getQ().get(i),  Math.random()*600,Math.random()*400, 50,50,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_DOUBLE_ELLIPSE);
	    	  else if(a.getQ0().equals(a.getQ().get(i))){
	    		  tab[i]=graph.insertVertex(parent, null,a.getQ().get(i),  Math.random()*600,Math.random()*400, 50,50,mxConstants.STYLE_FILLCOLOR + "=#00ff00");
	    	  }
	    	  
	    	  else
	    		  tab[i]=graph.insertVertex(parent, null,a.getQ().get(i),  Math.random()*600,Math.random()*400, 50,50,mxConstants.STYLE_SHAPE + "="+mxConstants.SHAPE_ELLIPSE);
	      }
	      
	    	  for(arc ar:fonc){
	    		  graph.insertEdge(parent, null,ar.getVal(),tab[a.getQ().indexOf(ar.getIn())], tab[a.getQ().indexOf(ar.getFin())]);
	    	  }
	      
	      
	      
	    } finally {
	      graph.getModel().endUpdate();
	    }
	 
	    mxGraphComponent graphComponent = new mxGraphComponent(graph);
	    getContentPane().add(graphComponent);
	  }
}
