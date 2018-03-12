import java.util.ArrayList;
import java.util.Date;

public class Updates {

	private static ArrayList<Artwork> newArtworks;
	private static ArrayList<Artwork> wonArtworks;
	private static ArrayList<Artwork> lostArtworks;

	private static ArrayList<Bid> newBids;
	private static ArrayList<Artwork> ending;
	private static ArrayList<User> users;

	private static Date date;
	private static User user;

	public Updates(User user1) {
		user = user1;
		ending = new ArrayList<Artwork>();
		newArtworks = new ArrayList<Artwork>();
		newBids = new ArrayList<Bid>();
		date = user.getLastLogin();

		System.out.println("Last login: " + date);
		if (user.getLastLogin() != null) {

			addArtworks();
			addBids();
			addUsers();
			newBids();
		}
	}

	public static ArrayList<Bid> newBids() {
		ArrayList<Bid> newBids = new ArrayList<>();

		ArrayList<Bid> allBids = FileReader.getBids();
		System.out.println(user.getFullName());
		ArrayList<Bid> userBids = user.getPlacedBids();
		System.out.println(userBids.size() + "asdasdasd");

		for (Bid bid : allBids) {

			System.out.println(bid.getBidDate() + "dsaas");

			Artwork a = bid.getArtwork();

			if (user.getBiddedArtworks().contains(a) && bid.getBidDate().after(date) && bid.getBidder() != user) {
				System.out.println("asddddd");

				newBids.add(bid);

			}

		}

		return newBids;
	}

	public static void addUsers() {
		users = new ArrayList<>();

		for (User user : FileReader.getUsers()) {
			if (user.getAccountCreationDate().after(date)) {
				users.add(user);
			}
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
				if (!ending.contains(artwork)) {
					ending.add(artwork);

				}
				System.out.println("added");
			}

		}

		return ending;

	}

	public static ArrayList<Artwork> lost() {
		ArrayList<Artwork> lostArtworks = new ArrayList<>();

		ArrayList<Artwork> biddedArtworks = LoginController.getUser().getBiddedArtworks();

		for (Artwork artwork : biddedArtworks) {

			if(artwork.isBidIsOver() && !artwork.getWinnerName().equals(LoginController.getUser().getUsername())) {
				lostArtworks.add(artwork);
			}
		}

		return lostArtworks;
	}
	
	
	public static ArrayList<Artwork> won() {
		ArrayList<Artwork> wonArtworks = new ArrayList<>();

		ArrayList<Artwork> biddedArtworks = LoginController.getUser().getBiddedArtworks();

		for (Artwork artwork : biddedArtworks) {

			if(artwork.isBidIsOver() && artwork.getWinnerName().equals(LoginController.getUser().getUsername())) {
				wonArtworks.add(artwork);
			}
		}

		return wonArtworks;
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

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Updates.user = user;
	}

	public static ArrayList<User> getUsers() {
		return users;
	}

	public static void setUsers(ArrayList<User> users) {
		Updates.users = users;
	}

	public static Date getDate() {
		return date;
	}

	public static void setDate(Date date) {
		Updates.date = date;
	}

}
