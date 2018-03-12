import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class NewUsersController {

    @FXML
    private FlowPane main;

    private ArrayList<User> users;
    
    
    public void initialize() {
    	users = new ArrayList<>();
    }


	public ArrayList<User> getUsers() {
		return users;
	}


	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
    
    
}
