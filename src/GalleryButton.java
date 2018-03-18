/**
 * Class for the gallery button in the custom gallery pop up window, used
 * for selecting and displaying the custom gallery
 * @author Ayden Ballard - 905438
 */

import java.util.Random;

import javafx.scene.control.Button;

public class GalleryButton extends Button {

	private CustomGallery customGallery;

	/**
	 * Constructor for gallery button
	 * @param gallery - Gallery linked to the button
	 */
	public GalleryButton(CustomGallery gallery) {
		Random rnd = new Random();
		customGallery = gallery;
		setText(customGallery.getName());
		setMinWidth(200);
		setMinHeight(100);
		int r = rnd.nextInt(255);
		int g = rnd.nextInt(100);
		int b = rnd.nextInt(255);
		setStyle("-fx-background-color: rgb(" + 0 + "," + g + "," + b + ",0.2); -fx-font-size: 1.5em; ");
	}

	/**
	 * Method to get the custom gallery that is linked to the button
	 * @return CustomGallery - the custom gallery
	 */
	public CustomGallery getCustomGallery() {
		return customGallery;
	}

	/**
	 * Method to set the custom gallery 
	 * @param customGallery
	 */
	public void setCustomGallery(CustomGallery customGallery) {
		this.customGallery = customGallery;
	}
	
	
	
	
	
	
}
