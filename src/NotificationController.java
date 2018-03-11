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

	public void initialize() {
		Updates u = new Updates(LoginController.getUser());

		NotificationField[] notifications = new NotificationField[6];

		int newArtsCount = u.getNewArtworks().size();
		int newBidsCount = u.getNewBids().size();

		NotificationDisplayController.setArts(u.getNewArtworks());
		

		NotificationField newArt = new NotificationField(one, canvas1, newArts, newArtsCount);
		NotificationField bidded = new NotificationField(previouslyBidded, canBid, prevNo,newBidsCount);
		NotificationField wonAuctions = new NotificationField(won, canWon, wonNo);
		NotificationField users = new NotificationField(newUsers, newUsersCan, newUsersCount);
		NotificationField lostArt = new NotificationField(lostAuc, lostCan, lost);
		NotificationField closeAuc = new NotificationField(close, closeCan, closeLabel);
		

		newArts.setText(newArtsCount + "");
		prevNo.setText(newBidsCount +"");
		

		notifications[0] = newArt;
		notifications[1] = bidded;
		notifications[2] = wonAuctions;
		notifications[3] = users;
		notifications[4] = lostArt;
		notifications[5] = closeAuc;
		
		for(NotificationField n : notifications) {
			n.getVbox().setOnMouseClicked(e-> openNewWindow());
		}

		
	}
	
	public void openNewWindow() {
		Scene scene;
		try {
			scene = new Scene(FXMLLoader.load(getClass().getResource("NotificationDisplay.fxml")));
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
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
	

}
