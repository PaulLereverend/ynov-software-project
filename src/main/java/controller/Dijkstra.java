package controller;

import java.awt.Color;
import java.util.ArrayList;

import entities.Obstacles;
import model.Case;
import model.Plateau;
import view.AffichagePlateau;


public class Dijkstra {
	private boolean termine = false;
	private Plateau grille;
	private AffichagePlateau view;
	
	
	
	public Dijkstra(Plateau grille, AffichagePlateau view) {
		super();
		this.grille = grille;
		this.view = view;
	}
	public void start() {
		ArrayList<Case> priority = new ArrayList<Case>();
		
		priority.add(grille.getCaseDepart());	
		while(!termine) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(priority.size() <= 0) {	
				this.termine = true;
				break;
			}
			int hops = priority.get(0).getDistance()+1;	
			ArrayList<Case> explored = this.explorerVoisins(priority.get(0), hops);
			if(explored.size() > 0) {
				priority.remove(0);	
				priority.addAll(explored);
				//Update();
			} else {
				priority.remove(0);
			}
		}
	}
	public ArrayList<Case> explorerVoisins(Case c, int distance){
		ArrayList<Case> explored = new ArrayList<Case>();
		ArrayList<Case> voisins = this.grille.getCasesArround(c);
		Case lastCase = c;
		for (Case caz : voisins) {
			if(caz.getObstacle() == null && !caz.isExplored()) {
				this.explorer(caz, lastCase, distance);
				explored.add(caz);
			}
			lastCase = caz;
		}
		return explored;
	}
	public void explorer(Case c, Case lastCase, int distance) {
		System.out.println("explorer : "+ c.getLigne()+ " / "+ c.getColonne());
		c.setExplored(true);
		this.view.colorierCase(c, Color.blue);
		c.setLastCase(lastCase);
		c.setDistance(distance);
		if(c.getObstacle() != null && c.getObstacle().getType() == Obstacles.ARRIVEE) {
			termine = true;
			this.afficherChemin(c);
		}
	}
	public void afficherChemin(Case c) {
		while(c.getObstacle() != null && c.getObstacle().getType() != Obstacles.DEPART) {
			this.view.colorierCase(c, Color.yellow);
			c = c.getLastCase();
		}
	}
}
