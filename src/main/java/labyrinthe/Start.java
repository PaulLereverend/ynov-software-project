package labyrinthe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Start extends Application{

		
	private int NB_CASE = 20;

	public void start(Stage primaryStage) throws Exception{
		//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

		GridPane gridPane = new GridPane();
		
		//gridPane.setMinSize(400, 200);
		gridPane.setPadding(new Insets(20));
		gridPane.setAlignment(Pos.CENTER);

		for (int i = 0; i < NB_CASE; i++) {
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
		}

		for (int j = 0; j < NB_CASE; j++)
		{
			ColumnConstraints col = new ColumnConstraints(30);
			gridPane.getColumnConstraints().add(col);
		}

		gridPane.setGridLinesVisible(true);

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(gridPane, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
