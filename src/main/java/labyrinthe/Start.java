package labyrinthe;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Effets;
import entities.Obstacle;
import entities.Obstacles;
import javafx.application.Application;
import javafx.stage.Stage;
import model.ORM;
import model.Plateau;
import view.AffichagePlateau;
import view.Menu;

public class Start extends Application{

	public void start(Stage primaryStage) throws Exception{
		
		
		/*InputStream inputStream = this.getClass()
		  .getClassLoader()
		  .getResourceAsStream("profile.png");
		 
		if(inputStream == null) {
		    fail("Unable to get resources");
		}
		user.setPhoto(IOUtils.toByteArray(inputStream)); */

		
		//Parent root = FXMLLoader.load(getClass().getRepoint_depart("sample.fxml"));
		
		Menu menuJeu = new Menu(primaryStage);
		
		primaryStage.setTitle("Labyrinthe");
		primaryStage.sizeToScene();
		primaryStage.show();
		
		//Plateau plateau = new Plateau();
	}
	/*public static void test() {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		
		Obstacle obstacle = new Obstacle(Effets.PASSANT,"Mur",null, 1,1, Obstacles.BOUE);
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(obstacle);
		session.getTransaction().commit();
		
		session.close();
	}*/
	public static void main(String[] args) {
		ORM.lancer();
		//Menu menuJeu = new Menu(primaryStage);
		launch(args);
	}
}
