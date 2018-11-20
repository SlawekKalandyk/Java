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
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

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

    private Histogram histogram = new Histogram();

    public Controller() {
    }

    public void initialize() {
        xAxis.setLabel("DataSpread");
        yAxis.setLabel("Count");
        
        /* for 1st input it doesn't show '1' on yAxis
        yAxis.setTickLabelFormatter(new NumberStringConverter() {
            @Override
            public String toString(Number number) {
                if (number.intValue() == number.doubleValue())
                    return "" + number.intValue();

                return "";
            }
        });*/

        columnAmountLabel.setText(histogram.getColumns().toString());

        dataListView.getItems().addListener(new ListChangeListener<Double>() {
            @Override
            public void onChanged(Change c) {
                while (c.next()) {
                    if (c.wasRemoved()) {
                        int removedFrom = c.getFrom();
                        int removedTo = c.getTo();
                        for (int i = removedFrom; i <= removedTo; ++i) {
                            histogram.removeFromHistogram(i);
                        }
                    }
                }
            }
        });
        dataListView.setCellFactory(new dataListCellFactory());

        histogramChart.getData().add(histogram.getSeries());
    }

    public void inputData(ActionEvent actionEvent) {
        Double input = Double.parseDouble(inputField.getCharacters().toString());

        for (int i = 0; true; ++i) {
            if (i == histogram.getDataList().size() || histogram.getDataList().get(i) > input) {
                histogram.getDataList().add(i, input);
                dataListView.getItems().add(i, input);
                break;
            }
        }

        if (histogram.getCounts().isEmpty())
            histogram.fillHistogramWithData();
        else {
            if (input >= histogram.getMaxData() || input <= histogram.getMinData())
                histogram.fillHistogramWithData();
            else
                histogram.insertIntoHistogram(input);
        }

        inputField.clear();
    }

    public void changeColumnAmount(ActionEvent actionEvent) {
        histogram.setColumns(Integer.parseInt(columnAmountField.getCharacters().toString()));
        columnAmountLabel.setText(histogram.getColumns().toString());

        if (histogram.getDataList().size() > 0)
            histogram.fillHistogramWithData();

        columnAmountField.clear();
    }
}