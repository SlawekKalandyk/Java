package Mainpack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GuiFile.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("First try");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
