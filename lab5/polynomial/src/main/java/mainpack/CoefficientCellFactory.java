package mainpack;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class CoefficientCellFactory implements Callback<ListView<Double>, ListCell<Double>> {

    @Override
    public ListCell<Double> call(ListView<Double> param) {
        return new CoefficientCell();
    }
}

