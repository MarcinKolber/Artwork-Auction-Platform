import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A class to draw circles for notifications
 * @author Marcin Kolber (869527)
 *
 */
public class DrawNotification {

	// Parameters for width and height of ovals
	private final static int DEFAULT_WIDTH = 75;
	private final static int DEFAULT_HEIGHT = 75;

	/**
	 * Method to create the red notification circle
	 * @param gc - graphics content to be processed
	 */
	public static void drawShapes(GraphicsContext gc) {
		
		Color color = new Color(0.502, 0.000, 0.004, 1); // pick a color
		gc.setFill(color);
		
		// draw a circle
		gc.fillOval(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}

	/**
	 * Method to create the no-new blue notification circle
	 * @param gc - graphics content to be processed
	 */
	public static void drawShapesBlue(GraphicsContext gc) {
		Color color = new Color(0.000, 0.000, 0.502, 0.3); // pick a color
		gc.setFill(color);
		
		// draw a circle
		gc.fillOval(0,0,DEFAULT_WIDTH,DEFAULT_HEIGHT);
	}


}
