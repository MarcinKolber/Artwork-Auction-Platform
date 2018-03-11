import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * This is the GUI class for displaying user information
 * 
 * @author Marcin Created on 04/12/2017
 */
public class DashboardSettingsController {

	@FXML
	private ListView<Date> list;

	@FXML
	private Label username; // Shows username of user

	@FXML
	private Label firstName; // Shows first name of user

	@FXML
	private Label lastName; // Shows last name of user

	@FXML
	private Label fullName; // Shows full name of user

	@FXML
	private Label address; // Shows address of user

	@FXML
	private Label postcode; // Shows postcode of user

	@FXML
	private Label lastLogin; // Shows last time logged in

	@FXML
	private Label phoneNumber; // Shows phone number of user

	@FXML
	private ImageView avatar; // Container for the user's avatar image

	private User user; // A user object

	@FXML
	private VBox logins;

	/**
	 * Method to intialise the dashboard window
	 */
	public void initialize() {

		user = LoginController.getUser();
		ArrayList<Date> ddd1 = (ArrayList<Date>) user.getLogins().clone();
		Collections.reverse(ddd1);
		ObservableList<Date> items = FXCollections.observableArrayList(ddd1);
		list.setItems(items);
		username.setText(user.getUsername());
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		fullName.setText(user.getFullName());
		address.setText(user.getAddress());
		postcode.setText(user.getPostcode());
		phoneNumber.setText(user.getPhonenumber() + "");
		avatar.setImage(user.getImage());
		// showLogins();
	}

	public void showLogins() {

		for (Date d : user.getLogins()) {

			Label l = new Label();
			l.setText(d.toString());
			logins.getChildren().add(l);

		}

	}

}
