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

	/**
	 * Method to create updates for the user
	 * @param user1 - current logged in user
	 */
	public Updates(User user1) {
		user = user1;
		//creating the ArrayLists
		ending = new ArrayList<Artwork>();
		newArtworks = new ArrayList<Artwork>();
		newBids = new ArrayList<Bid>();
		//getting the date the user last logged in
		date = user.getLastLogin();

		System.out.println("Last login: " + date); //printing when the user last logged in
		if (user.getLastLogin() != null) { //if the user has logged in before (not null)
			addArtworks();
			addBids();
			addUsers();
			newBids();
		}
	}

	/**
	 * Method to add new bids to the newBids ArrayList
	 * @return ArrayList of new bids
	 */
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

	/**
	 * Method to add new users to the users ArrayList
	 */
	public static void addUsers() {
		users = new ArrayList<>();

		for (User user : FileReader.getUsers()) {
			if (user.getAccountCreationDate().after(date)) {
				users.add(user);
			}
		}

	}

	/**
	 * Method to add new artworks to the newArtworks ArrayList
	 */
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

	/**
	 * Method to add new bids to the newBids ArrayList
	 */
	public void addBids() {
		ArrayList<Bid> bids = FileReader.getBids();
		for (Bid bid : bids) {
			System.out.println("title " + bid.getTitle());
			if (bid.getBidDate().after(date)) {
				newBids.add(bid);
			}
		}

	}

	/**
	 * Method to add soon ending auctions to the ending ArrayList
	 * @param i - amount of bids to go
	 * @return ArrayList of soon ending auctions
	 */
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

	/**
	 * Method to add lost artworks to the lost artworks ArrayList
	 * @return ArrayList of lost auctions
	 */
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

	/**
	 * Method to add won artworks to the wonArtworks ArrayList
	 * @return ArrayList of won auctions
	 */
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

	/**
	 * Method to get the new Artworks
	 * @return ArrayList of new artworks
	 */
	public ArrayList<Artwork> getNewArtworks() {
		return newArtworks;
	}

	/**
	 * Method to get the new bids
	 * @return ArrayList of new bids
	 */
	public ArrayList<Bid> getNewBids() {
		return newBids;
	}

	/**
	 * Method to get ending auctions
	 * @return ArrayList of ending auctions
	 */
	public ArrayList<Artwork> getEnding() {
		return ending;
	}

	/**
	 * Method to get the current user
	 * @return user
	 */
	public static User getUser() {
		return user;
	}

	/**
	 * Method to set the user
	 * @param user
	 */
	public static void setUser(User user) {
		Updates.user = user;
	}

	/**
	 * Method to return the ArrayList of all users
	 * @return ArrayList of users
	 */
	public static ArrayList<User> getUsers() {
		return users;
	}

	/**
	 * Method to set the users ArrayList
	 * @param users - ArrayList of users
	 */
	public static void setUsers(ArrayList<User> users) {
		Updates.users = users;
	}

	/**
	 * Method to get the last log in date
	 * @return
	 */
	public static Date getDate() {
		return date;
	}

	/**
	 * Method to set the last log in date
	 * @param date
	 */
	public static void setDate(Date date) {
		Updates.date = date;
	}

}
