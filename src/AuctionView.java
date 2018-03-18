import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AuctionView extends VBox {

	private Artwork artwork;
	private ImageView imgView;
	private Label description;
	private Label remaining;
	private Label year;
	private Label date;
	private Label creator;
	private Label highestBid;
	private Label title;
	private Label ended;
	private int width;

	public AuctionView(Artwork artwork) {
		width = 200;
		this.artwork = artwork;
		imgView = new ImageView();
		imgView.setFitWidth(150);
		imgView.setFitHeight(150);
		this.setPadding(new Insets(10, 10, 10, 10));

		description = new Label();

		imgView.setImage(artwork.getImage());
		title = new Label("Title: " + artwork.getTitle());
		highestBid = new Label("Current bid: " + artwork.getHighestBidAmount());
		creator = new Label("Creator " + artwork.getCreator());
		year = new Label("Year: " + artwork.getCreationYear());
		date = new Label("Added: " + artwork.getDateAdded());
		remaining = new Label("Remaining bids: " + artwork.leftBids());
		description = new Label(artwork.getDescription());

		description.setWrapText(true);

		this.setMinWidth(width);
		this.setSpacing(2);
		if (artwork.isBidIsOver()) {
			ended = new Label("ENDED");
			ended.setStyle("-fx-background-color: rgb(200,0, 20,0.5); ");

		} else {
			ended = new Label("ACTIVE");
			ended.setStyle("-fx-background-color: rgb(0,200, 20,0.5); ");

		}

		getChildren().addAll(imgView, title, highestBid, creator, year, date, remaining, description, ended);

		setOnMouseClicked(e -> {
			try {
				displayInWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		setOnMouseEntered(e -> {
			this.setStyle("-fx-background-color: rgb(0,0, 20,0.1);");
		});
		setOnMouseExited(e -> {
			this.setStyle("-fx-background-color: rgb(0,0, 20,0);");
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

	public ImageView getImgView() {
		return imgView;
	}

	public void setImgView(ImageView imgView) {
		this.imgView = imgView;
	}

	public Label getDescription() {
		return description;
	}

	public void setDescription(Label description) {
		this.description = description;
	}

	public Label getRemaining() {
		return remaining;
	}

	public void setRemaining(Label remaining) {
		this.remaining = remaining;
	}

	public Label getYear() {
		return year;
	}

	public void setYear(Label year) {
		this.year = year;
	}

	public Label getDate() {
		return date;
	}

	public void setDate(Label date) {
		this.date = date;
	}

	public Label getCreator() {
		return creator;
	}

	public void setCreator(Label creator) {
		this.creator = creator;
	}

	public Label getHighestBid() {
		return highestBid;
	}

	public void setHighestBid(Label highestBid) {
		this.highestBid = highestBid;
	}

	public Label getTitle() {
		return title;
	}

	public void setTitle(Label title) {
		this.title = title;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
