package entities;

public enum Obstacles {
	DEPART ("depart"),
	ARRIVEE ("arrivee"),
	BOUE ("boue"),
	MUR ("mur");
	
	private String name = "";
	
	//Constructeur
	Obstacles(String name){
		this.name = name;
	}
	
	public String toString(){
		return name;
	}
}
