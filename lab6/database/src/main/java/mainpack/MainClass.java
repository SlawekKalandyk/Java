package mainpack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/GuiFile.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Book Database");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }

}
