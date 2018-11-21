package mainpack;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class CoefficientCell extends ListCell<Double> {
    private Label cellLabel = new Label();
    private HBox cell = new HBox();
    private TextField coeffInput = new TextField();

    public CoefficientCell() {
        cell.getChildren().addAll(cellLabel, coeffInput);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        int index = this.getIndex();

        if (item == null || empty) {
            this.setText(null);
            this.setGraphic(null);
        } else {
            String coeffIndex = "a" + this.getIndex() + ":\t";
            cellLabel.setText(coeffIndex);

            this.setText(null);
            this.setGraphic(cell);
        }
    }
}
