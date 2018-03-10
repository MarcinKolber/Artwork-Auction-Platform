import java.util.ArrayList;
import java.util.Date;

public class Updates {

	private ArrayList<Artwork> newArtworks;
	private ArrayList<Bid> newBids;

	private Date date;
	private User user;

	public Updates(User user) {
		newArtworks = new ArrayList<Artwork>();
		newBids = new ArrayList<Bid>();
		date = user.getLastLogin();

		System.out.println("Last login: " + date);
		if (user.getLastLogin() != null) {

			addArtworks();
			addBids();

		}
	}

	public void addArtworks() {

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

	public ArrayList<Artwork> getNewArtworks() {
		return newArtworks;
	}

	public void setNewArtworks(ArrayList<Artwork> newArtworks) {
		this.newArtworks = newArtworks;
	}

	public ArrayList<Bid> getNewBids() {
		return newBids;
	}

	public void setNewBids(ArrayList<Bid> newBids) {
		this.newBids = newBids;
	}

}
