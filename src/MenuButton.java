import javafx.scene.control.Button;

public class MenuButton extends Button {

	private String title;
	private boolean separateWindow;
	public MenuButton(String title, String resource) {
		
		
		this.title = title;
		
		this.setPrefWidth(200);
		
		setText(title);
		this.setStyle("-fx-background-color: #1b1b1b;  -fx-font-size: 1.5em;");
		this.setOnMouseEntered(e-> {this.setStyle("-fx-background-color: #2e3135; -fx-font-size: 1.5em;");});
		this.setOnMouseExited(e-> {this.setStyle("-fx-background-color: #1b1b1b; -fx-font-size: 1.5em;");});
		setOnMouseClicked(e-> open());
		
	}
	
	public void open() {
		
	}
	
}
