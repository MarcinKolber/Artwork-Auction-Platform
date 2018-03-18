import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GalleryController {


    @FXML
    private Label selected;

	
	@FXML
	private FlowPane listGalleries;

	@FXML
	private Button createNewGallery;

	@FXML
	private FlowPane display;

	@FXML
	private VBox vbox1;

	private ArrayList<CustomGallery> galleries;

	public void initialize() {

		Random rnd = new Random();

		createNewGallery.setOnAction(e -> galleryCreator());

		galleries = FileReader.readGalleries(LoginController.getUser());
		System.out.println("The size is " + galleries.size());

		ArrayList<GalleryButton> galleryButtons = new ArrayList<GalleryButton>();

		vbox1.getChildren().clear();
		for (CustomGallery g : galleries) {
			GalleryButton gb = new GalleryButton(g);
			
			
			galleryButtons.add(gb);
			vbox1.getChildren().add(gb);

	

			gb.setOnAction(e -> displayGallery(gb.getCustomGallery()));

		}

	}

	public void displayGallery(CustomGallery cg) {
		selected.setText(cg.getName());

		display.getChildren().clear();
		for (Artwork art : cg.getArtworks()) {
			Listing listing = new Listing(art);
			
			listing.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					if (event.isControlDown()) {
						System.out.println("delete");
						try {
							Writer.removeFromGallery(cg, listing.getArtwork());
							cg.deleteArtwork(art);
						} catch (FileNotFoundException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						display.getChildren().remove(listing);

					} else {

						try {
							listing.displayInWindow();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}

				}
			});
			
			display.getChildren().add(listing);
			
		

		}
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

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent paramT) {
					initialize();
				}
			});

			stage.showAndWait();

			initialize();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}

	}
	


}
