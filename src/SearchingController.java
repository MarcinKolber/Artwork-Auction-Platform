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
	private TextField priceFrom;

	@FXML
	private Button refresh;

	private ToggleGroup tg;
	private ArrayList<Artwork> arts;

	private ArrayList<User> usersList;

	public void initialize() {
		usersList = FileReader.getUsers();
		tg = new ToggleGroup();

		artworks.setToggleGroup(tg);
		users.setToggleGroup(tg);
		artworks.setSelected(true);
		arts = FileReader.getArtworks();

		refresh();
		users.setOnAction(e -> refresh());
		artworks.setOnAction(e -> refresh());
		
		if(users.isSelected()) {
			sculptures.setDisable(true);
			paintings.setDisable(true);
			descriptions.setDisable(true);
			author.setDisable(true);
		
		}

		sculptures.setSelected(true);
		paintings.setSelected(true);
		sculptures.setOnAction(e -> refresh());
		paintings.setOnAction(e -> refresh());
		descriptions.setOnAction(e -> refresh());
		author.setOnAction(e -> refresh());
		refresh.setOnAction(e -> refresh());

	}

	public void refresh() {
		
		if(users.isSelected()) {
			sculptures.setDisable(true);
			paintings.setDisable(true);
			descriptions.setDisable(true);
			author.setDisable(true);
		
		} else {
			sculptures.setDisable(false);
			paintings.setDisable(false);
			descriptions.setDisable(false);
			author.setDisable(false);
		}

		if (users.isSelected()) {

			String input = searchingTextField.getText();
			s.getChildren().clear();

			for (User user : usersList) {
				if (user.getUsername().toLowerCase().contains(input.toLowerCase())) {
					UserView uv = new UserView(user);
					s.getChildren().add(uv);

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

		} else {

			ArrayList<Listing> found = new ArrayList<>();

			if (!descriptions.isSelected() && !author.isSelected()) { // none selected

				String userInput = searchingTextField.getText();
				s.getChildren().clear();

				for (Artwork artwork : arts) {
					if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
						Listing l = new Listing(artwork);
						l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
						s.getChildren().add(l);

					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						String userInput = searchingTextField.getText();
						if (userInput.length() > 0) {
							s.getChildren().clear();

							for (Artwork artwork : arts) {
								if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())) {
									Listing l = new Listing(artwork);
									l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

									s.getChildren().add(l);
									found.add(l);

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

							s.getChildren().add(l);
							found.add(l);

						}
					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent event) {

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
									s.getChildren().add(l);
									found.add(l);

								}
							}
						}

					}

				});

			} else if (!descriptions.isSelected() && author.isSelected()) { // only author
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

				if (userInput.trim().length() > 1) {
					for (Artwork artwork : arts) {
						if (artwork.getTitle().toLowerCase().contains(userInput.toLowerCase())
								|| artwork.getCreator().toLowerCase().contains(userInput.toLowerCase())) {
							Listing l = new Listing(artwork);

							if ((artwork.getCreator().toLowerCase().contains(userInput.toLowerCase()))) {
								l.getCreator().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");

							} else {
								l.getTitle().setStyle("-fx-background-color: rgb(255,0, 20,0.5);");
							}

							s.getChildren().add(l);

						}
					}
				}

				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						String userInput = searchingTextField.getText();
						if (userInput.trim().length() > 1) {

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

									s.getChildren().add(l);
									found.add(l);

								}
							}

						}
					}
				});
			} else if (descriptions.isSelected() && author.isSelected()) { // both
				s.getChildren().clear();

				String userInput = searchingTextField.getText();

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

							s.getChildren().add(l);
							found.add(l);

						}
					}
				}
				searchingTextField.setOnKeyReleased(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {

						String userInput = searchingTextField.getText();
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

									s.getChildren().add(l);
									found.add(l);

								}
							}
						}
					}

				});
			}

		}
	}
}
