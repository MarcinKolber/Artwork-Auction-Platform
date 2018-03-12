import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class NewBidController {

	@FXML
	private FlowPane main;

	private static ArrayList<Bid> bids = new ArrayList<>();
	private ArrayList<BidView> bidViews = new ArrayList<>();

	public void initialize() {

		for (Bid bid : bids) {

			BidView bv = new BidView(bid);
			bidViews.add(bv);

		}
		
		main.getChildren().addAll(bidViews);

	}

	public ArrayList<Bid> getBids() {
		return bids;
	}

	public static void setBids(ArrayList<Bid> bids1) {
		bids = bids1;
	}

}
