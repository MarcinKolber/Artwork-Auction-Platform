import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BidView extends VBox {

	private Bid bid;
	private ImageView imgView;
	
	
	public BidView (Bid bid1) {
		this.bid = bid1;
		imgView = new ImageView();
		Image img = bid.getArtwork().getImage();
		
		imgView.setImage(img);
		Label amount = new Label(bid.getAmount()+"");
		Label bidder = new Label(bid.getBidder().getUsername());
		Label time = new Label(bid.getBidDate()+"");
		Label remaining = new Label(bid.getArtwork().leftBids()+"");

		this.setSpacing(2);



		imgView.setFitWidth(150);
		imgView.setFitHeight(150);
		this.setPadding(new Insets(10, 10, 10, 10));
		getChildren().addAll(imgView, amount, bidder, time, remaining);


		setOnMouseEntered(e-> {this.setStyle("-fx-background-color: rgb(0,0, 20,0.1);");});
		setOnMouseExited(e-> {this.setStyle("-fx-background-color: rgb(0,0, 20,0);");});
		
		
	}
	
	
	public void displayInWindow() throws IOException {
		//ShowArtworkController.setArtwork(artwork);
		Scene scene;

		
		
		scene = new Scene(FXMLLoader.load(getClass().getResource("ShowArtwork.fxml")));
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	
	
	
	
	
}
