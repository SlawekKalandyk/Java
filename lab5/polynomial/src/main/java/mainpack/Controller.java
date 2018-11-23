package mainpack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class Controller {
    @FXML
    public LineChart polynomialChart;
    @FXML
    public NumberAxis xAxis;
    @FXML
    public NumberAxis yAxis;
    @FXML
    public TableView coefficientsTableView;
    @FXML
    public TableColumn indexTableColumn;
    @FXML
    public TableColumn coefficientTableColumn;
    @FXML
    public TextField coefficientsAmountInput;
    @FXML
    public Region filler;
    @FXML
    public Button calculateButton;
    @FXML
    public TextField beginXAxisInput;
    @FXML
    public TextField beginYAxisInput;
    @FXML
    public TextField endYAxisInput;
    @FXML
    public TextField endXAxisInput;
    @FXML
    public TextField calculationJumpInput;

    Polynomial polynomial = new Polynomial();

    public Controller() {
    }

    public void initialize() {
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLowerBound(polynomial.getBeginXRange());
        xAxis.setUpperBound(polynomial.getEndXRange());
        yAxis.setLowerBound(polynomial.getBeginYrange());
        yAxis.setUpperBound(polynomial.getEndYrange());

        indexTableColumn.setCellValueFactory(new PropertyValueFactory<Coefficient, String>("Index"));

        coefficientTableColumn.setCellFactory(TextFieldTableCell.<Coefficient>forTableColumn());

        polynomialChart.getData().add(polynomial.getSeries());
    }

    public void onCoefficientEdit(TableColumn.CellEditEvent cellEditEvent) {
        int position = cellEditEvent.getTablePosition().getRow();
        Double newValue = Double.parseDouble(cellEditEvent.getNewValue().toString());

        polynomial.changeCoefficient(position, newValue);
    }

    public void onCoeffAmountEntered(ActionEvent actionEvent) {
        int oldCoeffAmount = polynomial.getCoefficientsAmount();
        int newCoeffAmount = Integer.parseInt(coefficientsAmountInput.getText());

        for (int i = 0; i < newCoeffAmount; ++i)
            polynomial.changeCoefficient(i, 0.0);

        polynomial.setCoefficientsAmount(newCoeffAmount);

        coefficientsTableView.setItems(FXCollections.observableArrayList(polynomial.getCoefficients()));

        coefficientsAmountInput.setPromptText("Currently: " + polynomial.getCoefficientsAmount());
        coefficientsAmountInput.clear();
    }

    public void makeChartOnButtonClick(ActionEvent actionEvent) {
        polynomial.calculateChartPoints();
    }

    public void onBeginXAxisChanged(ActionEvent actionEvent) {
        Double newBeginXRange = Double.parseDouble(beginXAxisInput.getText());
        polynomial.setBeginXRange(newBeginXRange);
        xAxis.setLowerBound(newBeginXRange);
        polynomial.calculateChartPoints();
        beginXAxisInput.setPromptText("Currently: " + newBeginXRange);
        beginXAxisInput.clear();
    }

    public void onBeginYAxisChanged(ActionEvent actionEvent) {
        Double newBeginYRange = Double.parseDouble(beginYAxisInput.getText());
        polynomial.setBeginYrange(newBeginYRange);
        yAxis.setLowerBound(newBeginYRange);
        polynomial.calculateChartPoints();
        beginYAxisInput.setPromptText("Currently: " + newBeginYRange);
        beginYAxisInput.clear();
    }

    public void onEndXAxisChanged(ActionEvent actionEvent) {
        Double newEndXRange = Double.parseDouble(endXAxisInput.getText());
        polynomial.setEndXRange(newEndXRange);
        xAxis.setUpperBound(newEndXRange);
        polynomial.calculateChartPoints();
        endXAxisInput.setPromptText("Currently: " + newEndXRange);
        endXAxisInput.clear();
    }

    public void onEndYAxisChanged(ActionEvent actionEvent) {
        Double newEndYRange = Double.parseDouble(endYAxisInput.getText());
        polynomial.setEndYrange(newEndYRange);
        yAxis.setUpperBound(newEndYRange);
        polynomial.calculateChartPoints();
        endYAxisInput.setPromptText("Currently: " + newEndYRange);
        endYAxisInput.clear();
    }

    public void onCalculationJumpChanged(ActionEvent actionEvent) {
        Double newCalculationJump = Double.parseDouble(calculationJumpInput.getText());
        polynomial.setCalculationJump(newCalculationJump);
        polynomial.calculateChartPoints();
        calculationJumpInput.setPromptText("Currently: " + newCalculationJump);
        calculationJumpInput.clear();
    }
}
