package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Plateau {
	
	private int NB_CASE = 20;
	
	private GridPane gridPane = new GridPane();
	
	public Plateau() {
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setGridLinesVisible(true);
		
		//lignes
		for (int i = 0; i < NB_CASE; i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
		}
		
		//colonnes
		for (int j = 0; j < NB_CASE; j++) { 
			ColumnConstraints col = new ColumnConstraints(30); 
			gridPane.getColumnConstraints().add(col); 
		}
	}
	
	public Node getPane(int row, int col) {
		System.out.println(row);
		System.out.println(col);
		for (javafx.scene.Node node : gridPane.getChildren()) {
			System.out.println(node);
	        /*if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
	            return node;
	        }*/
	    }
		return null;
	}

	public GridPane getGridPane() {
		return gridPane;
	}
	
	public int getNbCases() {
		return NB_CASE;
	}

}