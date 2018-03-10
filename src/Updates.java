import java.util.ArrayList;
import java.util.Date;

public class Updates {

	private ArrayList<Artwork> newArtworks;
	private ArrayList<Bid> newBids;

	private Date date;
	private User user;

	public Updates(User user) {


		date = user.getLastLogin();

		if (user.getLastLogin() != null) {

			addArtworks();
			addBids();

		}
	}

	public void addArtworks() {

		System.out.println("started");
		ArrayList<Artwork> arts = FileReader.getArtworks();
		
		for(Artwork a: arts) {
			if(a.getDateAdded().after(date) ) {
				System.out.println(a.getTitle());
			}
		}
		
		
	}

	public void addBids() {

	}

}
