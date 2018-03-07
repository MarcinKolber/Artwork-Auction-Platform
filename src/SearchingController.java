import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SearchingController {

    @FXML
    private HBox searchingTab;

    @FXML
    private Pane s;
    
    
    public void initialize() {
    	s.setOnMouseClicked(new EventHandler<MouseEvent>() {
    	    @Override
    	    public void handle(MouseEvent t) {
    	        System.out.println("Oh dear lord, they clicked me!?");
    	    }
    	});
    	
    	
    	for()
    }

}




