/**
 * A class to create a new custom gallery
 * @author Ayden Ballard - 905438
 */

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

/**
 * A class to control creating galleries
 * @author Ayden Ballard (905438)
 *
 */
public class GalleryCreatorController {

	@FXML
	private TextField title; //title of the gallery

	@FXML
	private TextArea description; //description of the gallery

	@FXML
	private Slider paintingSlider; //painting capacity slider

	@FXML
	private Label paintVal; //painting capacity value

	@FXML
	private Slider sculpturesSlider; //sculpture capacity slider

	@FXML
	private Label sculpturesVal; //sculpture capacity value

	@FXML
	private Button addGallery; //button to create gallery
	
	private final int MAX_SLIDER_VAL = 20;

	/**
	 * Method to initialize the Gallery
	 */
	public void initialize() {
		paintingSlider.setMax(MAX_SLIDER_VAL); // set the paintingSlider max to 20
		sculpturesSlider.setMax(MAX_SLIDER_VAL); // set the sculptureSlider max to 20

		// Sets a button to create a gallery on action
		addGallery.setOnAction(e -> addGallery());

		
		// Refreshes the label when slider is used
		paintingSlider.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
				paintVal.setText((int) paintingSlider.getValue() + "");
			}
		});
		sculpturesSlider.valueProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
				sculpturesVal.setText((int) sculpturesSlider.getValue() + "");
			}
		});

	}

	/**
	 * Method to create a new gallery and create the gallery text file,
	 * it then alerts the user whether the gallery was successfully created or not
	 */
	public void addGallery() {
		String gTitle = title.getText(); // storing the title of the gallery
		String gdDescription = description.getText(); // storing the description of the gallery
		int paintings = (int) paintingSlider.getValue(); // storing the number of painting for the gallery
		int sculptures = (int) sculpturesSlider.getValue(); // storing the number of sculptures for the gallery

		// creating the gallery
		CustomGallery cg = new CustomGallery(gTitle, LoginController.getUser(), gdDescription, paintings, sculptures);

		try {
			// writing the gallery to the text file
			Writer.createCustomGallery(LoginController.getUser(), cg);

			// alerting the user that the gallery was successfully made
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Gallery has been created");
			alert.setContentText("You can start adding elements to it");
			alert.showAndWait();

			addGallery.getScene().getWindow().hide();

		} catch (IOException e) { // if something went wrong creating the gallery
			Alert alert = new Alert(AlertType.ERROR); // alert the user it could not be created
			alert.setHeaderText("Gallery could not be created");
			alert.setContentText("Make sure all fields are filled");
			alert.showAndWait();
		}

	}

}
