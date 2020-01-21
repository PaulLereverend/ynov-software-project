package entities;

public enum Effets {
	  BLOQUANT ("depart"),
	  PASSANT ("arrivee"),
	  RALENTIS ("ralentis");
	  private String name = "";
	   
	  //Constructeur
	  Effets(String name){
	    this.name = name;
	  }
	   
	  public String toString(){
	    return name;
	  }
}
