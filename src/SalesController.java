import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SalesController {

	@FXML
	private RadioButton weekly;

	@FXML
	private RadioButton monthly;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private VBox chartArea;

	@FXML
	private RadioButton barchart;

	@FXML
	private RadioButton piechart;

	@FXML
	private RadioButton linechart;

	@FXML
	private Button generateButton;

	private ArrayList<Artwork> userArtworks;

	private ArrayList<Artwork> soldArtworks;

	@FXML
	private Button ref;

	private ToggleGroup tg;
	private ToggleGroup tg1;

	private Date from;
	private Date until;

	public void initialize() {
		tg = new ToggleGroup();

		barchart.setToggleGroup(tg);
		piechart.setToggleGroup(tg);
		linechart.setToggleGroup(tg);

		weekly.setToggleGroup(tg1);
		monthly.setToggleGroup(tg1);

		monthly.setSelected(true);
		piechart.setSelected(true);

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

		ref.setOnAction(e -> ref());
		generateButton.setOnAction(e -> ref());

	}

	public void ref() {
		System.out.println("asassa");

		LocalDate date = dateFrom.getValue();

		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		if (piechart.isSelected()) {
			chartArea.getChildren().clear();
			chartArea.getChildren().add(getPieChart());
		} else if (barchart.isSelected()) {
			chartArea.getChildren().clear();
			chartArea.getChildren().add(getBarChart());

		} else if (linechart.isSelected()) {
			chartArea.getChildren().clear();
			chartArea.getChildren().add(getLineChart(2018));

		}

	}

	public BarChart getBarChart() {

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

		return null;
	}

	public PieChart getPieChart() {

		double sculptureVal = 0;

		double paintingVal = 0;

		for (Artwork art : soldArtworks) {
			if (art instanceof Sculpture) {
				sculptureVal += art.getHighestBidAmount();
			} else if (art instanceof Painting) {
				paintingVal += art.getHighestBidAmount();

			}

		}

		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
				new PieChart.Data("Sculptures: " + sculptureVal, sculptureVal),
				new PieChart.Data("Paintings: " + paintingVal, paintingVal));

		final PieChart chart = new PieChart(pieChartData);
		return chart;
	}

	public LineChart getLineChart(int year) {

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("Income per month in " + year);

		XYChart.Series<String, Number> data = new XYChart.Series<>();

		Month[] months = new Month[12];
		months[0] = new Month("January", 1);
		months[1] = new Month("February", 2);
		months[2] = new Month("March", 3);
		months[3] = new Month("April", 4);
		months[4] = new Month("May", 5);
		months[5] = new Month("June", 6);
		months[6] = new Month("July", 7);
		months[7] = new Month("August", 8);
		months[8] = new Month("September", 9);
		months[9] = new Month("October", 10);
		months[10] = new Month("November", 11);
		months[11] = new Month("December", 12);

		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		String d = dateFormatter.format(date);

		for (Artwork a : soldArtworks) {

			int month = a.getDateAdded().getMonth();

			if (a instanceof Painting) {
				months[month].addPaintingsProfit(a.getHighestBidAmount());
				System.out.println(a.getHighestBidAmount());
			} else if (a instanceof Sculpture) {
				months[month].addSculpturesProfit(a.getHighestBidAmount());
				System.out.println(a.getHighestBidAmount());

			}
		}

		for (Month m : months) {
			data.getData().add(new XYChart.Data(m.getName(), m.getTotal()));
		}

		lineChart.getData().add(data);

		test();
		return lineChart;

	}

	public void test() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
		Date date = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		System.out.println(week + "asasaasd");

	}

}

class Month {

	private String name;
	private int index;
	private double sculptureProfit;
	private double paintingsProfit;
	private double total;

	public Month(String name, int index) {

		this.name = name;
		this.index = index;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getSculptureProfit() {
		return sculptureProfit;
	}

	public void setSculptureProfit(double sculptureProfit) {
		this.sculptureProfit = sculptureProfit;
	}

	public double getPaintingsProfit() {
		return paintingsProfit;
	}

	public void setPaintingsProfit(double paintingsProfit) {
		this.paintingsProfit = paintingsProfit;
	}

	public void addPaintingsProfit(double profit) {
		paintingsProfit += profit;
	}

	public void addSculpturesProfit(double profit) {
		sculptureProfit += profit;
	}

	public double getTotal() {
		return sculptureProfit + paintingsProfit;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}

class Week extends Month {

	private int weekOfYear;

	public Week(String name, int index) {
		super(name, index);
		// TODO Auto-generated constructor stub
	}

}
