package view;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import entities.Niveau;
import entities.Obstacles;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	
	GridPane gridPane = new GridPane();
	
	private GridPane gridPaneSide = new GridPane();
	
	private Plateau plateau = Plateau.getPlateau();
	
	//click sur icone
	boolean putObstacle = false;
	//seulement une seule pose (depart/arrivee)
	ObstacleIcon obstIconSelected = null;
	//depart/arrivee déjà posé sur le plateau
	boolean startExist = false;
	boolean endExist = false;
	
	boolean isClear = false;
	Niveau niveau = new Niveau();
	
	public AffichagePlateau(Stage primaryStage, String type) {
		super();
		configPlateau(primaryStage, type);
		
	}
	
	public AffichagePlateau(Stage primaryStage, String type, Niveau niveau) {
		super();
		this.niveau = niveau;
		configPlateau(primaryStage, type);
	}
	
	private void configPlateau(Stage primaryStage, String type) {
		HBox root = new HBox();
	    root.setPadding(new Insets(20, 20, 20, 20));
		
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setAlignment(Pos.CENTER);
		gridPaneSide.setAlignment(Pos.CENTER);
		gridPane.setGridLinesVisible(true);
		
		if (this.niveau.getComposition() != null) {
			ByteArrayInputStream in = new ByteArrayInputStream(this.niveau.getComposition());
		    ObjectInputStream is;
			try {
				is = new ObjectInputStream(in);
				this.plateau = (Plateau) is.readObject();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		//lignes
		for (int i = 0; i < plateau.getNbCases(); i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
			gridPaneSide.getRowConstraints().add(row);
		}
		
		//colonnes
		for (int j = 0; j < plateau.getNbCases(); j++) { 
			ColumnConstraints col = new ColumnConstraints(30); 
			gridPane.getColumnConstraints().add(col);  
		}
		
		for (Case[] cases : plateau.casesTab) {
			for (Case c : cases) {
				initPlateau(c, type);
				if (this.niveau != null && c.getObstacle() != null) {
					Image img = null;
					if (c.getObstacle().getType() == Obstacles.BOUE) {
						img = new Image("file:src/main/resources/boue.png");
					}else if (c.getObstacle().getType() == Obstacles.MUR) {
						img = new Image("file:src/main/resources/mur.png");
					}else if (c.getObstacle().getType() == Obstacles.DEPART) {
						img = new Image("file:src/main/resources/drapeau.png");
					}else if (c.getObstacle().getType() == Obstacles.ARRIVEE) {
						img = new Image("file:src/main/resources/croix.png");
					}
					ObstacleIcon obsIcon = new ObstacleIcon(img, c.getObstacle().getType());
			    	setDefaultObstable(c.getLigne(), c.getColonne(), obsIcon);
				}
			}
		}
		
		//setDefaultObstable(2, 2, new ObstacleIcon(new Image("file:src/main/resources/drapeau.png"), Obstacles.DEPART));
		//setDefaultObstable(16, 16, new ObstacleIcon(new Image("file:src/main/resources/croix.png"), Obstacles.ARRIVEE));
		//plateau.setCaseDepart(2, 2);
		//plateau.setCaseArrivee(16, 16);
		/*
		 * Création du menu des icones obstacles 
		 */
		SideButtons sb = new SideButtons(this, primaryStage, gridPaneSide);
		if (type.equals("create") || type.equals("update")) {
			sb.displayEditView(this.niveau, plateau, type);
		}else {
			sb.displayExecView(this.niveau);
		}
		
		root.getChildren().addAll(gridPane, gridPaneSide);
		
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
	}

	public GridPane getGridPane() {
		return gridPane;
	}
	
	private void setDefaultObstable(int row, int column, ObstacleIcon obstacleIcon) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column) {
				Pane p = (Pane)node;
				ImageView image_view = new ImageView(obstacleIcon.getImg());
		    	image_view.setFitWidth(30);
		    	image_view.setFitHeight(30);
		    	image_view.setUserData(obstacleIcon.getTypeObstacle());
		    	if (obstacleIcon.getTypeObstacle() == Obstacles.BOUE)
		    	{
			    	plateau.setCaseBoue(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), false);
				}
		    	else if(obstacleIcon.getTypeObstacle() == Obstacles.MUR) 
		    	{
					plateau.setCaseMur(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), false);
				}
		    	else if (obstacleIcon.getTypeObstacle() == Obstacles.ARRIVEE) 
		    	{
			    	plateau.setCaseArrivee(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), false);
					endExist = true;
				}
		    	else if(obstacleIcon.getTypeObstacle() == Obstacles.DEPART) 
		    	{
					plateau.setCaseDepart(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), false);
					startExist = true;
				}
			    p.getChildren().clear();
			    p.getChildren().add(image_view);
			}
		}
	}
	
	private void initPlateau(Case c, String type) {
		
		final int colIndex = c.getColonne();
		final int rowIndex = c.getLigne();
		
		final Pane pane = new Pane();
        
		if (type.equals("create") || type.equals("update")) {
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
	            	setObstacleInPane(pane);
	            }
	        });
	    	
	    	pane.setOnMousePressed(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent t) {
	            	if (putObstacle) {
						putObstacle = false;
					}else {
						putObstacle = true;
						setObstacleInPane(pane);
					}
	            }
	        });
		}
    	
    	gridPane.add(pane, colIndex, rowIndex);
    }
	
	protected void setObstacleInPane(Pane pane) {
		if( putObstacle && obstIconSelected != null && canDisplayObstacle() && pane.getChildren().size() == 0 )
    	{
    		ImageView image_view = new ImageView(obstIconSelected.getImg());
		    	image_view.setFitWidth(30);
		    	image_view.setFitHeight(30);
		    	image_view.setUserData(obstIconSelected.getTypeObstacle());
	    	if (obstIconSelected.getTypeObstacle() == Obstacles.BOUE)
	    	{
		    	plateau.setCaseBoue(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), false);
			}
	    	else if(obstIconSelected.getTypeObstacle() == Obstacles.MUR) 
	    	{
				plateau.setCaseMur(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), false);
			}
	    	else if (obstIconSelected.getTypeObstacle() == Obstacles.ARRIVEE) 
	    	{
		    	plateau.setCaseArrivee(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), false);
				endExist = true;
			}
	    	else if(obstIconSelected.getTypeObstacle() == Obstacles.DEPART) 
	    	{
				plateau.setCaseDepart(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), false);
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
    				if (pane.getChildren().get(0).getUserData().equals(Obstacles.BOUE)) 
    				{
    			    	plateau.setCaseBoue(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), true);
    				}
    				else if(pane.getChildren().get(0).getUserData().equals(Obstacles.MUR)) 
    				{
    					plateau.setCaseMur(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), true);
    				}
    				else if (pane.getChildren().get(0).getUserData().equals(Obstacles.ARRIVEE)) 
    				{
            			plateau.setCaseArrivee(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), true);
						endExist = false;
					}
    				else if (pane.getChildren().get(0).getUserData().equals(Obstacles.DEPART)) 
    				{
						plateau.setCaseDepart(GridPane.getRowIndex(pane), GridPane.getColumnIndex(pane), true);
						startExist = false;
					}
    			}
    			
        		pane.getChildren().clear();
    		}
    	}
	}

	boolean canDisplayObstacle() {
		if (obstIconSelected.getTypeObstacle() != null) {
			//un seul depart / arrivee ou les autres obstacles
			if (endExist && obstIconSelected.getTypeObstacle() == Obstacles.ARRIVEE) {
				return false;
			}else if(startExist && obstIconSelected.getTypeObstacle() == Obstacles.DEPART) {
				return false;
			}else {
				return true;
			}
		}
		return false;
	}
	
	public void colorierCase(Case c, Color couleur) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
				if (GridPane.getColumnIndex(node) == c.getColonne() && GridPane.getRowIndex(node) == c.getLigne()) {
					if (couleur != null) {
						node.setStyle("-fx-background-color:#"+Integer.toHexString(couleur.getRGB()).substring(2)+";");
					}else {
						node.setStyle(null);
					}
		        }
			}
	    }
	}
	
	public void clearPlateau() {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) != null && GridPane.getColumnIndex(node) != null) {
				this.colorierCase(new Case(GridPane.getRowIndex(node), GridPane.getColumnIndex(node), null), null);
			}
	    }
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public Niveau getNiveau() {
		return this.niveau;
	}


}
