package mainpack;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Polynomial {
    private Double calculationJump = 1.0;
    private Integer coefficientsAmount = 0;
    private ArrayList<Coefficient> coefficients = new ArrayList<>();
    private ArrayList<Double> chartPoints = new ArrayList<>();
    private Double beginXRange = -5.0;
    private Double endXRange = 5.0;
    private Double beginYrange = -5.0;
    private Double endYrange = 5.0;
    private XYChart.Series series = new XYChart.Series();

    public Polynomial() {
    }

    public void calculateChartPoints() {
        series.getData().clear();
        chartPoints.clear();

        Double point = beginXRange;
        while (point <= endXRange) {
            Double temp = 0.0;

            for (int i = 0; i < coefficients.size(); ++i) {
                temp += coefficients.get(i).getCoefficientValue() * Math.pow(point, i);
            }

            chartPoints.add(temp);
            point += calculationJump;
        }

        for (int i = 0; i < chartPoints.size(); ++i) {
            series.getData().add(new XYChart.Data<>(
                    beginXRange + i * calculationJump, chartPoints.get(i)));
        }
    }

    public void changeCoefficient(int index, Double newCoefficient) {
        if(index < coefficients.size())
            coefficients.remove(index);
        coefficients.add(index, new Coefficient(index, newCoefficient));
        coefficientsAmount = coefficients.size();
    }

    public Double getCalculationJump() {
        return calculationJump;
    }

    public void setCalculationJump(Double calculationJump) {
        this.calculationJump = calculationJump;
    }

    public Double getBeginXRange() {
        return beginXRange;
    }

    public void setBeginXRange(Double beginXRange) {
        this.beginXRange = beginXRange;
    }

    public Double getEndXRange() {
        return endXRange;
    }

    public void setEndXRange(Double endXRange) {
        this.endXRange = endXRange;

    }

    public Double getBeginYrange() {
        return beginYrange;
    }

    public void setBeginYrange(Double beginYrange) {
        this.beginYrange = beginYrange;
    }

    public Double getEndYrange() {
        return endYrange;
    }

    public void setEndYrange(Double endYrange) {
        this.endYrange = endYrange;
    }

    public Integer getCoefficientsAmount() {
        return coefficientsAmount;
    }

    public void setCoefficientsAmount(int coefficientsAmount) {
        for(int i = coefficientsAmount; i < this.coefficientsAmount; ++i)
            coefficients.remove(coefficientsAmount);
        this.coefficientsAmount = coefficientsAmount;
    }

    public ArrayList<Coefficient> getCoefficients() {
        return coefficients;
    }

    public XYChart.Series getSeries() {
        return series;
    }

}
