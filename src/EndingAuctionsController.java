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
	
	private final int DEFAULT = 5;

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

		// 
		ArrayList<Listing> listings = new ArrayList<>();

		for (Artwork a : ending) {
			Listing l = new Listing(a);
			listings.add(l);
		}

		slider.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {

				sliderValue = (int) slider.getValue();
				value.setText(sliderValue + "");
				refresh();
			}
		});

		main.getChildren().addAll(listings);

	}

	public void refresh() {
		ArrayList<Artwork> ending = Updates.endingAuctions(sliderValue);
		ArrayList<Listing> listings = new ArrayList<>();

		main.getChildren().clear();
		for (Artwork a : ending) {
			Listing l = new Listing(a);
			listings.add(l);
		}

		main.getChildren().addAll(listings);

	}
}
