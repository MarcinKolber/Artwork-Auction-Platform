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
	private Artwork artwork;
	
	public BidView (Bid bid1) {
		this.bid = bid1;
		artwork = bid.getArtwork();
		imgView = new ImageView();
		Image img = bid.getArtwork().getImage();
		
		imgView.setImage(img);
		Label amount = new Label("Amount "+bid.getAmount()+"");
		Label bidder = new Label("Placed by "+ bid.getBidder().getUsername());
		Label time = new Label("Time "+ bid.getBidDate()+"");
		Label remaining = new Label("Remaining bids "+ bid.getArtwork().leftBids()+"");

		this.setSpacing(2);



		imgView.setFitWidth(150);
		imgView.setFitHeight(150);
		this.setPadding(new Insets(10, 10, 10, 10));
		getChildren().addAll(imgView, amount, bidder, time, remaining);


		setOnMouseEntered(e-> {this.setStyle("-fx-background-color: rgb(0,0, 20,0.1);");});
		setOnMouseExited(e-> {this.setStyle("-fx-background-color: rgb(0,0, 20,0);");});
		setOnMouseClicked(e-> {
			try {
				displayInWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
	
	
	public void displayInWindow() throws IOException {
		ShowArtworkController.setArtwork(artwork);
		Scene scene;

		
		
		scene = new Scene(FXMLLoader.load(getClass().getResource("ShowArtwork.fxml")));
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	
	
	
	
	
}
