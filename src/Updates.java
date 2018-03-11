import java.util.ArrayList;
import java.util.Date;

public class Updates {

	private static ArrayList<Artwork> newArtworks;
	private static ArrayList<Artwork> wonArtworks;
	private  static ArrayList<Artwork> lostArtworks;

	private  static ArrayList<Bid> newBids;
	private  static ArrayList<Artwork> ending;

	private static Date date;
	private static User user;

	public Updates(User user) {
		ending = new ArrayList<Artwork>();
		newArtworks = new ArrayList<Artwork>();
		newBids = new ArrayList<Bid>();
		date = user.getLastLogin();

		System.out.println("Last login: " + date);
		if (user.getLastLogin() != null) {

			addArtworks();
			addBids();

		}
	}

	public static void addArtworks() {

		ArrayList<Artwork> arts = FileReader.getArtworks();
		System.out.println(arts.size());
		for (Artwork a : arts) {
			if (a.getDateAdded().after(date)) {
				newArtworks.add(a);
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}

	}

	public void addBids() {

		ArrayList<Bid> bids = FileReader.getBids();

		for (Bid bid : bids) {
			System.out.println("title " + bid.getTitle());
			if (bid.getBidDate().after(date)) {
				newBids.add(bid);
			}
		}

	}

	public void addLost() {

		ArrayList<Bid> bids = FileReader.getBids();

		for (Bid b : bids) {

			Artwork a = b.getArtwork();
			if (a.isBidIsOver()) {

			}

		}

	}

	public static ArrayList<Artwork> endingAuctions(int i) {
		ending = new ArrayList<Artwork>();
		ArrayList<Bid> bids = LoginController.getUser().getPlacedBids();

		for (Bid b : bids) {
			System.out.println("not added");

			Artwork artwork = b.getArtwork();

			if (artwork.leftBids() < i) {
				if(!ending.contains(artwork)) {
					ending.add(artwork);

				}
				System.out.println("added");
			}

		}
		
		return ending;

	}

	public ArrayList<Artwork> getNewArtworks() {
		return newArtworks;
	}


	public ArrayList<Bid> getNewBids() {
		return newBids;
	}



	public ArrayList<Artwork> getEnding() {
		return ending;
	}


}
