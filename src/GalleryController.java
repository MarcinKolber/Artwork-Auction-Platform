import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GalleryController {

	@FXML
	private FlowPane listGalleries;

	@FXML
	private Button createNewGallery;

	@FXML
	private FlowPane display;

	@FXML
	private VBox vbox1;

	public void initialize() {

		Random rnd = new Random();

		createNewGallery.setOnAction(e -> galleryCreator());

		ArrayList<CustomGallery> galleries = FileReader.readGalleries(LoginController.getUser());
		System.out.println("The size is " + galleries.size());

		for (int i = 0; i < galleries.size(); i++) {
			Button b1 = new Button(galleries.get(i).getName());

			b1.setMinWidth(214);
			b1.setMinHeight(100);
			int r = rnd.nextInt(255);
			int g = rnd.nextInt(100);
			int b = rnd.nextInt(255);

			b1.setStyle("-fx-background-color: rgb(" + 0 + "," + g + "," + b + ",0.2); -fx-font-size: 1.5em; ");
			vbox1.getChildren().add(b1);
		}
		

	}

	public void displayGallery() {

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

			stage.show();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}

	}
}
