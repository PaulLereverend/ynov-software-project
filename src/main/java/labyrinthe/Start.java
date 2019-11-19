package labyrinthe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import entities.Obstacle;

public class Start extends Application{
	
	//final Text point_depart = new Text(50, 100, "DRAG ME");
	final Text target = new Text(200, 100, "DROP HERE");
	private int NB_CASE = 20;
	static String PNG_START = "png;base64,";
	static String JPG_START = "jpg;base64,";

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
		
		Image point_arrivee_img = new Image("file:src/main/resources/drapeau.png");
		ImageView point_arrivee = new ImageView(point_arrivee_img);
		point_arrivee.setFitWidth(30);
		point_arrivee.setFitHeight(30);
		gridPane.add(point_arrivee, 10, 10);
		  
		//lignes
		for (int i = 0; i < NB_CASE; i++) { 
			RowConstraints row = new RowConstraints(30);
			gridPane.getRowConstraints().add(row);
		}
		
		//colonnes
		for (int j = 0; j < NB_CASE; j++) { 
			ColumnConstraints col = new ColumnConstraints(30); 
			gridPane.getColumnConstraints().add(col); 
		}
		
		for (int i = 0 ; i < NB_CASE ; i++) {
            for (int j = 0; j < NB_CASE; j++) {
            	initCase(i, j, gridPane);
            }
        }
		  
		Scene scene = new Scene(gridPane);

		
		primaryStage.setTitle("Hello World");
		primaryStage.sizeToScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {	
		launch(args);
	}
	
	private void initCase(final int colIndex, final int rowIndex,GridPane gridPane) {
		
        if (colIndex == 5 && rowIndex == 10) {
    	    ImageView img = new ImageView();
    	    img.setImage(new Image(getClass().getResource("src/main/resources/cross.png").toExternalForm()));
    	    
        	final Image point_depart_img = new Image("file:src/main/resources/cross.png");
        	final ImageView pane_img = new ImageView(point_depart_img);
        	pane_img.setFitWidth(30);
        	pane_img.setFitHeight(30);
        	
            
        	pane_img.setOnMouseEntered(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent t) {
                	pane_img.setStyle("-fx-background-color:#dae7f3;");
                    
                }
            });

        	pane_img.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent t) {
                	pane_img.setStyle("-fx-background-color:transparent;");
                }
            });
        	
        	pane_img.setOnDragDetected(new EventHandler <MouseEvent>() {
                public void handle(MouseEvent event) {
                    /* drag was detected, start drag-and-drop gesture*/
                    System.out.println("onDragDetected");
                    
                    /* allow any transfer mode */
                    Dragboard db = pane_img.startDragAndDrop(TransferMode.ANY);
                    
                    /* put a string on dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putString("cross");
                    db.setContent(content);
                    
                    event.consume();
                }
            });
        	
        	pane_img.setOnDragDone(new EventHandler <DragEvent>() {
                public void handle(DragEvent event) {
                    /* the drag-and-drop gesture ended */
                    System.out.println("onDragDone");
                    /* if the data was successfully moved, clear it */
                    if (event.getTransferMode() == TransferMode.MOVE) {
//                    	pane_img.setText("");
                    }
                    
                    event.consume();
                }
            });
        	
        	gridPane.add(pane_img, colIndex, rowIndex);
            
		} else {
			final Pane pane = new Pane();
			
			pane.setOnMouseEntered(new EventHandler<MouseEvent>() {

                public void handle(MouseEvent t) {
                	pane.setStyle("-fx-background-color:#dae7f3;z-index : -1000;");
                }
            });

			pane.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent t) {
                	pane.setStyle("-fx-background-color:transparent;z-index : -1000;");
                }
            });
			
			gridPane.add(pane, colIndex, rowIndex);
		} 
    }
}
