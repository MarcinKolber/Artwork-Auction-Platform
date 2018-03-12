import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class NewUsersController {

	@FXML
	private FlowPane main;
	

    @FXML
    private Label lastLogin;


	private static ArrayList<User> users;
	private ArrayList<UserView> userViews;

	public void initialize() {


		lastLogin.setText(Updates.getDate()+"");
		
		userViews = new ArrayList<>();

		for (User user : users) {
			UserView uv = new UserView(user);
			userViews.add(uv);

		}

		main.getChildren().addAll(userViews);

	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users1) {
		users = users1;
	}

}
