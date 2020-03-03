package view;

import java.awt.Color;
import java.util.ArrayList;

import entities.Obstacles;
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
import model.ObstacleIcon;
import model.Plateau;

public class AffichagePlateau {
	
	private GridPane gridPane = new GridPane();
	
	private GridPane gridPaneObstacle = new GridPane();
	
	private Plateau plateau = new Plateau();
	
	//click sur icone
	private boolean putObstacle = false;
	//seulement une seule pose (depart/arrivee)
	private ObstacleIcon obstIconSelected = null;
	//depart/arrivee déjà posé sur le plateau
	private boolean startExist = false;
	private boolean endExist = false;
	
	private boolean isClear = false;
	
	public AffichagePlateau(Stage primaryStage, boolean hasNotEditPanel) {
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
		if (!hasNotEditPanel) {
			ArrayList<ObstacleIcon> pathImg = new ArrayList<>();
			ObstacleIcon o = new ObstacleIcon(new Image("file:src/main/resources/croix.png"), Obstacles.ARRIVEE);
			pathImg.add(o);
			o = new ObstacleIcon(new Image("file:src/main/resources/drapeau.png"), Obstacles.DEPART);
			pathImg.add(o);
			o = new ObstacleIcon(new Image("file:src/main/resources/boue.png"), Obstacles.BOUE);
			pathImg.add(o);
			o = new ObstacleIcon(new Image("file:src/main/resources/mur.png"), Obstacles.MUR);
			pathImg.add(o);
			initIcon(pathImg);
		}
		
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
    	
    	pane.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	//System.out.println(GridPane.getRowIndex(pane)+","+GridPane.getColumnIndex(pane));
            	if( putObstacle && obstIconSelected != null && canDisplayObstacle() && pane.getChildren().size() == 0 )
            	{
            		ImageView image_view = new ImageView(obstIconSelected.getImg());
  			    	image_view.setFitWidth(30);
  			    	image_view.setFitHeight(30);
  			    	image_view.setUserData(obstIconSelected.getTypeObstacle());
  			    	if (obstIconSelected.getTypeObstacle() == Obstacles.ARRIVEE) {
    					endExist = true;
    				}else if(obstIconSelected.getTypeObstacle() == Obstacles.DEPART) {
    					startExist = true;
    				}
  			    	pane.getChildren().add(image_view);
  			    	
            	}
            	else if(putObstacle && isClear) 
            	{
            		if (pane.getChildren().size() > 0) 
            		{
            			if (pane.getChildren().get(0).getUserData() != null) 
            			{
		            		if (pane.getChildren().get(0).getUserData().equals(Obstacles.ARRIVEE)) {
								endExist = false;
							}else if (pane.getChildren().get(0).getUserData().equals(Obstacles.DEPART)) {
								startExist = false;
							}
            			}
	            		pane.getChildren().clear();
            		}
            	}
            }
        });
    	
    	pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	if (putObstacle) {
					putObstacle = false;
				}else {
					putObstacle = true;
				}
            }
        });
    	
    	gridPane.add(pane, colIndex, rowIndex);
    }
	
	boolean canDisplayObstacle() {
		if (obstIconSelected.getTypeObstacle() != null) {
			//un seul depart / arrivee ou les autres obstacles
			if (obstIconSelected.getTypeObstacle() == Obstacles.ARRIVEE && endExist) {
				return false;
			}else if(obstIconSelected.getTypeObstacle() == Obstacles.DEPART && startExist) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public void initIcon(ArrayList<ObstacleIcon> obstacleIcon) {
		int i = 1;
		for (ObstacleIcon obsIcon : obstacleIcon) {
			Pane pane = createIcon(obsIcon);
	    	pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent t) {
	            	obstIconSelected = obsIcon;
	            	isClear = false;
	            	putObstacle = false;
	            	pane.setStyle("-fx-background-color:#dae7f3;");
	            }
	        });
	    	
			gridPaneObstacle.add(pane, 0, i);
			i = i+2;
		}
		Pane pane = createIcon(new ObstacleIcon(new Image("file:src/main/resources/balais.png"), null));
    	pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	obstIconSelected = null;
            	isClear = true;
            	putObstacle = false;
            	pane.setStyle("-fx-background-color:#dae7f3;");
            }
        });
    	
		gridPaneObstacle.add(pane, 0, i+2);
		
	}
	
	private Pane createIcon(ObstacleIcon obsIcon) {
		Pane pane = new Pane();
    	ImageView image_view = new ImageView(obsIcon.getImg());
    	image_view.setFitWidth(30);
    	image_view.setFitHeight(30);
    	pane.getChildren().add(image_view);
    	
    	pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	pane.setStyle("-fx-cursor: hand;");
            }
        });
    	
    	return pane;
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
	
	public Plateau getPlateau() {
		return plateau;
	}


}
