package model;

import entities.Obstacle;
import javafx.scene.Node;
import view.AffichagePlateau;

public class Case {
	
	private int ligne;
	private int colonne;
	private Obstacle obstacle;
	private boolean explored;
	private int distance;
	private Case lastCase;
	
	public Case(int row, int col, Obstacle obtacle) {
		this.ligne = row;
		this.colonne = col;
		this.setObstacle(obtacle);
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

	public Case getLastCase() {
		return lastCase;
	}

	public void setLastCase(Case lastCase) {
		this.lastCase = lastCase;
	}
}
