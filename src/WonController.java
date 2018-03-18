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
    
    public void initialize() {
    	
    	ArrayList<Artwork> won = Updates.won();
    	
		date.setText(Updates.getDate()+"");

		ArrayList<Listing> listings = new ArrayList<>();
		
		for(Artwork artwork: won) {
			Listing listing = new Listing(artwork);
			listings.add(listing);
		}
		
		main.getChildren().addAll(listings);
    	
    }

}
