package mainpack;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class Polynomial {
    private Double calculationJump;
    private ArrayList<Double> coefficients = new ArrayList<>();
    private ArrayList<Double> chartPoints = new ArrayList<>();
    private Double beginXRange;
    private Double endXRange;
    private Double beginYrange;
    private Double endYrange;

    public Polynomial() {

    }

    public void calculateChartPoints() {
        Double point = beginXRange;
        while (point <= endXRange) {
            Double temp = 0.0;

            for (int i = 0; i < coefficients.size(); ++i) {
                temp += coefficients.get(i) * Math.pow(point, i);
            }

            chartPoints.add(temp);
            point += calculationJump;
        }
    }

    public XYChart.Series makeChart() {
        XYChart.Series series = new XYChart.Series();

        for (int i = 0; i < chartPoints.size(); ++i) {
            series.getData().add(new XYChart.Data<>(
                    beginXRange + i * calculationJump, chartPoints.get(i)));
        }

        return series;
    }

    public void changeCoefficient(Integer index, Double newCoefficient) {
        coefficients.remove(index);
        coefficients.add(index, newCoefficient);
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

}
