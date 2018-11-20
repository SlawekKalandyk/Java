package Mainpack;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;


public class dataListCell extends ListCell<Double> {
    private Button deleteButton = new Button("Delete");
    private Label cellLabel = new Label();
    private HBox cell = new HBox();
    private Region filler = new Region();

    public dataListCell() {
        deleteButton.setOnAction(event -> {
            getListView().getItems().remove(getItem());
        });
        HBox.setHgrow(filler, Priority.ALWAYS);
        cell.getChildren().addAll(cellLabel, filler, deleteButton);
        cell.setAlignment(Pos.CENTER);
    }

    @Override
    public void updateItem(Double item, boolean empty) {
        super.updateItem(item, empty);
        int index = this.getIndex();

        if (item == null || empty) {
            this.setText(null);
            this.setGraphic(null);
        } else {
            String name = (index + 1) + ":\t" + item.toString();
            cellLabel.setText(name);

            this.setText(null);
            this.setGraphic(cell);
        }
    }
}
