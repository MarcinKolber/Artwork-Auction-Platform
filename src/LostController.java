import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * Controller to display auctions lost since the last login
 * @author Daniel Hart (873503)  
 *
 */
public class LostController {

	@FXML
	private Label date;	// date of the last login

	@FXML
	private FlowPane main; // a GUI container to hold lost auctions 
	
	private ArrayList<Artwork> artworks; // array list of lost auctions

	/**
	 * Initialises the GUI
	 */
	public void initialize() {

		// Gets a list of lost artworks since the last login
		artworks = Updates.lost();
		
		// Displays a date
		date.setText(Updates.getDate()+"");
		
		// Creates a list of auctions
		ArrayList<AuctionView> listings = new ArrayList<>();
		
		// Loop through artworks and add them to a list of auctions
		for(Artwork artwork: artworks) {
			AuctionView listing = new AuctionView(artwork);
			listings.add(listing);
		}
		
		// Adds artworks to display
		main.getChildren().addAll(listings);

	}
}
