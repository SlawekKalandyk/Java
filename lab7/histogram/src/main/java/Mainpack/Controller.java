package Mainpack;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Random;

public class Controller {
    @FXML
    public TextField inputField;
    @FXML
    public TextField columnAmountField;
    @FXML
    public Label columnAmountLabel;
    @FXML
    private BarChart<String, Number> histogramChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private XYChart.Series series1 = new XYChart.Series();
    private ArrayList<Integer> counts = new ArrayList<>();
    private ArrayList<Integer> dataList = new ArrayList<>();
    private Integer columns = 0;
    private Integer maxData = 0;

    public Controller() {
    }

    public void initialize() {
        xAxis.setLabel("DataSpread");
        yAxis.setLabel("Count");
        series1.setName("Histogram");
        columnAmountLabel.setText(columns.toString());

/*
        series1.getData().add(new XYChart.Data<>("0-10", counts.get(0)));

        series1.getData().add(new XYChart.Data<>("11-20", counts.get(1)));

        series1.getData().add(new XYChart.Data<>("21-30", counts.get(2)));

        series1.getData().add(new XYChart.Data<>("31-40", counts.get(3)));

        series1.getData().add(new XYChart.Data<>("41-50", counts.get(4)));
*/

        histogramChart.getData().add(series1);

    }

    public void inputData(ActionEvent actionEvent) {
        Integer input = Integer.parseInt(inputField.getCharacters().toString());

        for(int i = 0; true; ++i) {
            if(i == dataList.size() || dataList.get(i) > input) {
                dataList.add(input);
                break;
            }
        }

        if(counts.isEmpty()) {
            maxData = input;
            columns += 1;
            columnAmountLabel.setText(columns.toString());
            String interval = "0 - " + maxData.toString();
            counts.add(1);
            series1.getData().add(new XYChart.Data<>(interval, counts.get(0)));
            columnAmountField.setEditable(true);
        } else {
            if (input > maxData)
                maxData = input;

            int singleColumn = (int) Math.ceil(maxData / columns);

        }

        //series1.getData().add(new XYChart.Data<>("41-50", counts.get(4)));

        /*
        counts.set(0, counts.get(0) + 1);
        series1.getData().remove(0);
        series1.getData().add(0, new XYChart.Data<>("0-10", counts.get(0)));
        */
        inputField.clear();
    }

    public void changeColumnAmount(ActionEvent actionEvent) {
        columns = Integer.parseInt(columnAmountField.getCharacters().toString());
        columnAmountLabel.setText(columns.toString());



        columnAmountField.clear();
    }
}
