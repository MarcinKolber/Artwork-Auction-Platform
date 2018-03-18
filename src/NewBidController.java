import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * A controller for displaying new bids on artworks 
 * @author 869527
 *
 */
public class NewBidController {

	@FXML
	private FlowPane main;	// container with artworks

    @FXML
    private Label date;		// time of the last login
	
    // Array lists 
	private static ArrayList<Bid> bids = new ArrayList<>();
	private ArrayList<BidView> bidViews = new ArrayList<>();

	/**
	 * Initialise the window
	 */
	public void initialize() {

		// Display time of the last login
		date.setText(Updates.getDate()+"");
		
		// Get bids and add them to a list of bid views
		for (Bid bid : bids) {
			BidView bv = new BidView(bid);
			bidViews.add(bv);
		}
		
		// Display new bids in a window
		main.getChildren().addAll(bidViews);

	}

	/**
	 * Returns a list of bids
	 * @return list of bids
	 */
	public ArrayList<Bid> getBids() {
		return bids;
	}

	/**
	 * Sets a list of bids
	 * @param bids1 list of bids
	 */
	public static void setBids(ArrayList<Bid> bids1) {
		bids = bids1;
	}

}
