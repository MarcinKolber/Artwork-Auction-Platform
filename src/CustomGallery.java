import java.util.ArrayList;

public class CustomGallery {

	private User user;
	private String name;
	private ArrayList<Artwork> artworks;
	private String description;
	private int numberOfPaintings;
	private int numberOfSculptures;
	private int currentPaintings;
	private int currentSculptures;
	private String path;

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
