import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SalesController {


    @FXML
    private VBox chartArea;

    @FXML
    private DatePicker dateFrom;


    @FXML
    private DatePicker dateTo;


    @FXML
    private RadioButton barchart;

    @FXML
    private RadioButton piechart;

    @FXML
    private RadioButton linechart;

    @FXML
    private Button generateButton;


	@FXML
	private ChoiceBox<Integer> year;

	private ArrayList<Artwork> userArtworks;

	private ArrayList<Artwork> soldArtworks;

	@FXML
	private Button ref;

	private ToggleGroup tg;
	private ToggleGroup tg1;

	private Date from;
	private Date until;
	private int yearNo;
	private ArrayList<Integer> years;

	public void initialize() {

		years = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			years.add(i + 2010);
		}

		year.setItems(FXCollections.observableArrayList(years));

		year.getSelectionModel().select(8);
		yearNo = year.getSelectionModel().getSelectedItem();
		LocalDate minDate = LocalDate.MIN;

		LocalDate maxDate = LocalDate.MAX;

		dateFrom.setValue((LocalDate.of(yearNo, 1, 1)));
		dateTo.setValue((LocalDate.of(yearNo, 12, 31)));

		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");

		String d = dateFormatter.format(date);
		from = LoginController.getUser().getAccountCreationDate();

		until = new Date();

		tg = new ToggleGroup();

		barchart.setToggleGroup(tg);
		piechart.setToggleGroup(tg);
		linechart.setToggleGroup(tg);

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

		year.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
				yearNo = years.get((int) number2);

				dateFrom.setValue((LocalDate.of(yearNo, 1, 1)));
				dateTo.setValue((LocalDate.of(yearNo, 12, 31)));
				ref();
			}
		});
		ref();

	}

	public void ref() {

		if(!piechart.isSelected()) {

			dateFrom.setValue((LocalDate.of(yearNo, 1, 1)));
			dateTo.setValue((LocalDate.of(yearNo, 12, 31)));
		}
		

		LocalDate date1 = dateFrom.getValue();
		LocalDate date2 = dateTo.getValue();

		Date date3 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date4 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());

		from = date3;
		until = date4;

		System.out.println(date3);
		System.out.println(date4);

		if (piechart.isSelected()) {
			chartArea.getChildren().clear();
			dateFrom.setDisable(false);
			dateTo.setDisable(false);
			chartArea.getChildren().add(getPieChart());
		} else if (barchart.isSelected()) {
			int pickedYear = year.getSelectionModel().getSelectedItem();

			dateFrom.setDisable(true);
			dateTo.setDisable(true);
			chartArea.getChildren().clear();
			chartArea.getChildren().add(getBarChart(pickedYear));

		} else if (linechart.isSelected()) {
			int pickedYear = year.getSelectionModel().getSelectedItem();

			dateFrom.setDisable(true);
			dateTo.setDisable(true);

			chartArea.getChildren().clear();
			chartArea.getChildren().add(getLineChart(pickedYear));

		}

	}

	public BarChart<String, Number> getBarChart(int year) {

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		xAxis.setLabel("Month");
		yAxis.setLabel("Amount");

		XYChart.Series series1 = new XYChart.Series();
		series1.setName("Paintings");

		XYChart.Series series2 = new XYChart.Series();
		series2.setName("Sculptures");

		final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);

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

			if (a.getDateAdded().after(from) && a.getDateAdded().before(until)) {
				int month = a.getDateAdded().getMonth();

				if (a instanceof Painting) {
					months[month].addPaintingsProfit(a.getHighestBidAmount());
					System.out.println(a.getHighestBidAmount());
				} else if (a instanceof Sculpture) {
					months[month].addSculpturesProfit(a.getHighestBidAmount());
					System.out.println(a.getHighestBidAmount());

				}
			}
		}
		for (Month m : months) {
			series1.getData().add(new XYChart.Data(m.getName(), m.getPaintingsProfit()));
			series2.getData().add(new XYChart.Data(m.getName(), m.getSculptureProfit()));

		}

		bc.getData().addAll(series1, series2);

		return bc;
	}

	public PieChart getPieChart() {

		LocalDate date1 = dateFrom.getValue();

		LocalDate date2 = dateTo.getValue();

		Date date3 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date4 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		double sculptureVal = 0;

		double paintingVal = 0;

		for (Artwork art : soldArtworks) {
			if (art.getDateAdded().after(date3) && art.getDateAdded().before(date4)) {

				if (art instanceof Sculpture) {
					sculptureVal += art.getHighestBidAmount();
				} else if (art instanceof Painting) {
					paintingVal += art.getHighestBidAmount();

				}
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

			if (a.getDateAdded().after(from) && a.getDateAdded().before(until)) {

				int month = a.getDateAdded().getMonth();

				if (a instanceof Painting) {
					months[month].addPaintingsProfit(a.getHighestBidAmount());
					System.out.println(a.getHighestBidAmount());
				} else if (a instanceof Sculpture) {
					months[month].addSculpturesProfit(a.getHighestBidAmount());
					System.out.println(a.getHighestBidAmount());

				}
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
