import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * This class is used to display an Artwork object.
 */
public class ShowArtworkController {

	@FXML
	private BorderPane mainSection; //Panel for display

	@FXML
	private ImageView mainPic; //Holder for an image

	@FXML
	private ImageView pic1; //Holder for an image

	@FXML
	private ImageView pic2; //Holder for an image

	@FXML
	private ImageView pic3; //Holder for an image

	@FXML
	private ImageView pic4; //Holder for an image

	@FXML
	private Label categoryA; //Holder for text

	@FXML
	private Label titleA; //Holder for text

	@FXML 
	private Label creatorA; //Holder for text

	@FXML
	private Label yearA; //Holder for text

	@FXML
	private Label noOfBidsA; //Holder for text

	@FXML
	private Label bidsLimitA; //Holder for text

	@FXML
	private Label postcode1; //Holder for text

	@FXML
	private Label postcode2; //Holder for text

	@FXML
	private Label widthA; //Holder for text

	@FXML
	private Label heightA; //Holder for text

	@FXML
	private Label depthA; //Holder for text

	@FXML
	private Label materialA; //Holder for text

	@FXML
	private Label description; //Holder for text

	@FXML
	private ChoiceBox<String> track; //NOT SURE

	@FXML
	private Button addToCustomGallery; //Allows user to add artwork to custom galleries

	@FXML
	private Label currentPrice; //Holder for text

	@FXML
	private Label sellerA; //Holder for text

	@FXML
	private Button addToFav; //Allows user to add to favourite list

	@FXML
	private ImageView sellerAvatar; //Holder for image

	@FXML
	private TextField bidAmount; //Allows input for a bid amount

	@FXML
	private Button placeBid; //Allows user to place bids

	@FXML
	private Label noOfBids; //Holder for text

	private static Artwork artwork; //Artwork object in question

	/**
	 * Method to initialize the GUI.
	 */
	public void initialize() {

		// Get the owner of sculpture
		User owner = artwork.getOwner();
		Image image;

		// Display the owner avatar
		image = owner.getImage();
		sellerAvatar.setImage(image);

		// Display the owner's username
		sellerA.setText(artwork.getOwner().getUsername());

		// Sets all information about the painting
		titleA.setText(artwork.getTitle());

		if (artwork instanceof Sculpture) {
			widthA.setText(((Sculpture) artwork).getWidth() + "");
			heightA.setText(((Sculpture) artwork).getHeight() + "");
			heightA.setText(((Sculpture) artwork).getHeight() + "");
			depthA.setText(((Sculpture) artwork).getDepth() + "");
			materialA.setText(((Sculpture) artwork).getMaterial());

			artwork.resolveImage();
			((Sculpture) artwork).resolveAdditionalImages();

			// Adds images into an ArrayList
			ArrayList<Image> additionalImages = ((Sculpture) artwork).getAdditionalImages();
			ArrayList<ImageView> imageViews = new ArrayList<>();

			// Adds the images for display
			imageViews.add(pic1);
			imageViews.add(pic2);
			imageViews.add(pic3);
			imageViews.add(pic4);

			for (int i = 0; i < additionalImages.size(); i++) {
				imageViews.get(i).setImage((additionalImages.get(i)));
			}
		}

		if (artwork instanceof Painting) {
			widthA.setText(((Painting) artwork).getWidth() + "");
			heightA.setText(((Painting) artwork).getHeight() + "");

		}
		//Displays information
		yearA.setText(artwork.getCreationYear() + "");
		creatorA.setText(artwork.getCreator());
		noOfBidsA.setText(artwork.getNumberOfPlacedBids() + "");
		bidsLimitA.setText(artwork.getBidsAllowed() + "");
		mainPic.setImage(artwork.getImage());
		sellerA.setText(artwork.getOwner().getUsername());
		description.setText(artwork.getDescription());

		ObservableList<String> options = FXCollections
				.observableArrayList(LoginController.getUser().customGalleriesToString());
		track.setItems(options);

		if (artwork.getNumberOfBids() > 0) {
			currentPrice.setText(artwork.getHighestBidAmount() + "");
		} else { // Otherwise, display the reserve price
			currentPrice.setText(artwork.getReservePrice() + "");
		}
		//Action handlers
		addToFav.setOnAction(e -> showUser());
		placeBid.setOnAction(e -> addBid());

		addToCustomGallery.setOnAction(e -> addToCustomGallery());

	}

