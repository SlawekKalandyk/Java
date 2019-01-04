package mainpack;

import javafx.scene.control.TextField;

public class Coefficient {
    private String index;
    private Double coefficientValue;

    public Coefficient() {}

    public Coefficient(Integer index, Double coefficientValue) {
        this.index = "a" + index;
        this.coefficientValue = coefficientValue;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public Double getCoefficientValue() {
        return coefficientValue;
    }

    public void setCoefficientValue(Double coefficientValue) {
        this.coefficientValue = coefficientValue;
    }
}
