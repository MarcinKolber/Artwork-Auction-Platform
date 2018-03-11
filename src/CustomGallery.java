import java.util.ArrayList;

public class CustomGallery {

	private User user;
	private String name;
	private ArrayList<Artwork> artwork;
	
	public CustomGallery(String name, User user) {
		this.name = name;
		this.user = user;
		artwork = new ArrayList<>();
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
	
	
	
	
	
}
