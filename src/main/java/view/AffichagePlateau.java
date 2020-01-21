package view;

import java.awt.Color;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.Case;
import model.Plateau;

public class AffichagePlateau {
	
	private GridPane gridPane = new GridPane();
	
	private GridPane gridPaneObstacle = new GridPane();
	
	public static Plateau plateau = new Plateau();
	//TODO set case bleu, blanc (pour passage du bot)
	public AffichagePlateau(Stage primaryStage, boolean isDisplay) {
		super();
		
		HBox root = new HBox();
	    root.setPadding(new Insets(20, 20, 20, 20));
		
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setAlignment(Pos.CENTER);
		gridPaneObstacle.setAlignment(Pos.CENTER);
		gridPane.setGridLinesVisible(true);
		
		//lignes
		for (int i = 0; i < plateau.getNbCases(); i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
			gridPaneObstacle.getRowConstraints().add(row);
		}
		
		//colonnes
		for (int j = 0; j < plateau.getNbCases(); j++) { 
			ColumnConstraints col = new ColumnConstraints(30); 
			gridPane.getColumnConstraints().add(col);  
		}
		
		for (Case[] cases : plateau.casesTab) {
			for (Case c : cases) {
				initCase(c);
			}
		}
		
		/*
		 * Création du menu des obstacles 
		 */
		if (!isDisplay) {
			ArrayList<String> pathImg = new ArrayList<String>();
			pathImg.add("file:src/main/resources/croix.png");
			pathImg.add("file:src/main/resources/drapeau.png");
			pathImg.add("file:src/main/resources/boue.png");
			pathImg.add("file:src/main/resources/mur.png");
			initIcon(pathImg);
		}
		
		clorierCase(new Case(0,0, null), Color.BLUE);
		
		root.getChildren().addAll(gridPane, gridPaneObstacle);
		
		Scene scene = new Scene(root);

		primaryStage.setTitle("Labyrinthe");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public GridPane getGridPane() {
		return gridPane;
	}
	
	private void initCase(Case c) {
		
		final int colIndex = c.getColonne();
		final int rowIndex = c.getLigne();
		
		final Pane pane = new Pane();
		
		//au survole en mode drag
		pane.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                pane.setStyle("-fx-background-color:green;");
                
                event.consume();
            }
        });
		
		//quand on ne survole plus la case en mode drag
        pane.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
            	pane.setStyle("-fx-background-color:transparent;");
                
                event.consume();
            }
        });
        
        //autorise le drag and drop
        pane.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes( TransferMode.COPY_OR_MOVE );
                }
                event.consume();
            }
        });
        
        //une fois que le pacquet est drop
  		pane.setOnDragDropped(new EventHandler <DragEvent>() {
  			public void handle(DragEvent event) {
  				System.out.println("setOnDragDropped");
				System.out.println(GridPane.getRowIndex(pane));
				System.out.println(GridPane.getColumnIndex(pane));
				event.consume();
			}
  		});
        
        //au survole de la souris on coloris la case
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent t) {
            	pane.setStyle("-fx-background-color:#dae7f3;");
            }
        });
        
        //quand la case n'est plus survolée, on rend la case transparente
    	pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	pane.setStyle("-fx-background-color:transparent;");
            }
        });
    	
    	gridPane.add(pane, colIndex, rowIndex);
    }
	
	public void initIcon(ArrayList<String> pathImg) {
		int i = 1;
		for (String path : pathImg) {
			Pane pane = new Pane();
			Image image = new Image(path);
	    	ImageView image_view = new ImageView(image);
	    	image_view.setFitWidth(30);
	    	image_view.setFitHeight(30);
	    	pane.getChildren().add(image_view);
	    	
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
	    	
	    	//au debut du drag
        	pane.setOnDragDetected(new EventHandler <MouseEvent>() {
                public void handle(MouseEvent event) {
                    System.out.println("onDragDetected");
                    
                    Dragboard db = image_view.startDragAndDrop(TransferMode.ANY);
                    
                    ClipboardContent content = new ClipboardContent();
                    content.putString("cross");
                    db.setContent(content);
                    
                    event.consume();
                }
            });
        	
        	//une fois le transfert effectué
        	pane.setOnDragDone(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    System.out.println("onDragDone");
                    System.out.println(event.toString());
                    if (event.getTransferMode() == TransferMode.MOVE) {
    	            	pane.setStyle("-fx-opacity: 1.0 ;");
                    }
                    
                    event.consume();
                }
            });
	    	
			gridPaneObstacle.add(pane, 0, i);
			i = i+2;
		}
		
	}
	
	public void clorierCase(Case c, Color couleur) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
				if (GridPane.getColumnIndex(node) == c.getColonne() && GridPane.getRowIndex(node) == c.getLigne()) {
		            node.setStyle("-fx-background-color:#"+Integer.toHexString(couleur.getRGB()).substring(2)+";");
		        }
			}
	    }
	}


}
