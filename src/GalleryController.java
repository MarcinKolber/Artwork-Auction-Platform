import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/GalleryCreator.fxml"));

		// Try to display the user
		try {
			Parent root = fxmlL.load();

			Scene scene = new Scene(root);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}
    	
    	
    }
}
