import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

/**
 * A class to display notifications for new artworks
 * @author Joshua Thomas (907019)
 *
 */
public class NotificationDisplayController {


    @FXML
    private Label lastLogin;	// time of the last login

    @FXML
    private Label newArts;	// number of new artworks

    @FXML	
    private Label newSc;	// number of new sculptures

    @FXML
    private Label newPaint;	// number of new paintings

    @FXML
    private CheckBox sculpturesCheckbox;	// checkbox to select sculptures

    @FXML
    private CheckBox paintingCheckbox; // checkbox to select paintings
    
    @FXML
    private FlowPane main; // main container to display artworks
    
    // Values to keep a track of numbers of artworks of specific kind
    private int numberOfArtworks;
    private int numberOfSculptures;
    private int numberOfPaintings;


    // ArrayList to hold all artworks
    private static ArrayList<Artwork> arts = new ArrayList<>();
    
    
    /**
     * Initialises the GUI
     */
    public void initialize() {
    	
    	// Shows a time of last login
    	lastLogin.setText(Updates.getDate()+"");
    	
    	// Initial values
    	numberOfArtworks = 0;
    	numberOfSculptures = 0;
    	numberOfPaintings = 0;
    	
    	// Loops through all artworks
    	for(Artwork ar: arts) {
    		AuctionView l1 = new AuctionView(ar);
        	main.getChildren().add(l1);
        	numberOfArtworks++;
        	if(ar instanceof Painting) {
        		numberOfPaintings++;
        	} else if (ar instanceof Sculpture) {
        		numberOfSculptures++;
        	}
    	}

    	// Handles clicking on the checkbox and refreshes a window
    	paintingCheckbox.setOnAction(e-> {
    		filter();
    	});
    	
    	// Handles clicking on the checkbox and refreshes a window
    	sculpturesCheckbox.setOnAction(e-> {
    		filter();
    	});
    	
    	// Displays numbers as labels
    	newSc.setText(numberOfSculptures+"");
    	newPaint.setText(numberOfPaintings+"");
    	newArts.setText(numberOfArtworks+"");
    	
    }

    /**
     * Filters the content of the page
     */
    public void filter() {
    	main.getChildren().clear();
    	ArrayList<Artwork> display = new ArrayList<>();
    	
    	if(paintingCheckbox.isSelected()) {
    		for(Artwork a: arts) {
    			if(a instanceof Painting) {
    				display.add(a);
    			}
    		}
    	}
    	
    	if(sculpturesCheckbox.isSelected()) {
    		for(Artwork a: arts) {
    			if(a instanceof Sculpture) {
    				display.add(a);
    			}
    		}
    	}
    	
    	for(Artwork ar: display) {
    		AuctionView l1 = new AuctionView(ar);
        	main.getChildren().add(l1);
    	}
    	
    }
    
    /**
     * Returns a list of artworks
     * @return
     */
	public static ArrayList<Artwork> getArts() {
		return arts;
	}

	/**
	 * Sets a list of artworks
	 * @param arts1
	 */
	public static void setArts(ArrayList<Artwork> arts1) {
		arts = arts1;
	}

}