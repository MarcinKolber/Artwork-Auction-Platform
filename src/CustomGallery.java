import java.util.ArrayList;

/**
 * A class to represent a custom gallery made by an user to hold references to
 * artworks
 * 
 * @author 869527
 *
 */
public class CustomGallery {

	private User user; // creator of a custom gallery
	private String name; // name of the gallery
	private ArrayList<Artwork> artworks; // list of artworks contained in the gallery
	private String description; // description of a gallery
	private int numberOfPaintings;	// limit of paintings
	private int numberOfSculptures; // limit of sculptures
	private int currentPaintings; // number of paintings held in the gallery
	private int currentSculptures; // number of sculptures held in the gallery
	private String path;

	/**
	 * A constructor of a custom gallery
	 * 
	 * @param name name of the gallery
	 * @param user
	 * @param description
	 * @param paintings
	 * @param sculptures
	 */
	public CustomGallery(String name, User user, String description, int paintings, int sculptures) {
		this.name = name;
		this.user = user;
		this.currentPaintings = 0;
		this.currentSculptures = 0;
		this.artworks = new ArrayList<>();
		this.description = description;
		this.numberOfPaintings = paintings;
		this.numberOfSculptures = sculptures;
	}

	public void deleteArtwork(Artwork toDelete) {
		if (artworks.contains(toDelete)) {
			artworks.remove(toDelete);

			if (toDelete instanceof Sculpture) {
				currentSculptures--;
			} else if (toDelete instanceof Painting) {
				currentPaintings--;
			}
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Artwork> getArtwork() {
		return artworks;
	}

	public void setArtwork(ArrayList<Artwork> artwork) {
		this.artworks = artwork;
	}

	public String getTextFileOutput() {
		String output = name + "#" + user.getUsername() + "#" + description + "#" + numberOfPaintings + "#"
				+ numberOfSculptures + "#";

		return output;
	}

	public boolean addArtwork(Artwork artwork1) {
		if (!artworks.contains(artwork1)) {

			if (artwork1 instanceof Painting && currentPaintings < numberOfPaintings) {
				currentPaintings++;
				artworks.add(artwork1);
				return true;
			} else if (artwork1 instanceof Sculpture && currentSculptures < numberOfSculptures) {
				currentSculptures++;
				artworks.add(artwork1);
				return true;

			} else {
				return false;
			}

		}
		return false;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean hasArtwork(Artwork artwork1) {
		if (artworks.contains(artwork1)) {
			return true;
		} else {
			return false;
		}
	}

}
