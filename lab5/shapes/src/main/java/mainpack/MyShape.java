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
    private String name;

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
            /* TODO: boundaries */
            @Override
            public void handle(MouseEvent event) {
                if (    mousePosition.get().getX() > shape.getParent().getLayoutX() &&
                        mousePosition.get().getX() < shape.getParent().getLayoutX() + shape.getScene().getWidth() &&
                        mousePosition.get().getY() > shape.getParent().getLayoutY() &&
                        mousePosition.get().getY() < shape.getParent().getLayoutY() + shape.getScene().getHeight()) {

                    double deltaX = event.getSceneX() - mousePosition.get().getX();
                    double deltaY = event.getSceneY() - mousePosition.get().getY();
                    shape.setLayoutX(shape.getLayoutX() + deltaX);
                    shape.setLayoutY(shape.getLayoutY() + deltaY);
                    mousePosition.set(new Point2D(event.getSceneX(), event.getSceneY()));

                }
            }
        });
    }
}
