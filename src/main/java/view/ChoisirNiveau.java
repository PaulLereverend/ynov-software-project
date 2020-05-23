package view;

import java.util.List;

import entities.Niveau;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.ORM;

public class ChoisirNiveau {
		
	private GridPane gridPane = new GridPane();

	public ChoisirNiveau(Stage primaryStage, String type) {
		super();
		
		gridPane.setPadding(new Insets(100,100,100,100));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(30);
		
		int i;
		List<Niveau> listNiveau = ORM.listNiveaux();
		for (i = 0 ; i < listNiveau.size() ; i++) {
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
			
			Button level = new Button();
			level.setMinWidth(150);
			level.setText(listNiveau.get(i).getNom());
			final int i2 = i;
			level.setOnMouseClicked((event)->{
    			new AffichagePlateau(primaryStage, type, listNiveau.get(i2));
    		});
			
			gridPane.add(level, 0, i);
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

		primaryStage.setScene(scene);
	}


}
