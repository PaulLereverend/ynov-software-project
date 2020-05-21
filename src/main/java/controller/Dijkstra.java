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
	
	
	/**
	 * Constructeur de l'IA Dijkstra
	 * @param grille
	 * @param view
	 */
	public Dijkstra(Plateau grille, AffichagePlateau view) {
		super();
		this.grille = grille;
		this.view = view;
	}
	/**
	 * Lance la recherche de solutions de Dijkstra
	 */
	public void start() {
		ArrayList<Case> priority = new ArrayList<Case>();
		priority.add(this.grille.getCaseDepart());	
		ArrayList<Case> parcourues = new ArrayList<Case>();
		while(!termine) {
			if(priority.size() <= 0) {	
				this.termine = true;
				break;
			}
			int hops = priority.get(0).getDistance()+1;	
			ArrayList<Case> explored = this.explorerVoisins(priority.get(0), hops);
			parcourues.addAll(explored);
			if(explored.size() > 0) {
				priority.remove(0);	
				priority.addAll(explored);
				//Update();
			} else {
				priority.remove(0);
			}
		}
		this.afficherCouleurs(parcourues);
		
	}
	private void afficherCouleurs(ArrayList<Case> parcourues) {
		for (Case case1 : parcourues) {
			this.view.colorierCase(case1, case1.getColor());
		}
	}
	/**
	 * Recherche 
	 * @param c
	 * @param distance
	 * @return
	 */
	public ArrayList<Case> explorerVoisins(Case c, int distance){
		ArrayList<Case> explored = new ArrayList<Case>();
		ArrayList<Case> voisins = this.grille.getCasesArround(c);
		for (Case caz : voisins) {
			if(!caz.isExplored() && this.termine == false) {
				this.explorer(caz, c, distance);
				caz.setExplored(true);
				explored.add(caz);
			}
			//lastCase = caz;
		}
		return explored;
	}
	public void explorer(Case c, Case lastCase, int distance) {
		c.setColor(Color.blue);
		c.setLastCase(lastCase);
		c.setDistance(distance);
		if(c.getObstacle() != null && c.getObstacle().getType() == Obstacles.ARRIVEE) {
			System.out.println("terminé");
			termine = true;
			this.afficherChemin(c);
		}
	}
	public void afficherChemin(Case c) {
		while(c != this.grille.getCaseDepart()) {
			c.setColor(Color.yellow);
			c = c.getLastCase();
		}
	}
}
