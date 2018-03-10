import java.util.ArrayList;
import java.util.Date;

public class Updates {

	private ArrayList<Artwork> newArtworks;
	private ArrayList<Bid> newBids;

	private Date date;
	private User user;

	public Updates(User user) {
		newArtworks = new ArrayList<Artwork>();

		date = user.getLastLogin();

		if (user.getLastLogin() != null) {

			addArtworks();
			addBids();

		}
	}

	public void addArtworks() {

		ArrayList<Artwork> arts = FileReader.getArtworks();
		System.out.println(arts.size());
		for(Artwork a: arts) {
			System.out.println(a.getTitle());
			if(a.getDateAdded().after(date) ) {
				newArtworks.add(a);
				System.out.println(true);
			} else {
				System.out.println(false);
			}
		}
		
		
	}

	public void addBids() {

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
