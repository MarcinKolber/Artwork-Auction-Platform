import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class CreateGraphs
{
    public void makeGraph(User user)
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Artwork."); //set the x axis as artwork
        yAxis.setLabel("Price."); //set the y axis as price

        final LineChart<String, Number> lineChart =
                new LineChart<>(xAxis, yAxis); //create a new line chart with the x and y axis

        lineChart.setTitle("Artwork sales."); //set the title of the line chart to Artwork sales

        XYChart.Series series = new XYChart.Series();
        for (Artwork a : user.getArtSold()) { //for each artwork that this user has sold
            //add the artwork with its name and highest bid amount to the graph
            series.getData().add(new XYChart.Data(a.getTitle(), a.getHighestBidAmount()));
        }

    }
}
