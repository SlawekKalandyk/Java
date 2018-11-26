package mainpack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    public TextField peselInput;
    @FXML
    public TextField nameInput;
    @FXML
    public TextField surnameInput;
    @FXML
    public TextField phoneInput;
    @FXML
    public TextField peselFilterInput;
    @FXML
    public TextField nameFilterInput;
    @FXML
    public TextField surnameFilterInput;
    @FXML
    public TextField phoneFilterInput;
    @FXML
    public TableView peopleTableView;
    @FXML
    public TableColumn peselColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn surnameColumn;
    @FXML
    public TableColumn phoneColumn;
    @FXML
    public TableColumn deleteColumn;
    @FXML
    public Button addButton;
    @FXML
    public Button filterButton;

    private PeopleDB peopleDataBase = new PeopleDB();

    public Controller() {
    }

    public void initialize() {
        peselColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Pesel"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Surname"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Phone"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        deleteColumn.setCellFactory(new DeleteButtonCellFactory());

        peopleTableView.getItems().add(new Person("John Doe", "985 734 934", "95672857263"));
    }
    /*
    TODO: detailed conditions for adding a new person (like pesel validation)
     */
    public void onNewPersonAdded(ActionEvent actionEvent) {
        String pesel = peselInput.getText();
        String name = nameInput.getText();
        String surname = surnameInput.getText();
        String phone = phoneInput.getText();

        if(pesel.isEmpty() || name.isEmpty() || surname.isEmpty() || phone.isEmpty())
            return;
        else {
            Person newPerson = new Person(name, surname, phone, pesel);
            peopleDataBase.add(newPerson);
            peopleTableView.getItems().add(newPerson);
        }

        peselInput.clear();
        nameInput.clear();
        surnameInput.clear();
        phoneInput.clear();
    }

    public void onFilterButtonClicked(ActionEvent actionEvent) {

    }
}
