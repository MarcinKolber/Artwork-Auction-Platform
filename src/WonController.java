import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * A controller to display won artworks
 * @author 869527
 *
 */
public class WonController {

    @FXML
    private Label date; // a label to display a date of last login

    @FXML
    private FlowPane main; // a container with won artworks
    
    /**
     * Initialises GUI
     */
    public void initialize() {
    	
    	// Gets a list of won artworks 
    	ArrayList<Artwork> won = Updates.won();
    	
    	// Displays time of the last login
		date.setText(Updates.getDate()+"");

		// Intialises a list of artwork views
		ArrayList<AuctionView> listings = new ArrayList<>();
		
		// Adds artworks to a list to be displayed
		for(Artwork artwork: won) {
			AuctionView listing = new AuctionView(artwork);
			listings.add(listing);
		}
		
		// Displays a list of artworks
		main.getChildren().addAll(listings);
    	
    }

}
