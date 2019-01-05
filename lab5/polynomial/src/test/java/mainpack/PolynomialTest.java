package mainpack;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

public class PolynomialTest {

    @Test
    public void checkIfChartPointsAreCalculatedCorrectlyTest() {
        Double beginX = -10.0;
        Double endX = 10.0;
        Integer coeffAmount = 3;
        Double a_0 = 1.0;
        Double a_1 = 0.0;
        Double a_2 = 2.0;
        Double calcJump = 1.0;
        Polynomial polynomial = new Polynomial();
        polynomial.setBeginXRange(beginX);
        polynomial.setEndXRange(endX);
        polynomial.setCoefficientsAmount(coeffAmount);
        polynomial.changeCoefficient(0, a_0);
        polynomial.changeCoefficient(1, a_1);
        polynomial.changeCoefficient(2, a_2);
        polynomial.setCalculationJump(calcJump);
        polynomial.calculateChartPoints();

        ObservableList<XYChart.Data<Double, Double>> data = polynomial.getSeries().getData();

        ArrayList<Double> xValues = new ArrayList<>();
        for(int i = 0; i < data.size(); ++i)
            xValues.add(data.get(i).getXValue());

        ArrayList<Double> yDataValues = new ArrayList<>();
        for(int i = 0; i < data.size(); ++i)
            yDataValues.add(data.get(i).getYValue());

        ArrayList<Double> yValues = new ArrayList<>();
        for(Double x: xValues)
            yValues.add(a_0 * Math.pow(x, 2) + a_1 * x + a_2); //formula

        for(int i = 0; i < xValues.size(); ++i)
            assertEquals(yDataValues.get(i), yValues.get(i));
    }
}
