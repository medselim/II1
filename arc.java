package Automate;

public class arc {
	private String in;//sommet initial de l'arc
	private char val;//valeur de l'arc
	private String fin;//sommet final de l'arc
	public arc(String i,char v,String f){
		in=i;
		val=v;
		fin=f;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public char getVal() {
		return val;
	}
	public void setVal(char val) {
		this.val = val;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	

}
