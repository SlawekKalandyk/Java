package mainpack;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MySquare extends MyShape {
    public MySquare(int size1) {
        size.add(size1);
    }

    public void draw() {
        for (int i = 0; i < size.get(0); ++i) {
            for (int j = 0; j < size.get(0); ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }

    @Override
    public void draw(Pane pane) {
        Rectangle square = new Rectangle(size.get(0), size.get(0), Color.BLUE);
        square.relocate(0.0, 0.0);
        mouseDragging(square);
        pane.getChildren().add(square);
    }
}
