import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NotificationField {

	private int count;
	private VBox vbox;
	private Canvas canvas;
	private Label label;

	public NotificationField(VBox box, Canvas canvas, Label label) {


		this.label = label;
		this.vbox = box;
		this.canvas = canvas;
		vbox.setStyle("-fx-background-color: #1b1b1b");
		vbox.setOnMouseEntered(e-> {vbox.setStyle("-fx-background-color: #2e3135;");});
		vbox.setOnMouseExited(e-> {vbox.setStyle("-fx-background-color: #1b1b1b;");});
		
    	Updates u = new Updates(LoginController.getUser());
    	GraphicsContext gc = canvas.getGraphicsContext2D();

    	DrawNotification.drawShapes(gc);
    	
    	label.setText(u.getNewArtworks().size() + "");
    	
	}

}
