import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class NotificationController {


    @FXML
    private VBox one;

    @FXML
    private StackPane stackPane;

    @FXML
    private Canvas canvas1;

    @FXML
    private Label newArts;

    @FXML
    private VBox previouslyBidded;

    @FXML
    private StackPane stackPane1;

    @FXML
    private Canvas canBid;

    @FXML
    private Label prevNo;

    @FXML
    private VBox won;

    @FXML
    private StackPane stackPane11;

    @FXML
    private Canvas canWon;

    @FXML
    private Label wonNo;

    @FXML
    private VBox newUsers;

    @FXML
    private StackPane stackPane111;

    @FXML
    private Canvas newUsersCan;

    @FXML
    private Label newUsersCount;

    @FXML
    private VBox one1111;

    @FXML
    private StackPane stackPane1111;

    @FXML
    private Canvas can;

    @FXML
    private Label newArts1111;

    @FXML
    private VBox lostAuc;

    @FXML
    private StackPane stackPane11111;

    @FXML
    private Canvas lostCan;

    @FXML
    private Label lost;
    
    @FXML
    private VBox close;

    @FXML
    private Canvas closeCan;

    @FXML
    private Label closeLabel;
    
    public void initialize() {
    	Updates u = new Updates(LoginController.getUser());

    	NotificationField[] notifications = new NotificationField[6];
    	
    	int newArtsCount = u.getNewArtworks().size();
       	
    	newArts.setText(newArtsCount+"");
  
    	
    	
    	NotificationField newArt = new NotificationField(one, canvas1, newArts);
    	NotificationField bidded = new NotificationField(previouslyBidded, canBid, prevNo);
    	NotificationField wonAuctions = new NotificationField(won, canWon, wonNo);
    	NotificationField users = new NotificationField(newUsers, newUsersCan, newUsersCount);
    	NotificationField lostArt = new NotificationField(lostAuc, lostCan, lost);
    	NotificationField closeAuc = new NotificationField(close, closeCan, closeLabel);

    	notifications[0]= newArt;
    	notifications[1]= bidded;
    	notifications[2]= wonAuctions;
    	notifications[3]= users;
    	notifications[4]= lostArt;
    	notifications[5]= closeAuc;

    	
 
    }
    
    
	
}