	/**
	 * Method to create a custom gallery and artwork to it.
	 */
	public void addToCustomGallery() {

		CustomGallery cg = LoginController.getUser().getCustomGallery(track.getValue()); //New gallery

		if (cg.hasArtwork(artwork)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("An artwork is already in the gallery");
			alert.showAndWait(); //Checks if the image is already in the gallery
		
		} else {
			Writer.addArtworkToGallery(LoginController.getUser(), artwork, cg);

			boolean success = LoginController.getUser().getCustomGallery(track.getValue()).addArtwork(artwork);
			//Adds the artwork if it isn't already in the gallery
			if(success) {
				
				Writer.addArtworkToGallery(LoginController.getUser(), artwork,
						LoginController.getUser().getCustomGallery(track.getValue())); //Saves gallery addition to memory

				LoginController.getUser().getCustomGallery(track.getValue()).addArtwork(artwork);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Added");
				alert.setHeaderText("Added to a custom gallery");
				alert.setContentText("Thank you!");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Limit has been reached");
				alert.showAndWait(); //Error checking and warning
			}

		
		}



	}

	/**
	 * Method to show the information of a selected user.
	 */
	public void showUser() {

		User user = artwork.getOwner();

		// Passes the reference to the owner to the window displaying user
		UserDisplayController.setUser(user);

		// Loads the window for a user
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));

		// Try to display the user
		try {
			Parent root = fxmlL.load(); //New window for user information

			Scene scene = new Scene(root, 450, 300);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle(user.getUsername());
			stage.show();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}
	}

	/**
	 * Method to get an artwork object.
	 * @return Artwork - the artwork object.
	 */
	public static Artwork getArtwork() {
		return artwork;
	}

	/**
	 * Method to set an artwork.
	 * @param Artwork - the artwork object.
	 */
	public static void setArtwork(Artwork artwork) {
		ShowArtworkController.artwork = artwork;
	}

	/**
	 * Method to add a bid to an artwork in the window.
	 */
	public void addBid() {

		String type = "";
		Bid bid = null;

		if (artwork instanceof Painting) {
			type = "painting";

		} else {
			type = "sculpture";
		}
		try {
			String amountStr = bidAmount.getText();
			double amount = Double.parseDouble(amountStr);
			Date date = new Date();

			// Check if bid can be allowed
			if (artwork.getBidsAllowed() > artwork.getNumberOfPlacedBids() && amount > artwork.getReservePrice()
					&& amount > artwork.getHighestBidAmount()
					&& !LoginController.getUser().getUsername().equals(artwork.getCurrentHighestBidder())
					&& LoginController.getUser() != artwork.getOwner()) {

				// Inform user about the successful bid
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Bid has been placed");
				alert.setContentText("Thank you!");

				// Initialises a new bid and adds it to memory
				bid = new Bid(type, LoginController.getUser(), amount, artwork, date);
				LoginController.getUser().addBid(bid);
				artwork.addBidToItem(bid);
				FileReader.addBid(bid);

				// Save to memory
				try {
					Writer.writeBidFile(bid);
				} catch (IOException e) {
					e.printStackTrace();
				}
				alert.showAndWait();

			} else { // Error message if bid was not accepted
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Something went wrong");
				alert.setHeaderText("Bid has not been placed");
				alert.setContentText("Try again");
				alert.showAndWait();
			}
		} catch (NumberFormatException e) { // show an error message for wrong input
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong input");
			alert.setHeaderText("Bid could not be placed");
			alert.showAndWait();

		}

	}

}
