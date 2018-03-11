import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;


public class SearchingController {

	@FXML
	private HBox searchingTab;

	@FXML
	private FlowPane s;

	@FXML
	private Button searchButton;

	@FXML
	private TextField searchingTextField;

	public void initialize() {
		ArrayList<Artwork> arts = FileReader.getArtworks();
		
		searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				s.getChildren().clear();
				String userInput = searchingTextField.getText();
				
			
				
				for(Artwork artwork: arts) {
					if(artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
						System.out.println(userInput);
						Listing l = new Listing(artwork);
						s.getChildren().add(l);
					}
				}
				
				
			}

			
		});

		searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				String userInput = searchingTextField.getText();
				System.out.println(userInput);
				
			
				
				for(Artwork artwork: arts) {
					if(artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
						Listing l = new Listing(artwork);
						s.getChildren().add(l);
					}
				}
				
				
			}
		});

		for (Artwork a : arts) {
			if (a.getTitle().contains("Test")) {
				System.out.println(a.getTitle());

			}
		}
	}

}
