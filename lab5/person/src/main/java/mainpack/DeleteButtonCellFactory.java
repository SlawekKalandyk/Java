package mainpack;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class DeleteButtonCellFactory implements Callback<TableColumn<Person, String>, TableCell<Person, String>> {
    @Override
    public TableCell<Person, String> call(TableColumn<Person, String> param) {
        return new DeleteButtonCell();
    }
}
