import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class NotificationDisplayController {


    @FXML
    private Label lastLogin;

    @FXML
    private Label newArts;

    @FXML
    private Label newSc;

    @FXML
    private Label newPaint;

    @FXML
    private CheckBox sculpturesCheckbox;

    @FXML
    private CheckBox paintingCheckbox;

    @FXML
    private FlowPane main;
    private static ArrayList<Artwork> arts = new ArrayList<>();
    
    public void initialize() {
    	lastLogin.setText(Updates.getDate()+"");

    	for(Artwork ar: arts) {
        	Listing l1 = new Listing(ar);
        	main.getChildren().add(l1);
    	}

    	paintingCheckbox.setOnAction(e-> {
    		filter();
    	});
    	
    	sculpturesCheckbox.setOnAction(e-> {
    		filter();
    	});
    }

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
        	Listing l1 = new Listing(ar);
        	main.getChildren().add(l1);
    	}
    	
    }
    
	public static ArrayList<Artwork> getArts() {
		return arts;
	}

	public static void setArts(ArrayList<Artwork> arts1) {
		arts = arts1;
	}

}