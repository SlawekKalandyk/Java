package mainpack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyCircle extends MyShape {
    public MyCircle(int size1) {
        size.add(size1);
    }

    @Override
    public void draw() {

    }

    @Override
    public void draw(Pane pane) {
        Circle circle = new Circle(size.get(0), Color.RED);
        circle.relocate(0.0, 0.0);
        mouseDragging(circle);
        pane.getChildren().add(circle);
    }
}
