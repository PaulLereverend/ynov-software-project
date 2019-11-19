package view;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Plateau;

public class Interface {
	
	private GridPane gridPane = new GridPane();
	
	private Plateau plateau = new Plateau();
	
	public Interface(Stage primaryStage) {
		super();
		
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setGridLinesVisible(true);
		
		//lignes
		for (int i = 0; i < plateau.getNbCases(); i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
		}
		
		//colonnes
		for (int j = 0; j < plateau.getNbCases(); j++) { 
			ColumnConstraints col = new ColumnConstraints(30); 
			gridPane.getColumnConstraints().add(col); 
		}
		
		for (int i = 0 ; i < plateau.getNbCases() ; i++) {
	        for (int j = 0; j < plateau.getNbCases(); j++) {
	        	initCase(i, j);
	        }
	    }
		
		Scene scene = new Scene(gridPane);

		primaryStage.setTitle("Hello World");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
		
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
	
	public ArrayList<Node> getNeighbour(int row, int col) {
		ArrayList<Node> listVoisins = null;
		for(Node node : gridPane.getChildren()){
	        if (GridPane.getRowIndex(node) == null) {
	        	continue ; //ignore Group 
	        }
	        if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
	        	listVoisins.add(node);
	        }
	    }
	    return listVoisins;
	}

	public GridPane getGridPane() {
		return gridPane;
	}
	
	private void initCase(final int colIndex, final int rowIndex) {
		
		final Pane pane = new Pane();

        if (colIndex == 5 && rowIndex == 10) {

        	final Image point_depart_img = new Image("file:src/main/resources/cross.png");
        	final ImageView point_depart = new ImageView(point_depart_img);
        	point_depart.setFitWidth(30);
        	point_depart.setFitHeight(30);
        	pane.getChildren().add(point_depart);
        	
        	pane.setOnDragDetected(new EventHandler <MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");
                    
                    /* allow any transfer mode */
                    Dragboard db = point_depart.startDragAndDrop(TransferMode.ANY);
                    
                    /* put a string on dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putString("cross");
                    db.setContent(content);
                    
                    event.consume();
                }
            });
        	
        	pane.setOnDragDone(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == TransferMode.MOVE) {
                    	//point_depart.setText("");
                    }
                    
                    event.consume();
                }
            });
		} else if(colIndex == 10 && rowIndex == 10){
			
		  Image point_arrivee_img = new Image("file:src/main/resources/drapeau.png");
		  ImageView point_arrivee = new ImageView(point_arrivee_img);
		  point_arrivee.setFitWidth(30);
		  point_arrivee.setFitHeight(30);
		  pane.getChildren().add(point_arrivee);
		  
		} else {
			pane.setOnDragEntered(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                if (event.getGestureSource() != pane &&
	                        event.getDragboard().hasString()) {
	                	pane.setStyle("-fx-background-color:green;");
	                }
	                
	                event.consume();
	            }
	        });

	        pane.setOnDragExited(new EventHandler <DragEvent>() {
	            public void handle(DragEvent event) {
	                /* mouse moved away, remove the graphical cues */
	            	pane.setStyle("-fx-background-color:transparent;");
	                
	                event.consume();
	            }
	        });
		}
        
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent t) {
            	pane.setStyle("-fx-background-color:#dae7f3;");
                
            }
        });

    	pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	pane.setStyle("-fx-background-color:transparent;");
            }
        });
    	
    	gridPane.add(pane, colIndex, rowIndex);
    }
	
	public Node getCaseDepart() {
		return null;
	}
	
	public Node getCaseArrivee() {
		return null;
	}


}
