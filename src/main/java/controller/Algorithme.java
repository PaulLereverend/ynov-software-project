package controller;

import java.util.ArrayList;

import model.Case;

public interface Algorithme {

	public void start();
	int calculerScore(ArrayList<Case> parcourues);
}
