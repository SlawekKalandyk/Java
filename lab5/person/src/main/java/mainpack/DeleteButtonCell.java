package mainpack;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class DeleteButtonCell extends TableCell<Person, String> {
    private HBox cell = new HBox();
    private final Button deleteButton = new Button("Delete");

    public DeleteButtonCell() {
        cell.getChildren().add(deleteButton);
        cell.getStyleClass().add("deleteButtons");

        deleteButton.setOnAction(event ->
                getTableView().getItems().remove(getIndex()));
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty)
            setGraphic(null);
        else
            setGraphic(cell);

        setText(null);
    }
}
