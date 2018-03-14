import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

public class SalesController {

	@FXML
	private RadioButton daily;

	@FXML
	private RadioButton weekly;

	@FXML
	private RadioButton monthly;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private VBox chartArea;

	@FXML
	private RadioButton barchart;

	@FXML
	private RadioButton piechart;

	@FXML
	private RadioButton linechart;
	private ArrayList<Artwork> userArtworks;

	private ArrayList<Artwork> soldArtworks;
	private Date date1;

	@FXML
	private Button ref;

	public void initialize() {

		soldArtworks = new ArrayList<>();

		userArtworks = FileReader.getArtworks();

		for (Artwork artwork : userArtworks) {
			System.out.println(artwork.getOwner().getFullName());
			if (artwork.isBidIsOver() && artwork.getOwner() == LoginController.getUser()) {
				System.out.println(artwork.getOwner().getFirstName());
				soldArtworks.add(artwork);
				System.out.println("The artwork has been sold");

			}
		}

		chartArea.getChildren().add(getPieChart());

		ref.setOnAction(e -> ref());

	}

	public void ref() {
		System.out.println("asassa");

		LocalDate date = dateFrom.getValue();
		date1 = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		String d = dateFormatter.format(date1);
		System.out.println(d);
	}

	public PieChart getPieChart() {

		double sculptureVal = 0;

		double paintingVal = 0;

		for (Artwork art : soldArtworks) {
			if(art instanceof Sculpture) {
				sculptureVal += art.getHighestBidAmount();
			} else if (art instanceof Painting) {
				paintingVal += art.getHighestBidAmount();

			}

		}

		ObservableList<PieChart.Data> pieChartData = FXCollections
				.observableArrayList(new PieChart.Data("Sculptures: " + sculptureVal, sculptureVal), new PieChart.Data("Paintings: " + paintingVal, paintingVal));

		final PieChart chart = new PieChart(pieChartData);
		return chart;
	}

	// public static

}
