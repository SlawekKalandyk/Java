package mainpack;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    public TextField filterInput;
    @FXML
    public ChoiceBox filterChoiceBox;
    @FXML
    public TextField isbnInput;
    @FXML
    public TextField bookInput;
    @FXML
    public TextField authorInput;
    @FXML
    public TextField yearInput;
    @FXML
    public Button addButton;
    @FXML
    public TableView dataBaseTableView;
    @FXML
    public TableColumn isbnColumn;
    @FXML
    public TableColumn bookColumn;
    @FXML
    public TableColumn authorColumn;
    @FXML
    public TableColumn yearColumn;

    private DatabaseCommands db = new DatabaseCommands();

    public Controller() {
    }

    public void initialize() {
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("bookName"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));

        dataBaseTableView.setItems(FXCollections.observableArrayList(db.selectAllFromTable()));
    }

    public void onFilterTextInput(KeyEvent keyEvent) {
        String filter = filterInput.getCharacters().toString();
        if (filterChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Author"))
            dataBaseTableView.setItems(FXCollections.observableArrayList(db.selectFromTableByAuthorSurname(filter)));
        else if (filterChoiceBox.getSelectionModel().getSelectedItem().toString().equals("ISBN"))
            dataBaseTableView.setItems(FXCollections.observableArrayList(db.selectFromTableByISBN(filter)));
    }

    public void onAddButtonClicked(ActionEvent actionEvent) {
        String isbn = isbnInput.getText();
        String book = bookInput.getText();
        String author = authorInput.getText();
        String year = yearInput.getText();

        if (!(isbn.isEmpty() && book.isEmpty() && author.isEmpty() && year.isEmpty())) {
            db.addBook(new Book(isbn, book, author, Integer.parseInt(year)));
            onFilterTextInput(null);

            isbnInput.clear();
            bookInput.clear();
            authorInput.clear();
            yearInput.clear();
        }
    }
}
