package model;

import entities.Obstacles;
import javafx.scene.image.Image;

public class ObstacleIcon {
	
	private Image img;
	
	private Obstacles typeObstacle;

	public ObstacleIcon(Image img, Obstacles typeObstacle) {
		super();
		this.img = img;
		this.typeObstacle = typeObstacle;
	}

	public Image getImg() {
		return img;
	}

	public Obstacles getTypeObstacle() {
		return typeObstacle;
	}
	
	

}
