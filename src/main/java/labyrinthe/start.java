package labyrinthe;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class start {

	private int NB_CASE = 20;

	public void start(Stage primaryStage) throws Exception{
		//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

		GridPane gridPane = new GridPane();

		gridPane.setPadding(new Insets(20));

		for (int i = 0; i < NB_CASE; i++) {
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
		}

		for (int j = 0; j < NB_CASE; j++)
		{
			ColumnConstraints col = new ColumnConstraints(30);
			gridPane.getColumnConstraints().add(col);
		}


		//gridPane.getRowConstraints().add(new RowConstraints(30, 30, 30));

        /*for (int row = 0; row < 10; row++)
        {
            gridPane.getRowConstraints().add(new RowConstraints(30, 30, 30));

            Text textTest = new Text("row "+row+" ");
            gridPane.addRow(row, textTest);

            for (int column = 0; column < 10; column++)
            {
                //gridPane.getColumnConstraints().add(new ColumnConstraints(30, 30, 30));
                Text textTest2 = new Text("col "+column+" ");
                gridPane.addColumn(column, textTest2);
            }
        }*/

		gridPane.setGridLinesVisible(true);

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(gridPane, 300, 275));
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}

}
