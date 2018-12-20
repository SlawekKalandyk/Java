package mainpack;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

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
    @FXML
    public Button addToShapeListButton;
    @FXML
    public Button drawShapeListButton;
    @FXML
    public Button clearShapeListButton;

    private ArrayList<MyShape> shapeList = new ArrayList<>();

    public Controller() {
    }

    public void initialize() {
        drawPane.setStyle("-fx-background-color: Black;");

        shapeChoiceBox.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> {
                if(shapeChoiceBox.getValue().equals("Rectangle")) {
                    size2Input.setEditable(true);
                    size2Input.setPromptText("");
                    size2Input.setText("");
                } else if(shapeChoiceBox.getValue().equals("Square")
                        || shapeChoiceBox.getValue().equals("Circle")
                        || shapeChoiceBox.getValue().equals("Triangle")) {
                    size2Input.setEditable(false);
                    size2Input.setPromptText("Size 2 not needed");
                    size2Input.setText("");
                }
            });
    }

    public void drawOnButtonClick(ActionEvent actionEvent) {
        addToShapeListOnButtonClick(null);

        shapeList.get(shapeList.size() - 1).draw(drawPane);
        shapeList.remove(shapeList.size() - 1);
    }

    public void clearPane(ActionEvent actionEvent) {
        drawPane.getChildren().clear();
    }

    public void addToShapeListOnButtonClick(ActionEvent actionEvent) {
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
    }

    public void drawShapeList(ActionEvent actionEvent) {
        for(MyShape shape: shapeList) {
            shape.draw(drawPane);
        }
    }

    public void clearShapeList(ActionEvent actionEvent) {
        shapeList.clear();
    }
}
