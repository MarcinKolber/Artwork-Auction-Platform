import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This is the main GUI class which links to all other GUI classes
 * 
 * @author Marcin Created on 22/11/2017
 */
public class GUIController {

	@FXML
	private VBox updates;

	@FXML
	private Label nickname;

	@FXML
	private ListView<String> searchList; // A list of searched items

	@FXML
	private Hyperlink dashboardLink; // Hyperlink for navigation

	@FXML
	private Hyperlink myAuctionsLink; // Hyperlink for navigation

	@FXML
	private Hyperlink myBidsLink; // Hyperlink for navigation

	@FXML
	private Hyperlink userSettingsLink; // Hyperlink for navigation

	@FXML
	private Button createNewArtworkButton; // Button for creating new Artwork

	@FXML
	private Hyperlink favouriteUsers; // Hyperlink for navigation

	@FXML
	private Label username; // Shows a users username
	@FXML
	private Label firstName; // Shows a users first name

	@FXML
	private Label lastName; // Shows a users last name

	@FXML
	private Label fullName; // Shows a users full name

	@FXML
	private Label address; // Shows a users address

	@FXML
	private Label postcode; // Shows a users postcode

	@FXML
	private Label lastLogin; // Shows a users last log in time

	@FXML
	private Label phoneNumber; // Shows a users phone number

	@FXML
	private BorderPane mainSection; // The main window

	@FXML
	private RadioButton artworkSelect; // Button to filter artwork

	@FXML
	private RadioButton userSelect; // Button to filter user

	@FXML
	private CheckBox sculptureSelect; // Checkbox to filter sculptures

	@FXML
	private CheckBox paintingSelect; // Checkbox to filter paintings

	private ObservableList<String> observableList; // A list of strings

	@FXML
	private Button searchButton; // Button to implement a search/filter

	@FXML
	private TextField searching; // allows search input

	@FXML
	private Button display; // Displays an item

	@FXML
	private Label user1; // Shows user1

	@FXML
	private Label today; // Shows the date

	@FXML
	private ImageView avatar;

	@FXML
	private Hyperlink mainPage;

	@FXML
	private Hyperlink sales;

	@FXML
	private Hyperlink searchingB;

	@FXML
	private Button logOut;

	@FXML
	private Button advanced;

	@FXML
	private Hyperlink customGalleries;

	@FXML
	private HBox notificationBox;

	@FXML
	private HBox searchingBox;

	@FXML
	private HBox salesBox;

	@FXML
	private HBox galleriesBox;

	private ArrayList<HBox> hBoxes;

	private String searchingContent;

