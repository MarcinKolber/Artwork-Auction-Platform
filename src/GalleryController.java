import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;

public class GalleryController {

    @FXML
    private FlowPane listGalleries;

    @FXML
    private Button createNewGallery;

    @FXML
    private FlowPane display;

    
    public void initialize() {
    	
    	createNewGallery.setOnAction(e->galleryCreator());
    	
    }
    
    public void galleryCreator() {
    	
    	
    	
    }
}
