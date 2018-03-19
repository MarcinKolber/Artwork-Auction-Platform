import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A class to represent notifications
 * @author Marcin Kolber (869527)
 *
 */
public class NotificationField {

	private int count;	// number of new items
	private VBox vbox; // box to be processed
	private Canvas canvas; // canvas to draw notification on it
	private Label label; // label of the 
	private boolean change;

	/**
	 * Constructor the the notification field
	 * @param box box to hold elements
	 * @param canvas canvas for drawing a circle
	 * @param label label with description
	 */
	public NotificationField(VBox box, Canvas canvas, Label label) {
		this.label = label;
		this.vbox = box;
		this.canvas = canvas;

		vbox.setStyle("-fx-background-color: #1b1b1b");
		vbox.setOnMouseEntered(e-> {vbox.setStyle("-fx-background-color: #2e3135;");});
		vbox.setOnMouseExited(e-> {vbox.setStyle("-fx-background-color: #1b1b1b");});

    	GraphicsContext gc = canvas.getGraphicsContext2D();

    	int val = Integer.parseInt(label.getText());
		if(val>0){
	    	DrawNotification.drawShapes(gc);
		}
		else{
	    	DrawNotification.drawShapesBlue(gc);
		}

	}

	/**
	 * Constructor for the notification field
	 * @param box box to hold elements
	 * @param canvas canvas for drawing a circle
	 * @param label label with description
	 * @param val number of new elements
	 */
	public NotificationField(VBox box, Canvas canvas, Label label, int val) {
		this.label = label;
		this.vbox = box;
		this.canvas = canvas;

		vbox.setStyle("-fx-background-color: #1b1b1b");
		vbox.setOnMouseEntered(e-> {vbox.setStyle("-fx-background-color: #2e3135;");});
		vbox.setOnMouseExited(e-> {vbox.setStyle("-fx-background-color: #1b1b1b;");});
		
    	GraphicsContext gc = canvas.getGraphicsContext2D();

		if(val>0){
	    	DrawNotification.drawShapes(gc);

		}
		else {
	    	DrawNotification.drawShapesBlue(gc);

		}

	}

	/**
	 * Method to return isChange
	 * @return true if there was any change, false otherwise
	 */
	public boolean isChange() {
		return change;
	}

	/**
	 * Method to setChange
	 * @param change - boolean
	 */
	public void setChange(boolean change) {
		this.change = change;
	}

	/**
	 * Method to get the Vbox
	 * @return vbox of the notification panel
	 */
	public VBox getVbox() {
		return vbox;
	}

	/**
	 * Method to set the Vbox
	 * @param vbox box to be set
	 */
	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
	
	
	
	

}
