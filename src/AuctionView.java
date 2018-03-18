import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A class to graphically represent an auction
 * @author 869527
 *
 */
public class AuctionView extends VBox {

	private Artwork artwork;	// artwork to be displayed
	private ImageView imgView;	// image (thumbnail) of the artwork
	private Label description;	// text with description of the auction
	private Label remaining;	// text with a number of remaining bids 
	private Label year;			// a year of creation
	private Label date;			// date of addition to the database
	private Label creator;		// creator of the artwork
	private Label highestBid;	// amount of the highest bid on the artwork
	private Label title;		// title of the artwork
	private Label ended;		// label that says whether an auction if active or finished 
	
	private final int IMG_WIDTH = 150;
	private final int IMG_HEIGHT = 150;
	private final int PADDING = 10;
	private final int VIEW_WIDTH = 200;
	private final int SPACING = 2;

	
	/**
	 * Constructor of a view for an auction
	 * @param artwork artwork that is on auction
	 */
	public AuctionView(Artwork artwork) {
		this.artwork = artwork; 	// sets an artwork
		imgView = new ImageView();	// creates an image view
		
		// Sets dimensions of the image
		imgView.setFitWidth(IMG_WIDTH);
		imgView.setFitHeight(IMG_HEIGHT);
		
		// Sets padding of the view
		this.setPadding(new Insets(PADDING));

		
		// Displays information about the auction 
		description = new Label();
		imgView.setImage(artwork.getImage());
		title = new Label("Title: " + artwork.getTitle());
		highestBid = new Label("Current bid: " + artwork.getHighestBidAmount());
		creator = new Label("Creator " + artwork.getCreator());
		year = new Label("Year: " + artwork.getCreationYear());
		date = new Label("Added: " + artwork.getDateAdded());
		remaining = new Label("Remaining bids: " + artwork.leftBids());
		description = new Label(artwork.getDescription());

		// Wrapping description
		description.setWrapText(true);

		// Set dimensions of the view
		this.setMinWidth(VIEW_WIDTH);
		this.setSpacing(SPACING);
		
		// Displays a different label depending on the status of the auction
		if (artwork.isBidIsOver()) {
			ended = new Label("ENDED");
			ended.setStyle("-fx-background-color: rgb(200,0, 20,0.5); ");

		} else {
			ended = new Label("ACTIVE");
			ended.setStyle("-fx-background-color: rgb(0,200, 20,0.5); ");

		}

		// Adds elements to the view
		getChildren().addAll(imgView, title, highestBid, creator, year, date, remaining, description, ended);

		// If clicked, open an auction in a new window
		setOnMouseClicked(e -> {
			try {
				displayInWindow();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		// Change color of the background when a mouse enters a field, remove color when exits
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



}
