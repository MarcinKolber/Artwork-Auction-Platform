import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class extending VBox to display a bid
 * 
 * @author Marcin Kolber (869527)
 *
 */
public class BidView extends VBox {

	private Bid bid; // a reference to a bid
	private ImageView imgView; // view of an image of an artwork
	private Artwork artwork; // artwork that is bidded on
	private final int SPACING = 2;
	private final int WIDTH = 150;
	private final int HEIGHT = 150;
	private final int PADDING = 10;

	/**
	 * Constructor of a view of a bid
	 * 
	 * @param bid bid to be displayed
	 */
	public BidView(Bid bid) {
		// Retrieving data from a bid reference
		this.bid = bid;
		this.artwork = bid.getArtwork();
		this.imgView = new ImageView();

		// Getting an image of an artwork
		Image img = bid.getArtwork().getImage();

		// Setting an image for display
		imgView.setImage(img);

		// Adding labels to display data about a bid and the artwork
		Label amount = new Label("Amount " + bid.getAmount() + "");
		Label bidder = new Label("Placed by " + bid.getBidder().getUsername());
		Label time = new Label("Time " + bid.getBidDate() + "");
		Label remaining = new Label("Remaining bids " + bid.getArtwork().leftBids() + "");

		// Sets spacing between elements
		this.setSpacing(SPACING);

		// Setting dimensions
		imgView.setFitWidth(WIDTH);
		imgView.setFitHeight(HEIGHT);

		// Sets padding of a bid view
		this.setPadding(new Insets(PADDING));

		// Adds image and labels to a view
		getChildren().addAll(imgView, amount, bidder, time, remaining);

		// Change color when mouse enters a field with a bid
		setOnMouseEntered(e -> {
			this.setStyle("-fx-background-color: rgb(0,0, 20,0.1);");
		});

		// Removes a background color when mouse exits the field
		setOnMouseExited(e -> {
			this.setStyle("-fx-background-color: rgb(0,0, 20,0);");
		});

		// When clicked, open a view with an artwork in a new window
		setOnMouseClicked(e -> {
			try {
				displayInWindow();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

	}

	/**
	 * A method to display an artwork in a new window
	 * 
	 * @throws IOException if a FXML file is not found
	 */
	public void displayInWindow() throws IOException {

		/*
		 * Passing a reference of artwork to a controller handling displaying the
		 * artwork
		 */
		ShowArtworkController.setArtwork(artwork);
		
		// Displaying a window with artwork in a new window
		Scene scene;
		scene = new Scene(FXMLLoader.load(getClass().getResource("ShowArtwork.fxml")));
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

}
