package Mainpack;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Controller {
    @FXML
    public TextField inputField;
    @FXML
    public TextField columnAmountField;
    @FXML
    public Label columnAmountLabel;
    @FXML
    public ListView dataListView;
    @FXML
    private BarChart<String, Number> histogramChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    private XYChart.Series series1 = new XYChart.Series();
    private ArrayList<Integer> counts = new ArrayList<>();
    private ArrayList<Double> dataList = new ArrayList<>();
    private Integer columns = 10;
    private Double minData = 0.0;
    private Double maxData = 0.0;
    private ArrayList<Double> intervals = new ArrayList<>();

    public Controller() {
    }

    public void initialize() {
        xAxis.setLabel("DataSpread");
        yAxis.setLabel("Count");
        series1.setName("Histogram");
        columnAmountLabel.setText(columns.toString());

        dataListView.getItems().addListener(new ListChangeListener<Double>() {
            @Override
            public void onChanged(Change c) {
                while(c.next()) {
                    if(c.wasRemoved()) {
                        int removedFrom = c.getFrom();
                        int removedTo = c.getTo();
                        for(int i = removedFrom; i <= removedTo; ++i) {
                            removeFromHistogram(i);
                        }
                    }
                }
            }
        });
        dataListView.setCellFactory(new dataListCellFactory());

        histogramChart.getData().add(series1);
    }

    public void inputData(ActionEvent actionEvent) {
        Double input = Double.parseDouble(inputField.getCharacters().toString());

        for (int i = 0; true; ++i) {
            if (i == dataList.size() || dataList.get(i) > input) {
                dataList.add(i, input);
                dataListView.getItems().add(i, input);
                break;
            }
        }

        minData = dataList.get(0);
        maxData = dataList.get(dataList.size() - 1);

        if (counts.isEmpty()) {
            columnAmountLabel.setText(columns.toString());
            fillHistogramWithData();
            columnAmountField.setEditable(true);
        } else {
            if (input >= maxData || input <= minData)
                fillHistogramWithData();
            else
                insertIntoHistogram(input);
        }

        inputField.clear();
    }

    public void changeColumnAmount(ActionEvent actionEvent) {
        columns = Integer.parseInt(columnAmountField.getCharacters().toString());
        columnAmountLabel.setText(columns.toString());
        fillHistogramWithData();
        columnAmountField.clear();
    }

    private void fillHistogramWithData() {
        Double singleColumn = Math.ceil((maxData + 1 - minData) / columns);
        intervals.clear();
        intervals.add(minData);

        for (int i = 1; i <= columns; ++i) {
            Double temp = intervals.get((i - 1) * 2) + singleColumn;
            intervals.add(temp - 1);
            intervals.add(temp);
        }
        intervals.remove(intervals.size() - 1);

        counts.clear();
        for (int i = 0; i < columns; ++i)
            counts.add(0);

        int j = 0;
        for (int i = 1; i < intervals.size(); i += 2) {
            int counter = 0;
            while (j < dataList.size() && i < intervals.size()
                    && dataList.get(j) <= intervals.get(i)) {
                ++counter;
                ++j;
            }
            counts.set(i / 2, counts.get(i / 2) + counter);
        }

        updateChart();
    }

    public void insertIntoHistogram(Double input) {
        int i = 0;
        while (input > intervals.get(i))
            ++i;
        counts.set(i / 2, counts.get(i / 2) + 1);

        updateChart();
    }

    public void updateChart() {
        series1.getData().clear();
        for (int i = 1; i < intervals.size(); i += 2) {
            String interval = intervals.get(i - 1).toString() +
                    " - " + intervals.get(i).toString();
            series1.getData().add(new XYChart.Data<>(interval, counts.get((i - 1) / 2)));
        }
    }

    public void removeFromHistogram(Double input) {
        int i = 0;
        while (input > intervals.get(i))
            ++i;
        counts.set(i / 2, counts.get(i / 2) - 1);
        dataList.remove(input);

        updateChart();
    }

    public void removeFromHistogram(int index) {
        int i = 0;
        while (dataList.get(index) > intervals.get(i))
            ++i;
        counts.set(i / 2, counts.get(i / 2) - 1);
        dataList.remove(index);

        updateChart();
    }
}