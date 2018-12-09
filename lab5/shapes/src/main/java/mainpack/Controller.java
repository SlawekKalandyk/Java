package mainpack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Controller {
    @FXML
    public ChoiceBox shapeChoiceBox;
    @FXML
    public Button drawButton;
    @FXML
    public TextField size1Input;
    @FXML
    public TextField size2Input;
    @FXML
    public Pane drawPane;
    @FXML
    public Button clearButton;

    private ArrayList<MyShape> shapeList = new ArrayList<>();

    public Controller() {

    }

    public void initialize() {
        drawPane.setStyle("-fx-background-color: Black;");
    }

    public void drawOnButtonClick(ActionEvent actionEvent) {
        if (shapeChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Rectangle"))
            shapeList.add(new MyRectangle(Integer.parseInt(size1Input.getText()), Integer.parseInt(size2Input.getText())));
        else if (shapeChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Square"))
            shapeList.add(new MySquare(Integer.parseInt(size1Input.getText())));
        else if (shapeChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Circle"))
            shapeList.add(new MyCircle(Integer.parseInt(size1Input.getText())));
        else if (shapeChoiceBox.getSelectionModel().getSelectedItem().toString().equals("Triangle"))
            shapeList.add(new MyTriangle(Integer.parseInt(size1Input.getText())));

        size1Input.clear();
        size2Input.clear();

        shapeList.get(shapeList.size() - 1).draw(drawPane);
    }

    public void clearPane(ActionEvent actionEvent) {
        drawPane.getChildren().clear();
    }
}
