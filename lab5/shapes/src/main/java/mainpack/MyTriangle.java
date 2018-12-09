package mainpack;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class MyTriangle extends MyShape {
    public MyTriangle(int size1) {
        size.add(size1);
    }

    public void draw() {
        for(int i = 0; i < size.get(0); ++i) {
            for(int j = 0; j < i + 1; ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }

    @Override
    public void draw(Pane pane) {
        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(
                0.0, 0.0,
                0.0, (double) size.get(0),
                (double) size.get(0), (double) size.get(0)
        );

        triangle.setFill(Color.GREEN);
        triangle.relocate(0.0, 0.0);
        mouseDragging(triangle);
        pane.getChildren().add(triangle);
    }
}
