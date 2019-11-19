package model;

import javafx.collections.ObservableList;
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
		for(Node node : gridPane.getChildren()){
	        if (GridPane.getRowIndex(node) == null) {
	        	continue ; //ignore Group 
	        }
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
	        	return node;
	        }
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