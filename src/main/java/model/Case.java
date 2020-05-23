package model;

import java.io.Serializable;
import java.awt.Color;
import entities.Obstacle;
import javafx.scene.Node;
import view.AffichagePlateau;

public class Case implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ligne;
	private int colonne;
	private Obstacle obstacle;
	private boolean explored;
	private int distance;
	private Case lastCase;
	private Color color;
	private double distance_arrivee;
	
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getEuclidDist(Case arrivee) {		//CALCULATES THE EUCLIDIAN DISTANCE TO THE FINISH NODE
		int xdif = Math.abs(this.getColonne()-arrivee.getColonne());
		int ydif = Math.abs(this.getLigne()-arrivee.getLigne());
		this.distance_arrivee = Math.sqrt((xdif*xdif)+(ydif*ydif));
		return this.distance_arrivee;
	}
	
	@Override
	public String toString() {
		return "Case [ligne=" + ligne + ", colonne=" + colonne + "]";
	}
	
}
