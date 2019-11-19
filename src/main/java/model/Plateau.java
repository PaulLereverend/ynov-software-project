package model;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class Plateau {
	
	private int NB_CASES = 19;
	private Case[][] casesTab = new Case[NB_CASES][NB_CASES];
	
	public Plateau() {
		for (int i = 0; i < casesTab.length; i++) {
			for (int j = 0; j < casesTab.length; j++) {
				casesTab[i][j] = new Case(i, j, null);
			}
		}
	}
	
	public int getNbCases() {
		return NB_CASES;
	}
	
	public Case getCase(int row, int col) {
		return casesTab[row][col];
	}
	
	public ArrayList<Case> getCasesArround(int row, int col) {
		ArrayList<Case> listVoisins = new ArrayList<Case>(); 
		if(row+1 < casesTab.length) {
			listVoisins.add(casesTab[row+1][col]);
			System.out.println((row+1) + " " + col);
			if (col+1 < casesTab.length) {
				listVoisins.add(casesTab[row+1][col+1]);
				System.out.println((row+1) + " " + (col+1));
			}
			if (col-1 >= 0) {
				listVoisins.add(casesTab[row+1][col-1]);
				System.out.println((row+1) + " " + (col-1));
			}
		}
		
		if(row-1 >= 0) {//todo erreur ici
			listVoisins.add(casesTab[row-1][col]);
			System.out.println((row-1) + " " + (col));
			if (col+1 < casesTab.length) {
				listVoisins.add(casesTab[row-1][col+1]);
				System.out.println((row-1) + " " + (col+1));
			}
			if (col-1 >= 0) {
				listVoisins.add(casesTab[row-1][col-1]);
				System.out.println((row-1) + " " + (col-1));
			}
		}
		
		if (col+1 < casesTab.length) {
			listVoisins.add(casesTab[row][col+1]);
			System.out.println((row) + " " + (col+1));
		}
		if (col-1 >= 0) {
			listVoisins.add(casesTab[row][col-1]);
			System.out.println((row) + " " + (col-1));
		}
		
		return listVoisins;
	}
	
	public Case getCaseDepart() {
		return null;
	}
	
	public Case getCaseArrivee() {
		return null;
	}

}