package mainpack;

import javafx.scene.chart.XYChart;
import javafx.scene.shape.Rectangle;

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
        ArrayList<Double> xPoints = new ArrayList<>();
        Double point = beginXRange - calculationJump;
        xPoints.add(point);
        while (point < endXRange + calculationJump) {
            Double temp = 0.0;

            /*
                It's a_0 * x^n + a_1 * x^(n-1) + ...
             */
            for (int i = 0; i < coefficients.size(); ++i)
                temp += coefficients.get(i).getCoefficientValue() * Math.pow(point, coefficients.size() - 1 - i);

            chartPoints.add(temp);
            point += calculationJump;
            xPoints.add(point);
        }

        /*
        Adding data to series has to be like this to show the lines without dots,
        createSymbols="false" creates a weird bug where some points jump straight to y = 0
        where they shouldn't, even though their value is alright in chartPoints
         */
        for (int i = 0; i < chartPoints.size(); ++i) {
            XYChart.Data newData = new XYChart.Data<>(
                    xPoints.get(i), chartPoints.get(i));
            Rectangle rect = new Rectangle(0,0);
            rect.setVisible(false);
            newData.setNode(rect);
            series.getData().add(newData);
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
