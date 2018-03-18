import java.util.Random;

import javafx.scene.control.Button;

/**
 * A button to represent a gallery
 * 
 * @author 869527
 *
 */
public class GalleryButton extends Button {

	// Dimensions of a gallery button
	private final int WIDTH = 200;
	private final int HEIGHT = 100;

	// A reference to a gallery
	private CustomGallery customGallery;

	/**
	 * Constructor to set up a button
	 * 
	 * @param gallery
	 */
	public GalleryButton(CustomGallery gallery) {
		Random rnd = new Random();
		customGallery = gallery;
		setText(customGallery.getName());

		// Set the dimensions of the button
		setMinWidth(WIDTH);
		setMinHeight(HEIGHT);

		// Set color of the button
		int g = rnd.nextInt(100);
		int b = rnd.nextInt(255);
		setStyle("-fx-background-color: rgb(" + 0 + "," + g + "," + b + ",0.2); -fx-font-size: 1.5em; ");

	}

	/**
	 * Return a gallery
	 * 
	 * @return custom gallery referenced by a button
	 */
	public CustomGallery getCustomGallery() {
		return customGallery;
	}

	/**
	 * Set a gallery
	 * 
	 * @param customGallery
	 *            gallery to be referenced by a button
	 */
	public void setCustomGallery(CustomGallery customGallery) {
		this.customGallery = customGallery;
	}

}
