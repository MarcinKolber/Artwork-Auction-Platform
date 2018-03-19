import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * A class providing an advanced searching engine
 * 
 * @author Marcin Kolber (869527)
 *
 */
public class SearchingController {

	@FXML
	private HBox searchingTab; // a box that holds a text field and a button

	@FXML
	private TextField searchingTextField; // a text field for user input

	@FXML
	private Button searchButton; // button to press for searching

	@FXML
	private FlowPane s; // pane with displayed found artworks

	@FXML
	private RadioButton artworks; // radio button to select only artworks

	@FXML
	private RadioButton users; // radio button to select only users

	@FXML
	private CheckBox sculptures; // checkbox to include sculptures

	@FXML
	private CheckBox paintings; // checkbox to include paintings

	@FXML
	private CheckBox descriptions; // checkbox to include description

	@FXML
	private CheckBox author; // checkbox to include authors

	@FXML
	private CheckBox priceRange; // checkbox to include price range

	@FXML
	private TextField priceFrom; // field with a min price

	@FXML
	private TextField priceTo; // field with a max price

	@FXML
	private Button refresh; // button to refresh the searching results

	private ToggleGroup tg; // toggle group for users and artworks radio buttons

	private ArrayList<Artwork> arts; // arraylist of found artworks

	private ArrayList<User> usersList; // arraylist of found users

	private double minimum; // min price
	private double maximum; // max price

	public void initialize() {
		// initialising maximum and minimum values
		minimum = Double.MIN_VALUE;
		maximum = Double.MAX_VALUE;

		// getting list of users
		usersList = FileReader.getUsers();
		tg = new ToggleGroup();

		// adding artworks and users to toggle group
		artworks.setToggleGroup(tg);
		users.setToggleGroup(tg);

		artworks.setSelected(true); // selecting artworks by default
		sculptures.setSelected(true); // select sculptures by default
		paintings.setSelected(true); // select paintings by default

		arts = FileReader.getArtworks(); // getting the artwork files

		refresh(); // refreshing the search
		users.setOnAction(e -> refresh()); // refresh when users is toggled
		artworks.setOnAction(e -> refresh()); // refresh when artworks is toggled
		sculptures.setOnAction(e -> refresh()); // refresh when sculptures is toggled
		paintings.setOnAction(e -> refresh()); // refresh when paintings is toggled
		descriptions.setOnAction(e -> refresh()); // refresh when descriptions is toggled
		author.setOnAction(e -> refresh()); // refresh when author is toggled

		if (users.isSelected()) { // if users is selected
			sculptures.setDisable(true); // disable sculptures
			paintings.setDisable(true); // disable paintings
			descriptions.setDisable(true); // disable descriptions
			author.setDisable(true); // disable author
			priceRange.setDisable(true);
			priceFrom.setDisable(true);
			priceTo.setDisable(true);
		}

		// when refreshed, get the new min and max values
		refresh.setOnAction(e -> {
			getMinMax();
			refresh();
		});

		// when price change is changed, get the new min and max values
		priceRange.setOnAction(e -> {
			getMinMax();
			refresh();
		});

	}

	/**
	 * Method to get the min and max vlues
	 */
	public void getMinMax() {

		if (priceRange.isSelected()) { // if the price range is enabled
			double min = Double.MIN_VALUE;
			double max = Double.MAX_VALUE;

			if (priceRange.isSelected()) {
				try {
					int minPrice = Integer.parseInt(priceFrom.getText()); // get the min price
					int maxPrice = Integer.parseInt(priceTo.getText()); // get the max price
					// set the min and max values
					minimum = minPrice;
					maximum = maxPrice;
					// print out the values

				} catch (Exception e) { // if something goes wrong, set the min and max to default values
					minimum = Double.MIN_VALUE;
					maximum = Double.MAX_VALUE;
				}
			}
		} else { // if price range is disabled, set the min and max to default values
			minimum = Double.MIN_VALUE;
			maximum = Double.MAX_VALUE;
		}

	}

