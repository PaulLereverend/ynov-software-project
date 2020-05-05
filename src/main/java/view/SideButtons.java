package view;

import java.util.ArrayList;

import entities.Obstacles;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ORM;
import model.ObstacleIcon;

public class SideButtons {
	
	private GridPane gridPaneSide = new GridPane();
	
	private AffichagePlateau affichagePlateau;
	
	private Stage primaryStage;

	public SideButtons(AffichagePlateau affichagePlateau, Stage primaryStage, GridPane gridPaneSide) {
		this.gridPaneSide = gridPaneSide;
		this.affichagePlateau = affichagePlateau;
		this.primaryStage = primaryStage;
	}
	
	public void displayEditView() {
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
	
	public void initIcon(ArrayList<ObstacleIcon> pathImg) {
		int i = 1;
		for (ObstacleIcon obsIcon : pathImg) {
			Pane pane = createIcon(obsIcon);
	    	pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
	            public void handle(MouseEvent t) {
	            	affichagePlateau.obstIconSelected = obsIcon;
	            	affichagePlateau.isClear = false;
	            	affichagePlateau.putObstacle = false;
	            	clearBackground();
	            	pane.setStyle("-fx-background-color:#dae7f3;");
	            }
	        });
	    	
			gridPaneSide.add(pane, 0, i);
			i = i+2;
		}
		Pane pane = createIcon(new ObstacleIcon(new Image("file:src/main/resources/balais.png"), null));
    	pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	affichagePlateau.obstIconSelected = null;
            	affichagePlateau.isClear = true;
            	affichagePlateau.putObstacle = false;
            	pane.setStyle("-fx-background-color:#dae7f3;");
            }
        });
    	gridPaneSide.add(pane, 0, i+2);
    	
    	TextField levelName = new TextField();
    	levelName.setPrefWidth(30);
    	gridPaneSide.add(levelName, 0, i+4);
    	
    	Button save = new Button();
    	save.setOnMouseClicked((event)->{
    		//ORM.saveObstacle(new Obstacle(Effets.BLOQUANT, "mur", null, 0, 0, Obstacles.MUR));
    		ORM.listNiveaux();
		});
    	save.setStyle("-fx-background-color:lightgreen;");
    	save.setText("Sauvegarder");
    	gridPaneSide.add(save, 0, i+5);
    	
    	Button cancel = new Button();
    	cancel.setOnMouseClicked((event)->{
    		new Menu(primaryStage);
		});
    	cancel.setStyle("-fx-background-color:red;");
    	cancel.setText("Annuler");
    	gridPaneSide.add(cancel, 0, i+6);
		
	}
	
	protected void clearBackground() {
		for (Node elmt : gridPaneSide.getChildren()) {
			if (elmt instanceof Pane) {
				elmt.setStyle("-fx-background-color:transparent;");
			}
		}
		
	}

	private Pane createIcon(ObstacleIcon obsIcon) {
		Pane pane = new Pane();
    	ImageView image_view = new ImageView(obsIcon.getImg());
    	image_view.setFitWidth(30);
    	image_view.setFitHeight(30);
    	pane.getChildren().add(image_view);
    	
    	pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
            	pane.setStyle(pane.getStyle()+"-fx-cursor: hand;");
            }
        });
    	
    	return pane;
	}
	
	public void displayExecView() {
		int i = 2;
		Button astar = new Button();
		astar.setOnMouseClicked((event)->{
    		//lancer astar
		});
		astar.setText("A Star");
    	gridPaneSide.add(astar, 0, i);
    	
    	Button dijkstra = new Button();
    	dijkstra.setOnMouseClicked((event)->{
    		//lance dijkstra
		});
    	dijkstra.setText("Dijkstra");
    	gridPaneSide.add(dijkstra, 0, i+2);
    	
    	Button retour = new Button();
    	retour.setOnMouseClicked((event)->{
    		new Menu(primaryStage);
		});
    	retour.setText("Retour");
    	gridPaneSide.add(retour, 0, i+4);
		
	}

}
