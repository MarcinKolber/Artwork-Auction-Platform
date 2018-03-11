import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class NotificationDisplayController {

    @FXML
    private FlowPane main;
    
    private static ArrayList<Artwork> arts = new ArrayList<>();
    
    public void initialize() {
    	

    	for(Artwork ar: arts) {
        	Listing l1 = new Listing(ar);
        	main.getChildren().add(l1);
    	}
    	



    	
    }

	public static ArrayList<Artwork> getArts() {
		return arts;
	}

	public static void setArts(ArrayList<Artwork> arts1) {
		arts = arts1;
	}
    
    

}