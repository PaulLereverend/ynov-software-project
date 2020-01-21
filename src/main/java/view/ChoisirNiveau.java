package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ChoisirNiveau {
		
	private GridPane gridPane = new GridPane();

	public ChoisirNiveau(Stage primaryStage, boolean isDisplay) {
		super();
		
		gridPane.setPadding(new Insets(100,100,100,100));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(30);
		
		int i;
		for (i = 0; i < 3; i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
			Button niveau = new Button();
			niveau.setMinWidth(150);
			niveau.setText("Niveau "+i);
			niveau.setOnMouseClicked((event)->{
    			new AffichagePlateau(primaryStage, isDisplay);
    		});
			gridPane.add(niveau, 0, i);
		}
		
		RowConstraints row = new RowConstraints(30);
		gridPane.getRowConstraints().add(row);
		Button retour = new Button();
		retour.setMinWidth(150);
		retour.setText("Retour");
		retour.setOnMouseClicked((event)->{
			new Menu(primaryStage);
		});
		gridPane.add(retour, 0, i+1);
		
		Scene scene = new Scene(gridPane);

		primaryStage.setTitle("Labyrinthe");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}


}
