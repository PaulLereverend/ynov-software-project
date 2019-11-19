package labyrinthe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Plateau;

public class Start extends Application{

	public void start(Stage primaryStage) throws Exception{
			
		/*Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		
		Obstacle obstacle = new Obstacle(0,0,"test", "rouge", 1,1);
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(obstacle);
		session.getTransaction().commit();
		session.close();*/
		//Parent root = FXMLLoader.load(getClass().getRepoint_depart("sample.fxml"));

		GridPane gridPane = new GridPane();
		  
		gridPane.setPadding(new Insets(20,20,20,20));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setGridLinesVisible(true);
		Plateau plateau = new Plateau(gridPane);
		Scene scene = new Scene(gridPane);

		primaryStage.setTitle("Hello World");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {	
		launch(args);
	}
}
