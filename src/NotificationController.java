import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class to display a panel with notifications
 * @author Joshua Thomas (907019)
 *
 */
public class NotificationController {

	@FXML
	private VBox one; // vbox for the first panel

	@FXML
	private StackPane stackPane; // a pane to display notifications

	@FXML
	private Canvas canvas1; // canvas to be drawn on

	@FXML
	private Label newArts; // label with a number of new artworks

	@FXML
	private VBox previouslyBidded; // vbox to represent new bids

	@FXML
	private StackPane stackPane1; // pane to handle layout of the new bids panel

	@FXML
	private Canvas canBid; // canvas to draw a notification circle on it

	@FXML
	private Label prevNo; // a label to represent number of new bids on previously bidded items

	@FXML
	private VBox won; // vbox to show won artworks since the last login

	@FXML
	private StackPane stackPane11; // pane to handle layout of the  won artworks notification panel

	@FXML
	private Canvas canWon; // canvas to draw a circle related to notifications about won artworks

	@FXML
	private Label wonNo; // label to represent a number of artworks won since the last login 

	@FXML
	private VBox newUsers; // a box to show new users since the last login

	@FXML
	private StackPane stackPane111; // stack pane to display notifications about new userss

	@FXML
	private Canvas newUsersCan; // a canvas to draw a circle on

	@FXML
	private Label newUsersCount; // a label to show a number of new users

	@FXML
	private VBox one1111; 

	@FXML
	private StackPane stackPane1111;

	@FXML
	private Canvas can;

	@FXML
	private Label newArts1111;

	@FXML
	private VBox lostAuc; // represents a box for lost auctions

	@FXML
	private StackPane stackPane11111;

	@FXML
	private Canvas lostCan; // canvas to represent a 

	@FXML
	private Label lost; // label to represent a number of lost artworks

	@FXML
	private VBox close; // box to hold items for closing auctions

	@FXML
	private Canvas closeCan; // canvas to draw a circle for closing auctions

	@FXML
	private Label closeLabel; // a label to show a number of ending auctions
	
	private Updates update;
	private final int REMAINING_BIDS = 5;

	/**
	 * Initialises the GUI
	 */
	public void initialize() {
		// Computes updates for user
		update = new Updates(LoginController.getUser());
		
		// Sets an array list of new users to a new users controller
		NewUsersController.setUsers(Updates.getUsers());

		// Sets an array list of new bids
		NewBidController.setBids(Updates.newBids());
		NotificationField[] notifications = new NotificationField[6];

		// Stores number of new artworks/bids/users
		int newArtsCount = update.getNewArtworks().size();
		int newBidsCount = Updates.newBids().size();
		int endingNumber = Updates.endingAuctions(REMAINING_BIDS).size();
		int lostCount = Updates.lost().size();
		int wonCount = Updates.won().size();


		// Update controllers
		NotificationDisplayController.setArts(update.getNewArtworks());

		// checks number of new users
		int newUsers1 = Updates.getUsers().size();
		
		
		lost.setText(lostCount+"");
		
		// Creates notification fields as interactive buttons with a vbox, canvas, label and a number as parameters
		NotificationField newArt = new NotificationField(one, canvas1, newArts, newArtsCount);
		NotificationField bidded = new NotificationField(previouslyBidded, canBid, prevNo,newBidsCount);
		NotificationField wonAuctions = new NotificationField(won, canWon, wonNo, wonCount);
		NotificationField users = new NotificationField(newUsers, newUsersCan, newUsersCount, newUsers1);
		NotificationField lostArt = new NotificationField(lostAuc, lostCan, lost, lostCount);
		NotificationField closeAuc = new NotificationField(close, closeCan, closeLabel, endingNumber);
		

		// Sets displayed labels with numbers
		newArts.setText(newArtsCount + "");
		prevNo.setText(newBidsCount +"");
		closeLabel.setText(endingNumber +"");
		newUsersCount.setText(newUsers1 +"");
		wonNo.setText(wonCount +"");

		// Puts notification field into an array
		notifications[0] = newArt;
		notifications[1] = bidded;
		notifications[2] = wonAuctions;
		notifications[3] = users;
		notifications[4] = lostArt;
		notifications[5] = closeAuc;
		
		// Sets notification buttons on action
		notifications[0].getVbox().setOnMouseClicked(e-> openNewWindow(0));
		notifications[1].getVbox().setOnMouseClicked(e-> openNewWindow(1));
		notifications[2].getVbox().setOnMouseClicked(e-> openNewWindow(2));
		notifications[3].getVbox().setOnMouseClicked(e-> openNewWindow(3));
		notifications[4].getVbox().setOnMouseClicked(e-> openNewWindow(4));		
		notifications[5].getVbox().setOnMouseClicked(e-> openNewWindow(5));


		
	}
	
	/**
	 * Displays a different window depending on an index
	 * @param i index of the notification button
	 */
	public void openNewWindow(int i) {
		Scene scene;
		try {
			
			// new artworks
			if(i==0) {	
				scene = new Scene(FXMLLoader.load(getClass().getResource("NotificationDisplay.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			// new bids on currently bidded artworks
			if(i==1) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("NewBids.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			// won artworks since the last login
			if(i==2) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("Won.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			
			// new users since the last login
			if(i==3) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("NewUsers.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			// lost auctions since the last login
			if(i==4) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("Lost.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			// auctions coming to the end
			if(i==5) { 
				scene = new Scene(FXMLLoader.load(getClass().getResource("EndingAuctions.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * A method to switch back to searching
	 */
	public void switchTab() {
		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/Searching.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns an object of updates
	 * @return updates
	 */
	public Updates getUpdate() {
		return update;
	}

	/**
	 * Sets an object containting updates
	 * @param update updates to be set
	 */
	public void setUpdate(Updates update) {
		this.update = update;
	}
	
}
