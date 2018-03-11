import java.util.Random;

import javafx.scene.control.Button;

public class GalleryButton extends Button {

	private CustomGallery customGallery;
	
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

	public CustomGallery getCustomGallery() {
		return customGallery;
	}

	public void setCustomGallery(CustomGallery customGallery) {
		this.customGallery = customGallery;
	}
	
	
	
	
	
	
}
