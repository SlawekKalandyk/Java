package mainpack;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public abstract class MyShape {
    protected ArrayList<Integer> size = new ArrayList<>();

    public abstract void draw();

    public abstract void draw(Pane pane);

    protected void mouseDragging(Shape shape) {
        final ObjectProperty<Point2D> mousePosition = new SimpleObjectProperty<>();

        shape.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));
            }
        });

        shape.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double deltaX = event.getSceneX() - mousePosition.get().getX();
                double deltaY = event.getSceneY() - mousePosition.get().getY();
                shape.setLayoutX(shape.getLayoutX() + deltaX);
                shape.setLayoutY(shape.getLayoutY() + deltaY);
                mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

                if (shape.getLayoutX() < 0)
                    shape.setLayoutX(0);
                else if (shape.getLayoutX() > shape.getScene().getWidth() - shape.getLayoutBounds().getWidth())
                    shape.setLayoutX(shape.getScene().getWidth() - shape.getLayoutBounds().getWidth());
                if (shape.getLayoutY() < 0)
                    shape.setLayoutY(0);
                else if (shape.getLayoutY() > shape.getScene().getHeight() - shape.getParent().getLayoutY() - shape.getLayoutBounds().getHeight())
                    shape.setLayoutY(shape.getScene().getHeight() - shape.getParent().getLayoutY() - shape.getLayoutBounds().getHeight());
            }
        });
    }
}
