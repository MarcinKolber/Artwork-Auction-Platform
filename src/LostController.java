import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class LostController {

	@FXML
	private Label date;

	@FXML
	private FlowPane main;
	private ArrayList<Artwork> artworks;

	public void initialize() {

		artworks = Updates.lost();
		date.setText(Updates.getDate()+"");
		
		ArrayList<Listing> listings = new ArrayList<>();
		
		for(Artwork artwork: artworks) {
			Listing listing = new Listing(artwork);
			listings.add(listing);
		}
		
		main.getChildren().addAll(listings);

	}
}
