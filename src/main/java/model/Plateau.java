package model;

import java.util.ArrayList;
import entities.Effets;
import entities.Obstacle;
import entities.Obstacles;


public class Plateau {
	
	private int NB_CASES = 19;
	public Case[][] casesTab = new Case[NB_CASES][NB_CASES];
	private static volatile Plateau plateau = null;
	
	private Plateau() {
		for (int i = 0; i < casesTab.length; i++) {
			for (int j = 0; j < casesTab.length; j++) {
				casesTab[i][j] = new Case(i, j, null);
			}
		}
	}
	
	public final static Plateau getPlateau() {
        if (Plateau.plateau == null) {
           synchronized(Plateau.class) {
             if (Plateau.plateau == null) {
            	 Plateau.plateau = new Plateau();
             }
           }
        }
        return Plateau.plateau;
    }
	
	public int getNbCases() {
		return NB_CASES;
	}
	public int size() {
		return NB_CASES*NB_CASES;
	}
	
	public Case getCase(int row, int col) {
		return casesTab[row][col];
	}
	
	public ArrayList<Case> getCasesArround(Case c) {
		int row = c.getLigne(); 
		int col = c.getColonne();
		ArrayList<Case> listVoisins = new ArrayList<Case>(); 
		if(row+1 < casesTab.length) {
			listVoisins.add(casesTab[row+1][col]);
			//System.out.println((row+1) + " " + col);
			if (col+1 < casesTab.length) {
				listVoisins.add(casesTab[row+1][col+1]);
				//System.out.println((row+1) + " " + (col+1));
			}
			if (col-1 >= 0) {
				listVoisins.add(casesTab[row+1][col-1]);
				//System.out.println((row+1) + " " + (col-1));
			}
		}
		
		if(row-1 >= 0) {
			listVoisins.add(casesTab[row-1][col]);
			//System.out.println((row-1) + " " + (col));
			if (col+1 < casesTab.length) {
				listVoisins.add(casesTab[row-1][col+1]);
				//System.out.println((row-1) + " " + (col+1));
			}
			if (col-1 >= 0) {
				listVoisins.add(casesTab[row-1][col-1]);
				//System.out.println((row-1) + " " + (col-1));
			}
		}
		
		if (col+1 < casesTab.length) {
			listVoisins.add(casesTab[row][col+1]);
			//System.out.println((row) + " " + (col+1));
		}
		if (col-1 >= 0) {
			listVoisins.add(casesTab[row][col-1]);
			//System.out.println((row) + " " + (col-1));
		}
		
		return listVoisins;
	}
	
	public void setCaseDepart(int row, int col) {
		getCase(row, col).setObstacle(new Obstacle(Effets.PASSANT, "depart", null, 1, 1, Obstacles.DEPART));
		getCase(row, col).setDistance(0);
	}
	
	public void setCaseArrivee(int row, int col) {
		getCase(row, col).setObstacle(new Obstacle(Effets.PASSANT, "arrivee", null, 1, 1, Obstacles.ARRIVEE));
	}
	
	public void setCaseBoue(int row, int col) {
		getCase(row, col).setObstacle(new Obstacle(Effets.RALENTIS, "boue", null, 1, 1, Obstacles.BOUE));
	}
	
	public void setCaseMur(int row, int col) {
		getCase(row, col).setObstacle(new Obstacle(Effets.BLOQUANT, "mur", null, 1, 1, Obstacles.MUR));
	}
	
	public Case getCaseDepart() {
		return getCaseObstacle(Obstacles.DEPART);
	}
	
	public Case getCaseArrivee() {
		return getCaseObstacle(Obstacles.ARRIVEE);
	}
	
	public Case getCaseObstacle(Obstacles type) {
		for (Case[] cases : casesTab) {
			for (Case c : cases) {
				if (c.getObstacle() != null && c.getObstacle().getType() == type) {
					return c;
				}
			}
		}
		return null;
	}
}