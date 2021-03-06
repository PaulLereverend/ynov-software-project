package view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controller.AStar;
import controller.Dijkstra;
import entities.Historique;
import entities.Niveau;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.ORM;
import model.ObstacleIcon;
import model.Plateau;

public class SideButtons {
	
	private GridPane gridPaneSide = new GridPane();
	
	private AffichagePlateau affichagePlateau;
	
	private Stage primaryStage;
	
	Text dikstraHisto = new Text();
	
	Text astarHisto = new Text();

	public SideButtons(AffichagePlateau affichagePlateau, Stage primaryStage, GridPane gridPaneSide) {
		this.gridPaneSide = gridPaneSide;
		this.affichagePlateau = affichagePlateau;
		this.primaryStage = primaryStage;
	}
	
	public void displayEditView(Niveau niveau, Plateau plateau, String type) {
		ArrayList<ObstacleIcon> pathImg = new ArrayList<>();
		ObstacleIcon o = new ObstacleIcon(new Image("file:src/main/resources/croix.png"), Obstacles.ARRIVEE);
		pathImg.add(o);
		o = new ObstacleIcon(new Image("file:src/main/resources/drapeau.png"), Obstacles.DEPART);
		pathImg.add(o);
		o = new ObstacleIcon(new Image("file:src/main/resources/boue.png"), Obstacles.BOUE);
		pathImg.add(o);
		o = new ObstacleIcon(new Image("file:src/main/resources/mur.png"), Obstacles.MUR);
		pathImg.add(o);
		initIcon(pathImg, niveau, plateau, type);
	}
	
	public void initIcon(ArrayList<ObstacleIcon> pathImg, Niveau niveau, Plateau plateau, String type) {
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
    	if (type.equals("create")) {
	    	levelName.setPrefWidth(30);
	    	gridPaneSide.add(levelName, 0, i+4);
    	}
    	
    	Button save = new Button();
    	save.setOnMouseClicked((event)->{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		try {
    	    	ObjectOutputStream os = new ObjectOutputStream(out);
				os.writeObject(plateau);
				if (type.equals("update")) {
					ORM.updateNiveau(niveau.getNom(), out.toByteArray());
					ORM.listNiveaux();
				}else {
					Niveau newNiveau = new Niveau(levelName.getText(), "Hugo", new Date(), new Date(), out.toByteArray());
					ORM.saveNiveau(newNiveau);
				}
				new Menu(primaryStage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public void displayExecView(Niveau niveau) {
		int i = 2;
		Button astar = new Button();
		astar.setText("A Star");
    	astar.setOnMouseClicked((event)->{
    		this.affichagePlateau.clearPlateau();
    		AStar a = new AStar(this.affichagePlateau.getPlateau(), this.affichagePlateau);
    		a.start(); // lance astar
    		updateHisto(niveau, dikstraHisto, astarHisto);
    		this.affichagePlateau.getPlateau().resetCases();
		});
    	gridPaneSide.add(astar, 0, i);
    	
    	Button dijkstra = new Button();
    	dijkstra.setOnMouseClicked((event)->{
    		this.affichagePlateau.clearPlateau();
    		Dijkstra dij = new Dijkstra(this.affichagePlateau);
    		dij.start(); //lance dijkstra
    		updateHisto(niveau, dikstraHisto, astarHisto);
    		this.affichagePlateau.getPlateau().resetCases();
		});
    	dijkstra.setText("Dijkstra");
    	gridPaneSide.add(dijkstra, 0, i+2);
    	
    	Button retour = new Button();
    	retour.setOnMouseClicked((event)->{
    		new Menu(primaryStage);
		});
    	retour.setText("Retour");
    	gridPaneSide.add(retour, 0, i+4);
    	
    	gridPaneSide.add(dikstraHisto, 0, i+6);
    	gridPaneSide.add(astarHisto, 0, i+7);
    	
    	updateHisto(niveau, dikstraHisto, astarHisto);
		
	}
	
	private void updateHisto(Niveau niveau, Text dikstraHisto, Text astarHisto) {
		//astarHisto.setText("A* : no data");
		//dikstraHisto.setText("Dijkstra : no data");
		List<Historique> histoDijsk = ORM.listHistoDijkstra(niveau);
    	if (histoDijsk != null && histoDijsk.size() > 0) {
    		dikstraHisto.setText("Dijkstra : "+histoDijsk.get(histoDijsk.size()-1).getResultat());
		}
		
		List<Historique> histoAstar = ORM.listHistoAstar(niveau);
		if (histoAstar != null && histoAstar.size() > 0) {
			astarHisto.setText("A* : "+histoAstar.get(histoAstar.size()-1).getResultat());
		}
	}

}
