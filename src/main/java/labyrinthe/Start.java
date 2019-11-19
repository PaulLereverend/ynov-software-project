package labyrinthe;

import javafx.application.Application;
import javafx.stage.Stage;
import view.Interface;

public class Start extends Application{

	public void start(Stage primaryStage) throws Exception{
		
		/*Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		
		Obstacle obstacle = new Obstacle(0,0,"test", "rouge", 1,1);
		Session session = sf.openSession();
		session.beginTransaction();
		session.save(obstacle);
		session.getTransaction().commit();
		
		session.close();
		InputStream inputStream = this.getClass()
		  .getClassLoader()
		  .getResourceAsStream("profile.png");
		 
		if(inputStream == null) {
		    fail("Unable to get resources");
		}
		user.setPhoto(IOUtils.toByteArray(inputStream));

		*
		*
		*/
		//Parent root = FXMLLoader.load(getClass().getRepoint_depart("sample.fxml"));
		
		Interface interfaceJeu = new Interface(primaryStage);
	}
	
	public static void main(String[] args) {	
		launch(args);
	}
}