	/**
	 * Initialises the main elements of GUI
	 */
	@SuppressWarnings("deprecation")
	public void initialize() {

		hBoxes = new ArrayList<>();
		hBoxes.add(notificationBox);
		hBoxes.add(searchingBox);
		hBoxes.add(salesBox);
		hBoxes.add(galleriesBox);

		for (HBox box : hBoxes) {
			box.setOnMouseEntered(e -> box.setStyle("fx-background-color: rgb(126, 40, 61, 0.9);"));
			box.setOnMouseExited(e -> box.setStyle("-fx-background-color: rgb(26, 40, 61, 0.1);"));

		}

		notificationBox.setOnMouseClicked(e ->  openMainTab());
		searchingBox.setOnMouseClicked(e -> advancedSearching());
		salesBox.setOnMouseClicked(e -> openSalesTab());
		galleriesBox.setOnMouseClicked(e -> openGalleries());

		avatar.setImage(LoginController.getUser().getImage());
		paintingSelect.setSelected(true);
		sculptureSelect.setSelected(true);
		artworkSelect.setSelected(true);

		mainPage.setOnAction(e -> openMainTab());

		ToggleGroup tg = new ToggleGroup();
		userSelect.setToggleGroup(tg);
		artworkSelect.setToggleGroup(tg);

		user1.setText("Nice to see you " + LoginController.getUser().getFullName());
		LocalDate date = LocalDate.now();
		today.setText("Today is " + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear());
		userSettingsLink.setOnAction(e -> userSettings());

		createNewArtworkButton.setOnAction(e -> createNewArtwork());
		favouriteUsers.setOnAction(e -> userSettings1());
		myAuctionsLink.setOnAction(e -> showMyAuctions());

		display.setOnAction(e -> getSearchSelection());

		myBidsLink.setOnAction(e -> displayMyBids());
		dashboardLink.setOnAction(e -> displayMainDashboard());

		nickname.setText("  " + LoginController.getUser().getUsername());
		searchingB.setOnAction(e -> openSearchTab());
		sales.setOnAction(e -> openSalesTab());
		searchButton.setOnAction(e -> handleSearch());

		avatar.setOnMouseClicked(e -> userSettings());

		logOut.setOnAction(e -> exit());

		customGalleries.setOnAction(e -> openGalleries());
		advanced.setOnAction(e -> advancedSearching());

		searching.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				String input = searching.getText();
				searchingContent = input;
				handleSearch();

			}

		});
	}

	public void changeBoxesColor() {

	}

	public void advancedSearching() {
		BorderPane bp; // Border Pane to load the new BorderPane in

		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/Searching.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openGalleries() {
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/Gallery.fxml"));

		// Try to display the user
		try {
			Parent root = fxmlL.load();

			Scene scene = new Scene(root, 1000, 600);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();

		} catch (IOException e) { // catch an exception if file cannot be loaded
			e.printStackTrace();
		}
	}

	public void exit() {
		System.exit(0);
	}

	/**
	 * Method to display a tab for sales
	 */
	public void openSalesTab() {
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/Sales.fxml"));
			mainSection.getChildren().setAll(bp);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openMainTab() {
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/MainPage.fxml"));
			mainSection.getChildren().setAll(bp);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	


	

	/**
	 * Method to display a searching tab
	 */
	public void openSearchTab() {
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/Searching.fxml"));
			mainSection.getChildren().setAll(bp);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to display my bids
	 */
	public void displayMyBids() {
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("/MyBids.fxml"));
			mainSection.getChildren().setAll(bp);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to filter by artwork or users
	 */
	public void handleSearch() {
		if (artworkSelect.isSelected()) {
			displayArtworks();
		} else if (userSelect.isSelected()) {
			displayUsers();
		}
	}

	/**
	 * Displays chosen artwork
	 */
	public void displayArtworks() {
		ArrayList<String> sculptures = new ArrayList<>();
		ArrayList<String> paintings = new ArrayList<>();
		ArrayList<String> artworks = new ArrayList<>();

		for (Sculpture sculptureA : FileReader.getSculptures()) {
			String s = searching.getText();
			if (sculptureA.getTitle().toLowerCase().contains(s.toLowerCase())) {
				sculptures.add(sculptureA.getTitle());
			}
		}

		for (Painting paintingA : FileReader.getPaintings()) {
			String s = searching.getText();
			if (paintingA.getTitle().toLowerCase().contains(s.toLowerCase())) {
				paintings.add(paintingA.getTitle());
			}
		}

		if (sculptureSelect.isSelected() && !paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(sculptures);
		} else if (!sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			observableList = FXCollections.observableArrayList(paintings);
		} else if (sculptureSelect.isSelected() && paintingSelect.isSelected()) {
			for (String paint : paintings) {
				artworks.add(paint);
			}
			for (String scul : sculptures) {
				artworks.add(scul);
			}
			observableList = FXCollections.observableArrayList(artworks);
		} else {
			observableList = FXCollections.observableArrayList(new ArrayList<String>());
		}
		searchList.setItems(observableList);
	}

	/**
	 * Displays selected object
	 */
	public void getSearchSelection() {
		String s = searchList.getSelectionModel().getSelectedItem();
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			if (userSelect.isSelected()) {
				if (FileReader.getUser(s) != null) {
					User user = FileReader.getUser(s);
					UserDisplayController.setUser(user);

					FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("/UserDisplay.fxml"));
					try {
						Parent root = fxmlL.load();
						Scene scene = new Scene(root, 450, 300);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle(user.getUsername());
						stage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				AuctionView l = new AuctionView(FileReader.getArtwork(s));
				l.displayInWindow();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays a user
	 */
	public void displayUsers() {
		ArrayList<String> users = new ArrayList<>();
		for (User user : FileReader.getUsers()) {
			if (user.getUsername().toLowerCase().contains(searchingContent.toLowerCase())) {
				users.add(user.getUsername());
			}
		}
		observableList = FXCollections.observableArrayList(users);
		searchList.setItems(observableList);
	}

	/**
	 * Switches Scene into one that contains list of auctions made by the user
	 */
	public void showMyAuctions() {
		BorderPane bp; // Border Pane to load the new BorderPane in
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("MyAuctions.fxml"));
			mainSection.getChildren().setAll(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Switches Scene into one that contains list of favourite users added by the
	 * user
	 */
	public void favouriteUsers() {
		BorderPane bp;
		try {
			bp = (BorderPane) FXMLLoader.load(getClass().getResource("FavouriteUsers.fxml"));
			mainSection.getChildren().setAll(bp);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens a new window where user can input data for Artwork
	 */
	public void createNewArtwork() {
		FXMLLoader fxmlL = new FXMLLoader(getClass().getResource("AddArtwork.fxml"));
		try {
			Parent root = fxmlL.load();

			Scene scene = new Scene(root, 1009, 750);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);

			stage.setTitle("Add a new artwork");
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays the users information
	 */
	public void userSettings() {
		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
			mainSection.getChildren().setAll(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Displays the main page
	 */
	public void displayMainDashboard() {
		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("MainDashboard.fxml"));
			mainSection.getChildren().setAll(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Show the favourite users settings
	 */
	public void userSettings1() {
		BorderPane bp;
		try {
			bp = FXMLLoader.load(getClass().getResource("FavouriteUsers.fxml"));
			mainSection.getChildren().setAll(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Returns the main section
	 * 
	 * @return mainSection - the main GUI
	 */
	public BorderPane getMainSection() {
		return mainSection;
	}

}