import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawNotification {

	
	private Canvas canvas; 
	
	private GraphicsContext gc; // Object that allows drawing

	/**
	 * Method to create the notification box
	 * @param gc - graphics content
	 */
	public static void drawShapes(GraphicsContext gc) {
		Color color = new Color(0.502, 0.000, 0.004, 1);
		gc.setFill(color);

		gc.fillOval(0,0,75,75);
	}

	/**
	 * Method to create the no-new notification box
	 * @param gc - graphics content
	 */
	public static void drawShapesBlue(GraphicsContext gc) {
		Color color = new Color(0.000, 0.000, 0.502, 0.3);
		gc.setFill(color);

		gc.fillOval(0,0,75,75);
	}


}
