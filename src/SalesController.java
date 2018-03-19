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

/**
 * This class is used to create sales based graphs for analysis.
 * @author Joshua Thomas (907019) and Marwan Refaie (689580)  
 */
public class SalesController {

    @FXML
    private VBox chartArea; //A panel to show the chart

    @FXML
    private DatePicker dateFrom; //Allows user to pick a start date for analysis


    @FXML
    private DatePicker dateTo; //Allows user to pick an end date for analysis


    @FXML
    private RadioButton barchart; //Allows user to pick a bar chart

    @FXML
    private RadioButton piechart; //Allows user to pick a pie chart

    @FXML
    private RadioButton linechart; //Allows user to pick a line chart

    @FXML
    private Button generateButton; //Allows user to create the chart

	@FXML
	private ChoiceBox<Integer> year; //Allows user to pick a year to analyse sales of

	private ArrayList<Artwork> userArtworks; //Storage for user artworks

	private ArrayList<Artwork> soldArtworks; //Storage for sold artworks

	@FXML
	private Button ref; //Allows user to refresh

	private ToggleGroup tg; // toggle group for radio buttons for types of charts
	private Date from; //Start date of analysis
 	private Date until; //End date of analysis
	private int yearNo; //Number of the year
	private ArrayList<Integer> years; //Storage for years

	/**
	 * Method to initialize the GUI.
	 */
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



	/**
	 * Refreshes the area with a chart
	 */
	public void ref() {

		// Handling dates
		if(!piechart.isSelected()) {

			dateFrom.setValue((LocalDate.of(yearNo, 1, 1)));
			dateTo.setValue((LocalDate.of(yearNo, 12, 31)));
		}
		

		// Getting dates from date pickers
		LocalDate date1 = dateFrom.getValue();
		LocalDate date2 = dateTo.getValue();

		// Converting dates
		Date date3 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date4 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());

		from = date3;
		until = date4;

		// Displaying different charts depending on the selection
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

	/**
	 * Method to get a bar chart object representing a specific year.
	 * @param year - the year of the bar chart you want.
	 * @return a bar chart with computed data
	 */
	public BarChart<String, Number> getBarChart(int year) {
		//Setting up the bar chart
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
			//Checks the date of the sales and adds to arraylist
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

		return bc; //The created barchart
	}

	/**
	 * Method to return a pie chart object.
	 * @return pie chart with displayed data
	 */
	public PieChart getPieChart() {
		//Setting the pie chart
		LocalDate date1 = dateFrom.getValue();

		LocalDate date2 = dateTo.getValue();

		Date date3 = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date date4 = Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		
		double sculptureVal = 0;

		double paintingVal = 0;

		for (Artwork art : soldArtworks) {
			if (art.getDateAdded().after(date3) && art.getDateAdded().before(date4)) {
				//Checking if sale was in date, adds to value if it is
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
		return chart; //The pie chart
	}

	/**
	 * Method to return a line chart object representing a certain year.
	 * @param year - the year.
	 * @return a line chart 
	 */
	public LineChart getLineChart(int year) {
		//Sets the chart
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();

		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("Income per month in " + year);

		XYChart.Series<String, Number> data = new XYChart.Series<>();

		// Creates new months objects
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
			//Checks if sale is between the dates and adds to the chart if it is
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

		// Adds months to a chart
		for (Month m : months) {
			data.getData().add(new XYChart.Data(m.getName(), m.getTotal()));
		}

		lineChart.getData().add(data);

		return lineChart; //The line chart objects

	}



}

/**
 * Class for dates
 */
class Month {

	private String name; //Name of the month
 	private int index; //Number of the month
	private double sculptureProfit; //Profit from sculpture sales
	private double paintingsProfit; //Profit from painting sales
	private double total; //Total profit from sales

	/**
	 * A constructor for a new Month object.
	 */
	public Month(String name, int index) {

		this.name = name;
		this.index = index;

	}

	/**
	 * Method to return the of the month.
	 * @return String - name of the month e.g. January.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the same of the month.
	 * @param String - name of the month.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Method to get the index of the month.
	 * @return int - index of the month.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Method to set the index of a month.
	 * @param int - index of the month.
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Method to get the profit from sculpture sales.
	 * @return double - profit from sulptures.
	 */
	public double getSculptureProfit() {
		return sculptureProfit;
	}

	/**
	 * Method to set the profit made by sculpture sales.
	 * @param double - sculpture profit.
	 */
	public void setSculptureProfit(double sculptureProfit) {
		this.sculptureProfit = sculptureProfit;
	}

	/**
	 * Method to return the profit made by painting sales.
	 * @return double - painting profit.
	 */
	public double getPaintingsProfit() {
		return paintingsProfit;
	}

	/**
	 * Method to set the profit made by painting sales.
	 * @param double - painting profit.
	 */
	public void setPaintingsProfit(double paintingsProfit) {
		this.paintingsProfit = paintingsProfit;
	}

	/**
	 * Method to work out a sum of painting profits.
	 * @param double - profit from sale.
	 */
	public void addPaintingsProfit(double profit) {
		paintingsProfit += profit;
	}
	
	/**
	 * Method to work out a sum of painting profits.
	 * @param double - profit from sale.
	 */
	public void addSculpturesProfit(double profit) {
		sculptureProfit += profit;
	}
	/**
	 * Method to get the total profits from sales.
	 * @return double - total profits.
	 */
	public double getTotal() {
		return sculptureProfit + paintingsProfit;
	}

	/**
	 * Method to set the total profits from sales.
	 * @param double - total profits.
	 */
	public void setTotal(double total) {
		this.total = total;
	}

}
