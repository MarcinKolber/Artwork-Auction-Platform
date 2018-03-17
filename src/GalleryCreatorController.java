import java.awt.image.BufferedImage;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;

public class GalleryCreatorController {

    @FXML
    private TextField title;

    @FXML
    private TextArea description;

    @FXML
    private Slider paintingSlider;

    @FXML
    private Label paintVal;

    @FXML
    private Slider sculpturesSlider;

    @FXML
    private Label sculpturesVal;

    @FXML
    private Button addGallery;
    
    
    public void initialize() {
    	paintingSlider.setMax(20); //set the paintingSlider max to 20
    	sculpturesSlider.setMax(20); //set the sculptureSlider max to 20
    	
    	addGallery.setOnAction(e-> addGallery());

    	paintingSlider.valueProperty().addListener(new ChangeListener<Object>() {
    		@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
    			paintVal.setText((int)paintingSlider.getValue()+"");
			}
		});
    	sculpturesSlider.valueProperty().addListener(new ChangeListener<Object>() {
    		@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
    			sculpturesVal.setText((int)sculpturesSlider.getValue()+"");
			}
		});

    	
    }

	/**
	 * Method to add a new gallery
	 */
	public void addGallery() {
    	String gTitle = title.getText(); //storing the title of the gallery
    	String gdDescription = description.getText(); //storing the description of the gallery
    	int paintings = (int) paintingSlider.getValue(); //storing the number of painting for the gallery
    	int sculptures = (int) sculpturesSlider.getValue(); //storing the number of sculptures for the gallery

    	//creating the gallery
    	CustomGallery cg = new CustomGallery(gTitle, LoginController.getUser(), gdDescription, paintings, sculptures);

    	try {
    		//writing the gallery to the text file
			Writer.createCustomGallery( LoginController.getUser(), cg);

			//alerting the user that the gallery was successfully made
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Gallery has been created");
			alert.setContentText("You can start adding elements to it");
			alert.showAndWait();

			addGallery.getScene().getWindow().hide();
<<<<<<< HEAD
			
			
			
		} catch (IOException e) { //if something went wrong creating the gallery
=======
		}
		catch(IOException e){ //if something went wrong creating the gallery
>>>>>>> branch 'master' of https://github.com/ElMarko1/CS-235-Assignment-2.git
			Alert alert = new Alert(AlertType.ERROR); //alert the user it could not be created
			alert.setHeaderText("Gallery could not be created");
			alert.setContentText("Make sure all fields are filled");
			alert.showAndWait();			
		}
    	
    	
    	
    }
    
    

}
