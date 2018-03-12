import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class EndingAuctionsController {

    @FXML
    private FlowPane main;


    @FXML
    private Label date;

    @FXML
    private Slider slider;

    @FXML
    private Label value;

    
    
    private int sliderValue;
    
    public void initialize() {
    
    	date.setText(Updates.getDate()+"");
    	sliderValue = 5;
    	ArrayList<Artwork> ending = Updates.endingAuctions(sliderValue);
    	
    	
    	ArrayList<Listing> listings = new ArrayList<>();
    	
    	for(Artwork a : ending) {
    		Listing l = new Listing(a);
    		listings.add(l);
    	}
    	
    	slider.valueProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<?> arg0, Object arg1, Object arg2) {
			
				sliderValue = (int) slider.getValue();
				value.setText(sliderValue+"");
				refresh();
			}
		});

    	
    	main.getChildren().addAll(listings);
    	//main.getChildren().add
    	
    	
    	
    }
    
    public void refresh() {
    	ArrayList<Artwork> ending = Updates.endingAuctions(sliderValue);
    	ArrayList<Listing> listings = new ArrayList<>();

    	main.getChildren().clear();
    	for(Artwork a : ending) {
    		Listing l = new Listing(a);
    		listings.add(l);
    	}

    	main.getChildren().addAll(listings);

    }
}
