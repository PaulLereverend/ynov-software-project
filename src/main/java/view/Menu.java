package view;

import controller.Dijkstra;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.ORM;

public class Menu {
	
	private GridPane gridPane = new GridPane();

	public Menu(Stage primaryStage) {
		super();
		
		gridPane.setPadding(new Insets(100,100,100,100));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(30);
		
		for (int i = 0; i < 3; i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
			Button bouton = new Button();
			bouton.setMinWidth(150);
        	if (i == 0) {
        		bouton.setText("Jouer");
        		bouton.setOnMouseClicked((event)->{
        			new ChoisirNiveau(primaryStage, "read");
        		});
			}else if(i == 1) {
				bouton.setText("Modifier un niveau");
				bouton.setOnMouseClicked((event)->{
        			new ChoisirNiveau(primaryStage, "update");
        		});
			}else {
				bouton.setText("Créer un niveau");
				bouton.setOnMouseClicked((event)->{
        			new AffichagePlateau(primaryStage, "create");
        		});
			}
			gridPane.add(bouton, 0, i);
		}
		
		Scene scene = new Scene(gridPane);

		primaryStage.setScene(scene);
	}

}
