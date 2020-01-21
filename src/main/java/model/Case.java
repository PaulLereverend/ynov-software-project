package model;

import entities.Obstacle;
import view.AffichagePlateau;

public class Case {
	
	private int ligne;
	private int colonne;
	private Obstacle obstacle;
	private boolean explored;
	private int distance;
	
	public Case(int row, int col, Obstacle type_obtacle) {
		this.ligne = row;
		this.colonne = col;
		this.setObstacle(type_obtacle);
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}
	public boolean isExplored() {
		return explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDistance() {
		return this.distance;
	}

	public double getEuclidDist() {		//CALCULATES THE EUCLIDIAN DISTANCE TO THE FINISH NODE
		int xdif = Math.abs(ligne-AffichagePlateau.plateau.getCaseArrivee().getLigne());
		int ydif = Math.abs(colonne-AffichagePlateau.plateau.getCaseArrivee().getColonne());
		double dToEnd = Math.sqrt((xdif*xdif)+(ydif*ydif));
		return dToEnd;
	}
	

}
