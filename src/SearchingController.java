import java.awt.*;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;


public class SearchingController {

	@FXML
	private HBox searchingTab;

	@FXML
	private Pane s;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchingTextField;

	public void initialize() {

		searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String userInput = searchingTextField.getText();
				System.out.println(userInput);
			}
		});

		ArrayList<Artwork> arts = FileReader.getArtworks();
		for (Artwork a : arts) {
			if (a.getTitle().contains("Test")) {
				System.out.println(a.getTitle());

			}
		}
	}

}
