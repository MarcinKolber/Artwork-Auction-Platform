import java.util.ArrayList;

public class CustomGallery {

	private User user;
	private String name;
	private ArrayList<Artwork> artwork;
	private String description;
	private int numberOfPaintings;
	private int numberOfSculptures;
	
	public CustomGallery(String name, User user, String description, int paintings, int sculptures) {
		this.name = name;
		this.user = user;
		artwork = new ArrayList<>();
		this.description = description;
		this.numberOfPaintings = paintings;
		this.numberOfSculptures = sculptures;
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
		return artwork;
	}

	public void setArtwork(ArrayList<Artwork> artwork) {
		this.artwork = artwork;
	}
	
	public String getTextFileOutput() {
		String output = name+"#"+user.getUsername() +"#"+description+"#"+numberOfPaintings+"#"+numberOfSculptures+"#";
		
		return output;
	}
	
	public void addArtwork(Artwork artwork1) {
		if(!artwork.contains(artwork1)) {
			artwork.add(artwork1);

		}
	}
	
	
}
