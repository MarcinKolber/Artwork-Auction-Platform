import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class EndingAuctionsController {

    @FXML
    private FlowPane main;

    
    
    
    public void initialize() {
    	
    	ArrayList<Artwork> ending = Updates.endingAuctions(5);
    	ArrayList<Listing> listings = new ArrayList<>();
    	
    	for(Artwork a : ending) {
    		Listing l = new Listing(a);
    		listings.add(l);
    	}
    	
    	main.getChildren().addAll(listings);
    	//main.getChildren().add
    	
    	
    	
    }
}
