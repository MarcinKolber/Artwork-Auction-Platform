import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class WonController {

    @FXML
    private Label date;

    @FXML
    private FlowPane main;
    
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
