package mainpack;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class example extends Application {
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 800, Color.BLACK);

        Rectangle r = new Rectangle(25,25,100,100);
        r.setFill(Color.BLUE);
        root.getChildren().add(r);

        moveRectangleOnMouseHold(scene, r, new TranslateTransition(Duration.millis(2000), r));


        stage.setScene(scene);
        stage.show();
    }

    private void moveRectangleOnMouseHold(Scene scene, final Rectangle rect, TranslateTransition transition) {
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!event.isControlDown()) {
                    rect.setX(event.getSceneX() - rect.getWidth()/2);
                    rect.setY(event.getSceneY() - rect.getHeight()/2);
                }
            }
        });
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(!event.isControlDown()) {
                    transition.setToX(event.getSceneX() - rect.getX() - rect.getWidth()/2);
                    transition.setToY(event.getSceneY() - rect.getY() - rect.getHeight()/2);
                    transition.playFromStart();
                }
            }
        });
    }
}
