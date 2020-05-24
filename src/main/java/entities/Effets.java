package entities;

public enum Effets {
	  BLOQUANT ("bloquant"),
	  PASSANT ("passant"),
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
