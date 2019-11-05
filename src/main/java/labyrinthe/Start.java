package labyrinthe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Obstacle;

public class Start extends Application{

		
	private int NB_CASE = 20;

	public void start(Stage stage) throws Exception{
		
		/*Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		
		Obstacle obstacle = new Obstacle(0,0,"test", "rouge", 1,1);
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(obstacle);
		session.getTransaction().commit();
		session.close();*/
		//Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

		GridPane gridPane = new GridPane();
		
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
		
		Scene scene = new Scene(gridPane);

		stage.setTitle("Hello World");
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}


	public static void main(String[] args) {
		
		launch(args);
	}

}
