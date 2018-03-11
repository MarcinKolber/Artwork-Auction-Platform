import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Listing extends VBox {

	private Artwork artwork;
	private ImageView imgView;
	private Label description;

	private int width;

	public Listing(Artwork artwork) {
		width = 200;
		this.artwork = artwork;
		imgView = new ImageView();
		imgView.setFitWidth(150);
		imgView.setFitHeight(150);
		this.setPadding(new Insets(10, 10, 10, 10));

		description = new Label();

		imgView.setImage(artwork.getImage());
		Label title = new Label(artwork.getTitle());
		Label highestBid = new Label("Current bid: " + artwork.getHighestBidAmount());
		Label creator = new Label("Creator " + artwork.getCreator());
		Label year = new Label("Year: " + artwork.getCreationYear());
		Label date = new Label("Added: " + artwork.getDateAdded());
		Label remaining = new Label("Remaining bids: " + artwork.leftBids());
		this.setMinWidth(width);
		this.setSpacing(2);

		getChildren().addAll(imgView, title, highestBid, creator, year, date, remaining);

		setOnMouseClicked(e -> {
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

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

}
