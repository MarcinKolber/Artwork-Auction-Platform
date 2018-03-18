import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * A controller for displaying user accounts
 * created since the last login 
 * @author 869527
 *
 */
public class NewUsersController {

	@FXML
	private FlowPane main;	// container with users
	
    @FXML
    private Label lastLogin;	// label displaying last login time

    // Array lists of users and views
	private static ArrayList<User> users;
	private ArrayList<UserView> userViews;

	/**
	 * Initialises the window
	 */
	public void initialize() {
		
		// Displays time of last login
		lastLogin.setText(Updates.getDate()+"");
		
		// List of user views
		userViews = new ArrayList<>();

		// Loops through recently created accounts and adds them to an arraylist
		for (User user : users) {
			UserView uv = new UserView(user);
			userViews.add(uv);

		}

		// Display all users from the list
		main.getChildren().addAll(userViews);

	}

	/**
	 * Returns a list of users
	 * @return list of users
	 */
	public ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Sets a list of users 
	 * @param users1 list of users
	 */
	public static void setUsers(ArrayList<User> users1) {
		users = users1;
	}

}
