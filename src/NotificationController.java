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

public class NotificationController {

	@FXML
	private VBox one;

	@FXML
	private StackPane stackPane;

	@FXML
	private Canvas canvas1;

	@FXML
	private Label newArts;

	@FXML
	private VBox previouslyBidded;

	@FXML
	private StackPane stackPane1;

	@FXML
	private Canvas canBid;

	@FXML
	private Label prevNo;

	@FXML
	private VBox won;

	@FXML
	private StackPane stackPane11;

	@FXML
	private Canvas canWon;

	@FXML
	private Label wonNo;

	@FXML
	private VBox newUsers;

	@FXML
	private StackPane stackPane111;

	@FXML
	private Canvas newUsersCan;

	@FXML
	private Label newUsersCount;

	@FXML
	private VBox one1111;

	@FXML
	private StackPane stackPane1111;

	@FXML
	private Canvas can;

	@FXML
	private Label newArts1111;

	@FXML
	private VBox lostAuc;

	@FXML
	private StackPane stackPane11111;

	@FXML
	private Canvas lostCan;

	@FXML
	private Label lost;

	@FXML
	private VBox close;

	@FXML
	private Canvas closeCan;

	@FXML
	private Label closeLabel;
	
	private Updates update;

	public void initialize() {
		update = new Updates(LoginController.getUser());
		NewUsersController.setUsers(Updates.getUsers());

		NotificationField[] notifications = new NotificationField[6];

		int newArtsCount = update.getNewArtworks().size();
		int newBidsCount = update.getNewBids().size();
		int endingNumber = Updates.endingAuctions(5).size();


		NotificationDisplayController.setArts(update.getNewArtworks());
		System.out.println(Updates.getUsers().size());

		int newUsers1 = Updates.getUsers().size();
		
		NotificationField newArt = new NotificationField(one, canvas1, newArts, newArtsCount);
		NotificationField bidded = new NotificationField(previouslyBidded, canBid, prevNo,newBidsCount);
		NotificationField wonAuctions = new NotificationField(won, canWon, wonNo);
		NotificationField users = new NotificationField(newUsers, newUsersCan, newUsersCount, newUsers1);
		NotificationField lostArt = new NotificationField(lostAuc, lostCan, lost);
		NotificationField closeAuc = new NotificationField(close, closeCan, closeLabel, endingNumber);
		

		newArts.setText(newArtsCount + "");
		prevNo.setText(newBidsCount +"");
		closeLabel.setText(endingNumber +"");
		 newUsersCount.setText(newUsers1 +"");


		notifications[0] = newArt;
		notifications[1] = bidded;
		notifications[2] = wonAuctions;
		notifications[3] = users;
		notifications[4] = lostArt;
		notifications[5] = closeAuc;
		
		notifications[0].getVbox().setOnMouseClicked(e-> openNewWindow(0));
		notifications[1].getVbox().setOnMouseClicked(e-> openNewWindow(1));
		notifications[2].getVbox().setOnMouseClicked(e-> openNewWindow(2));
		notifications[3].getVbox().setOnMouseClicked(e-> openNewWindow(3));
		notifications[4].getVbox().setOnMouseClicked(e-> openNewWindow(4));		
		notifications[5].getVbox().setOnMouseClicked(e-> openNewWindow(5));


		
	}
	
	public void openNewWindow(int i) {
		Scene scene;
		try {
			
			if(i==0) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("NotificationDisplay.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			if(i==1) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("NewBids.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
			if(i==3) {
				scene = new Scene(FXMLLoader.load(getClass().getResource("NewUsers.fxml")));
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
			}
			
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
	
	public void switchTab() {
		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/Searching.fxml"));
			//mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Updates getUpdate() {
		return update;
	}

	public void setUpdate(Updates update) {
		this.update = update;
	}
	
	
	

}