	/**
	 * Method to refresh the search
	 */
	public void refresh() {

		if (users.isSelected()) { // if users are enabled, disable everything but users
			sculptures.setDisable(true);
			paintings.setDisable(true);
			descriptions.setDisable(true);
			author.setDisable(true);

		} else { // if users is not enabled, enable everything else
			sculptures.setDisable(false);
			paintings.setDisable(false);
			descriptions.setDisable(false);
			author.setDisable(false);
		}

		if (users.isSelected()) { // if users is selected

			String input = searchingTextField.getText(); // get the string from text box
			s.getChildren().clear(); // clear the current users displayed

			for (User user : usersList) { // for each user in users
				if (user.getUsername().toLowerCase().contains(input.toLowerCase())) {
					UserView uv = new UserView(user); // create a new user view
					s.getChildren().add(uv); // add the user to the display

				}
			}

			// 
			searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					String input = searchingTextField.getText();

					s.getChildren().clear();

					for (User user : usersList) {
						if (user.getUsername().toLowerCase().contains(input.toLowerCase())) {
							UserView uv = new UserView(user);
							s.getChildren().add(uv);

						}
					}

				}

			});

		} else {

			if (!descriptions.isSelected() && !author.isSelected()) { // none selected

				String userInput = searchingTextField.getText();
				s.getChildren().clear();

				for (Artwork artwork : arts) {
					if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
						AuctionView l = new AuctionView(artwork);
						l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

						if (sculptures.isSelected() && artwork instanceof Sculpture
								&& artwork.getHighestBidAmount() > minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						} else if (paintings.isSelected() && artwork instanceof Painting
								&& artwork.getHighestBidAmount() > minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						}

					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						String userInput = searchingTextField.getText();

						double min = Double.MIN_VALUE;
						double max = Double.MAX_VALUE;

						if (priceRange.isSelected()) {
							try {
								double minPrice = Double.parseDouble(priceFrom.getText());
								double maxPrice = Double.parseDouble(priceTo.getText());

								min = minPrice;
								max = maxPrice;

							} catch (Exception e) {

							}
						}

						s.getChildren().clear();

						for (Artwork artwork : arts) {
							if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
								AuctionView l = new AuctionView(artwork);
								l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

								if (sculptures.isSelected() && artwork instanceof Sculpture
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								} else if (paintings.isSelected() && artwork instanceof Painting
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								}

							}
						}

					}

				});

			}

			else if (descriptions.isSelected() && !author.isSelected()) { // 2
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

				if (!userInput.trim().isEmpty()) {
					s.getChildren().clear();
					for (Artwork artwork : arts) {
						if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
							AuctionView l = new AuctionView(artwork);

							if (artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
								l.getDescription().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

							} else {
								l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
							}

							if (sculptures.isSelected() && artwork instanceof Sculpture
									&& artwork.getHighestBidAmount() >= minimum
									&& artwork.getHighestBidAmount() <= maximum) {
								s.getChildren().add(l);

							} else if (paintings.isSelected() && artwork instanceof Painting
									&& artwork.getHighestBidAmount() >= minimum
									&& artwork.getHighestBidAmount() <= maximum) {
								s.getChildren().add(l);

							}

						}
					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {

						String userInput = searchingTextField.getText();

						double min = Double.MIN_VALUE;
						double max = Double.MAX_VALUE;

						if (priceRange.isSelected()) {
							try {
								double minPrice = Double.parseDouble(priceFrom.getText());
								double maxPrice = Double.parseDouble(priceTo.getText());

								min = minPrice;
								max = maxPrice;

							} catch (Exception e) {

							}
						}

						if (!userInput.trim().isEmpty()) {
							s.getChildren().clear();
							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
										|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
									AuctionView l = new AuctionView(artwork);

									if (artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
										l.getDescription().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

									} else {
										l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
									}

									if (sculptures.isSelected() && artwork instanceof Sculpture
											&& artwork.getHighestBidAmount() >= minimum
											&& artwork.getHighestBidAmount() <= maximum) {
										s.getChildren().add(l);

									} else if (paintings.isSelected() && artwork instanceof Painting
											&& artwork.getHighestBidAmount() >= minimum
											&& artwork.getHighestBidAmount() <= maximum) {
										s.getChildren().add(l);

									}

								}
							}
						}

					}

				});

			} else if (!descriptions.isSelected() && author.isSelected()) { // only author
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

				for (Artwork artwork : arts) {
					if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
							|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
						AuctionView l = new AuctionView(artwork);

						if ((artwork.getCreator().toLowerCase().contains(userInput.toLowerCase()))) {
							l.getCreator().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

						} else {
							l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
						}

						if (sculptures.isSelected() && artwork instanceof Sculpture
								&& artwork.getHighestBidAmount() >= minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						} else if (paintings.isSelected() && artwork instanceof Painting
								&& artwork.getHighestBidAmount() >= minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						}
					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {
						String userInput = searchingTextField.getText();

						double min = Double.MIN_VALUE;
						double max = Double.MAX_VALUE;

						s.getChildren().clear();

						for (Artwork artwork : arts) {
							if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
									|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
								AuctionView l = new AuctionView(artwork);

								if ((artwork.getCreator().toLowerCase().contains(userInput.toLowerCase()))) {
									l.getCreator().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

								} else {
									l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
								}

								if (sculptures.isSelected() && artwork instanceof Sculpture
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								} else if (paintings.isSelected() && artwork instanceof Painting
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								}

							}

						}
					}
				});
			} else if (descriptions.isSelected() && author.isSelected()) { // both
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

				for (Artwork artwork : arts) {
					if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
							|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())
							|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
						AuctionView l = new AuctionView(artwork);

						if ((artwork.getDescription().toLowerCase().contains(userInput.toLowerCase()))) {
							l.getDescription().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

						} else if (artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
							l.getCreator().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
						}

						else {
							l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
						}

						if (sculptures.isSelected() && artwork instanceof Sculpture
								&& artwork.getHighestBidAmount() >= minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						} else if (paintings.isSelected() && artwork instanceof Painting
								&& artwork.getHighestBidAmount() >= minimum
								&& artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						}
					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {

						String userInput = searchingTextField.getText();

						double min = Double.MIN_VALUE;
						double max = Double.MAX_VALUE;

						s.getChildren().clear();

						for (Artwork artwork : arts) {
							if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
									|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())
									|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
								AuctionView l = new AuctionView(artwork);

								if ((artwork.getDescription().toLowerCase().contains(userInput.toLowerCase()))) {
									l.getDescription().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

								} else if (artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
									l.getCreator().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");
								}

								else {
									l.getTitle().setStyle("-fx-background-color: rgb(255, 153, 0, 0.5);");

								}

								if (sculptures.isSelected() && artwork instanceof Sculpture
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								} else if (paintings.isSelected() && artwork instanceof Painting
										&& artwork.getHighestBidAmount() >= minimum
										&& artwork.getHighestBidAmount() <= maximum) {
									s.getChildren().add(l);

								}

							}

						}
					}

				});
			}

		}
	}
}
