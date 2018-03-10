import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NotificationField {

	private int count;
	private VBox vbox;
	private Canvas canvas;
	private Label label;
	private boolean change;

	public NotificationField(VBox box, Canvas canvas, Label label) {

		

	
		this.label = label;
		this.vbox = box;
		this.canvas = canvas;
		vbox.setStyle("-fx-background-color: #1b1b1b");
		vbox.setOnMouseEntered(e-> {vbox.setStyle("-fx-background-color: #2e3135;");});
		vbox.setOnMouseExited(e-> {vbox.setStyle("-fx-background-color: #1b1b1b;");});
		
    	GraphicsContext gc = canvas.getGraphicsContext2D();

    	int val = Integer.parseInt(label.getText());
		if(val>0) {
	    	DrawNotification.drawShapes(gc);

		} else {
	    	DrawNotification.drawShapesBlue(gc);

		}
    	
    	
    	
	}

	
	public NotificationField(VBox box, Canvas canvas, Label label, int val) {

		

		
		this.label = label;
		this.vbox = box;
		this.canvas = canvas;
		vbox.setStyle("-fx-background-color: #1b1b1b");
		vbox.setOnMouseEntered(e-> {vbox.setStyle("-fx-background-color: #2e3135;");});
		vbox.setOnMouseExited(e-> {vbox.setStyle("-fx-background-color: #1b1b1b;");});
		
    	GraphicsContext gc = canvas.getGraphicsContext2D();

		if(val>0) {
	    	DrawNotification.drawShapes(gc);

		} else {
	    	DrawNotification.drawShapesBlue(gc);

		}
    	
    	
    	
	}
	
	public boolean isChange() {
		return change;
	}

	public void setChange(boolean change) {
		this.change = change;
	}


	public VBox getVbox() {
		return vbox;
	}


	public void setVbox(VBox vbox) {
		this.vbox = vbox;
	}
	
	
	
	

}
