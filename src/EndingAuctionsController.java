import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;

/**
 * 
 * A controller for displaying auctions coming to close
 * @author 869527
 *
 */
public class EndingAuctionsController {
	
	private final int DEFAULT = 5; // default number of remaining bids

	@FXML
	private FlowPane main; // a container to hold ending auctions

	@FXML
	private Label date; // a label that shows the last login of the user

	@FXML
	private Slider slider; // slider to choose a number of maximum remaining bids

	@FXML
	private Label value; // a label to display a value of a slider

	private int sliderValue; // to keep a value of a slider

	public void initialize() {

		// Displays a time of the last login
		date.setText(Updates.getDate() + "");
		
		// Set a value for a slider
		sliderValue = DEFAULT;
		
		// Returns a list of auctions with an arbitrary number or remaining bids
		ArrayList<Artwork> ending = Updates.endingAuctions(sliderValue);

		// A list of auctions
		ArrayList<AuctionView> listing = new ArrayList<>();

		
		// Loop through auctions and add them to a list of auction views
		for (Artwork a : ending) {
			AuctionView l = new AuctionView(a);
			listing.add(l);
		}

		// A listener to check value of a slider and refresh the screen 
		slider.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {

				sliderValue = (int) slider.getValue();
				value.setText(sliderValue + "");
				refresh();
			}
		});

		main.getChildren().addAll(listing);

	}

	/**
	 * A method to refresh a container with displayed auctions
	 */
	public void refresh() {
		
		// Array lists of artworks and views of them
		ArrayList<Artwork> ending = Updates.endingAuctions(sliderValue);
		ArrayList<AuctionView> listing = new ArrayList<>();

		// Ensure nothing is left in the container
		main.getChildren().clear();
		
		// Loop through auctions and add listings
		for (Artwork artwork : ending) {
			AuctionView l = new AuctionView(artwork);
			listing.add(l);
		}

		// Display all elements of the list
		main.getChildren().addAll(listing);

	}
}
