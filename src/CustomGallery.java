import java.util.ArrayList;

/**
 * A class to represent a custom gallery made by an user to hold references to
 * artworks
 * @author Ayden Ballard - 905438
 *
 */
public class CustomGallery {

	private User user; // creator of a custom gallery
	private String name; // name of the gallery
	private ArrayList<Artwork> artworks; // list of artworks contained in the gallery
	private String description; // description of a gallery
	private int numberOfPaintings; // limit of paintings
	private int numberOfSculptures; // limit of sculptures
	private int currentPaintings; // number of paintings held in the gallery
	private int currentSculptures; // number of sculptures held in the gallery
	private String path; //file path of the custom gallery

	/**
	 * A constructor of a custom gallery
	 * @param name - name of the gallery
	 * @param user - user that has the gallery
	 * @param description - description of the gallery
	 * @param paintings - number of paintings allowed
	 * @param sculptures - number of sculptures allowed
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

	/**
	 * Deletes an artwork from a gallery
	 * @param toDelete - artwork to be deleted
	 */
	public void deleteArtwork(Artwork toDelete) {
		if (artworks.contains(toDelete)){
			// Remove an artwork from an array list
			artworks.remove(toDelete);
			// Decrement a corresponding counter
			if (toDelete instanceof Sculpture){
				currentSculptures--;
			}
			else if(toDelete instanceof Painting){
				currentPaintings--;
			}
		}

	}

	/**
	 * Adds artwork to the custom gallery
	 * @param artwork - artwork to be added
	 * @return confirmation of success or failure of the addition
	 */
	public boolean addArtwork(Artwork artwork) {
		// Checks if an artwork is already in the gallery
		if (!artworks.contains(artwork)) {
			// If limit has not been reached, add an artwork to a gallery and increment a
			// corresponding counter
			if(artwork instanceof Painting && currentPaintings < numberOfPaintings){
				currentPaintings++;
				artworks.add(artwork);
				return true;
			}
			else if(artwork instanceof Sculpture && currentSculptures < numberOfSculptures){
				currentSculptures++;
				artworks.add(artwork);
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	/**
	 * Returns a creator of the gallery
	 * @return user that created the gallery
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets an owner of the gallery
	 * @param user - owner of the gallery
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Returns a name of the gallery
	 * @return name of the gallery
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets a name of the gallery
	 * @param name - name of the gallery
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a list of artworks
	 * @return list of artworks in a gallery
	 */
	public ArrayList<Artwork> getArtworks() {
		return artworks;
	}

	/**
	 * Sets a list of artworks in a gallery
	 * @param artwork
	 */
	public void setArtworks(ArrayList<Artwork> artwork) {
		this.artworks = artwork;
	}

	/**
	 * Returns a string used when creating a gallery
	 * @return string to be saved in a file
	 */
	public String getTextFileOutput() {
		String output = name + "#" + user.getUsername() + "#" + description + "#" + numberOfPaintings + "#"
				+ numberOfSculptures + "#";
		return output;
	}

	/**
	 * Returns a path to a text file 
	 * @return path to the file with saved gallery
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets a path to a text file with a gallery
	 * @param path path to a text file
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Checks if an artwork is already in the gallery
	 * @param artwork artwork to be checked for
	 * @return true if an artwork has been in the gallery, false otherwise
	 */
	public boolean hasArtwork(Artwork artwork) {
		if(artworks.contains(artwork)){
			return true;
		}
		else{
			return false;
		}
	}

}
