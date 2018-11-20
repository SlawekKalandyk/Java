package Mainpack;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class dataListCellFactory implements Callback<ListView<Double>, ListCell<Double>> {
    @Override
    public ListCell<Double> call(ListView<Double> listview) {
        return new dataListCell();
    }
}
