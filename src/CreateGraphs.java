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
        xAxis.setLabel("Artwork.");
        yAxis.setLabel("Price.");

        final LineChart<String, Number> lineChart =
                new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Artwork sales.");

        XYChart.Series series = new XYChart.Series();
        for (Artwork a : user.getArtSold()) {
            series.getData().add(new XYChart.Data(a.getTitle(), a.getHighestBidAmount()));
        }

    }
}
