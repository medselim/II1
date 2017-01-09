package Automate;
import java.util.ArrayList;
public class arc2 {
	private ArrayList<String> in;//sommet initial de l'arc
	private char val;//valeur de l'arc
	private ArrayList<String> fin;//sommet final de l'arc
	public arc2(ArrayList<String> i,char v,ArrayList<String> f){
		in=i;
		val=v;
		fin=f;
	}
	public ArrayList<String> getIn() {
		return in;
	}
	public void setIn(ArrayList<String> in) {
		this.in = in;
	}
	public char getVal() {
		return val;
	}
	public void setVal(char val) {
		this.val = val;
	}
	public ArrayList<String> getFin() {
		return fin;
	}
	public void setFin(ArrayList<String> fin) {
		this.fin = fin;
	}
	

}
