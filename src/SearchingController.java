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

public class SearchingController {

	@FXML
	private HBox searchingTab;

	@FXML
	private TextField searchingTextField;

	@FXML
	private Button searchButton;

	@FXML
	private FlowPane s;

	@FXML
	private RadioButton artworks;

	@FXML
	private RadioButton users;

	@FXML
	private CheckBox sculptures;

	@FXML
	private CheckBox paintings;

	@FXML
	private CheckBox descriptions;

	@FXML
	private CheckBox author;

	@FXML
	private CheckBox priceRange;

	@FXML
	private TextField priceFrom;

	@FXML
	private TextField priceTo;

	@FXML
	private Button refresh;

	private ToggleGroup tg;
	private ArrayList<Artwork> arts;

	private ArrayList<User> usersList;
	private double minimum;
	private double maximum;

	public void initialize() {
		//initialising maximum and minimum values
		minimum = Double.MIN_VALUE;
		maximum = Double.MAX_VALUE;

		//getting list of users
		usersList = FileReader.getUsers();
		tg = new ToggleGroup();

		//adding artworks and users to toggle group
		artworks.setToggleGroup(tg);
		users.setToggleGroup(tg);

		artworks.setSelected(true); //selecting artworks by default
		sculptures.setSelected(true); //select sculptures by default
		paintings.setSelected(true); //select paintings by default

		arts = FileReader.getArtworks(); //getting the artwork files

		refresh(); //refreshing the search
		users.setOnAction(e -> refresh()); //refresh when users is toggled
		artworks.setOnAction(e -> refresh()); //refresh when artworks is toggled
		sculptures.setOnAction(e -> refresh()); //refresh when sculptures is toggled
		paintings.setOnAction(e -> refresh()); //refresh when paintings is toggled
		descriptions.setOnAction(e -> refresh()); //refresh when descriptions is toggled
		author.setOnAction(e -> refresh()); //refresh when author is toggled

		if (users.isSelected()) { //if users is selected
			sculptures.setDisable(true); //disable sculptures
			paintings.setDisable(true); //disable paintings
			descriptions.setDisable(true); //disable descriptions
			author.setDisable(true); //disable author

		}

		//when refreshed, get the new min and max values
		refresh.setOnAction(e -> {
			getMinMax();
			refresh();
		});

		//when price change is changed, get the new min and max values
		priceRange.setOnAction(e -> {
			getMinMax();
			refresh();
		});

	}

	/**
	 * Method to get the min and max vlues
	 */
	public void getMinMax() {

		if (priceRange.isSelected()) { //if the price range is enabled
			double min = Double.MIN_VALUE;
			double max = Double.MAX_VALUE;

			if (priceRange.isSelected()) {
				try {
					int minPrice = Integer.parseInt(priceFrom.getText()); //get the min price
					int maxPrice = Integer.parseInt(priceTo.getText()); //get the max price
					//set the min and max values
					minimum = minPrice;
					maximum = maxPrice;
					//print out the values
					System.out.println(min);
					System.out.println(max);

				} catch (Exception e) { //if something goes wrong, set the min and max to default values
					minimum = Double.MIN_VALUE;
					maximum = Double.MAX_VALUE;
				}
			}
		} else { //if price range is disabled, set the min and max to default values
			minimum = Double.MIN_VALUE;
			maximum = Double.MAX_VALUE;
		}

	}

	/**
	 * Method to refresh the search
	 */
	public void refresh() {

		if (users.isSelected()) { //if users are enabled, disable everything but users
			sculptures.setDisable(true);
			paintings.setDisable(true);
			descriptions.setDisable(true);
			author.setDisable(true);

		} else { //if users is not enabled, enable everything else
			sculptures.setDisable(false);
			paintings.setDisable(false);
			descriptions.setDisable(false);
			author.setDisable(false);
		}

		if (users.isSelected()) { //if users is selected

			String input = searchingTextField.getText(); //get the string from text box
			s.getChildren().clear(); //clear the current users displayed

			for (User user : usersList) { //for each user in users
				if (user.getUsername().toLowerCase().contains(input.toLowerCase())) {
					UserView uv = new UserView(user); //create a new user view
					s.getChildren().add(uv); //add the user to the display

				}
			}

			searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					String input = searchingTextField.getText();
					if (input.length() > 0) {
						s.getChildren().clear();

						for (User user : usersList) {
							if (user.getUsername().toLowerCase().contains(input.toLowerCase())) {
								UserView uv = new UserView(user);
								s.getChildren().add(uv);

							}
						}
					}

				}

			});

		} else{
	//TODO COMMENT THIS
			if (!descriptions.isSelected() && !author.isSelected()) { // none selected

				String userInput = searchingTextField.getText();
				s.getChildren().clear();

				for (Artwork artwork : arts) {
					if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
						Listing l = new Listing(artwork);
						l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
						System.out.println("min " + minimum);
						System.out.println("max " + maximum);

						if (sculptures.isSelected() && artwork instanceof Sculpture
								&& artwork.getHighestBidAmount() > minimum && artwork.getHighestBidAmount() <= maximum) {
							s.getChildren().add(l);

						} else if (paintings.isSelected() && artwork instanceof Painting
								&& artwork.getHighestBidAmount() > minimum && artwork.getHighestBidAmount() <= maximum) {
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
						if (userInput.length() > 0) {
							s.getChildren().clear();

							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
									Listing l = new Listing(artwork);
									l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

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

			}

			else if (descriptions.isSelected() && !author.isSelected()) { // 2
				s.getChildren().clear();

				System.out.println("Case two");
				String userInput = searchingTextField.getText();

				if (!userInput.trim().isEmpty() && userInput.length() > 2) {
					s.getChildren().clear();
					for (Artwork artwork : arts) {
						if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
							Listing l = new Listing(artwork);

							if (artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
								l.getDescription().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

							} else {
								l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
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

						if (!userInput.trim().isEmpty() && userInput.length() > 2) {
							s.getChildren().clear();
							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
										|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
									Listing l = new Listing(artwork);

									if (artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())) {
										l.getDescription().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

									} else {
										l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
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

				if (userInput.trim().length() > 0) {
					for (Artwork artwork : arts) {
						if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
							Listing l = new Listing(artwork);

							if ((artwork.getCreator().toLowerCase().contains(userInput.toLowerCase()))) {
								l.getCreator().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

							} else {
								l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
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

						if (userInput.trim().length() > 0) {

							s.getChildren().clear();

							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
										|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
									Listing l = new Listing(artwork);

									if ((artwork.getCreator().toLowerCase().contains(userInput.toLowerCase()))) {
										l.getCreator().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

									} else {
										l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
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
			} else if (descriptions.isSelected() && author.isSelected()) { // both
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

				if (userInput.trim().length() > 0) {
					for (Artwork artwork : arts) {
						if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
							Listing l = new Listing(artwork);

							if ((artwork.getDescription().toLowerCase().contains(userInput.toLowerCase()))) {
								l.getDescription().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

							} else if (artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
								l.getCreator().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
							}

							else {
								l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
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

						s.getChildren().clear();

						if (userInput.trim().length() > 1) {
							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
										|| artwork.getDescription().toLowerCase().contains(userInput.toLowerCase())
										|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
									Listing l = new Listing(artwork);

									if ((artwork.getDescription().toLowerCase().contains(userInput.toLowerCase()))) {
										l.getDescription().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

									} else if (artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
										l.getCreator().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
									}

									else {
										l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

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
			}

		}
	}
}
