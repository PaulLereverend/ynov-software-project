package model;

import entities.Obstacle;

public class Case {
	
	private int ligne;
	private int colonne;
	private Obstacle obstacle;
	
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

}
