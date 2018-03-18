import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * This class is used to diplay a user.
 */
public class UserView extends VBox {

	private User user; //The user being displayed
	private ImageView imgView; //Image holder for the user image.

	/**
	 * Method to set the user that is being diplayed.
	 * @param User - the desired user.
	 */
	public UserView(User user) {
		//Sets the image and imageView
		imgView = new ImageView();
		this.user = user;
		imgView.setImage(user.getImage());
		imgView.setFitWidth(150);
		imgView.setFitHeight(150);
		this.setPadding(new Insets(10, 10, 10, 10));

		Label username = new Label(user.getUsername() + "");
		Label date = new Label(user.getAccountCreationDate()+ "");

		getChildren().addAll(imgView, username, date);

		setOnMouseClicked(e -> displayInWindow());
	}

	/**
	 * Creates the new frame for displaying a user.
	 */
	public void displayInWindow() {

		UserDisplayController.setUser(user);

		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));

		try {
			Parent root = fxmlL.load();

			Scene scene = new Scene(root, 450, 300);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle(user.getUsername());
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
