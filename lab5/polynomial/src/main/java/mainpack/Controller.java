package mainpack;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Controller {
    @FXML
    public LineChart polynomialChart;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public NumberAxis yAxis;

    Polynomial polynomial = new Polynomial();

    public Controller() {
    }

    public void initialize() {
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
    }
}
