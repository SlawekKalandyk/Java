package mainpack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyRectangle extends MyShape{
    public MyRectangle(int size1, int size2) {
        size.add(size1);
        size.add(size2);
    }

    public void draw() {
        for(int i = 0; i < size.get(0); ++i) {
            for(int j = 0; j < size.get(0); ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }

    @Override
    public void draw(Pane pane) {
        Rectangle rectangle = new Rectangle(size.get(0), size.get(1), Color.AZURE);
        rectangle.relocate(0.0, 0.0);
        mouseDragging(rectangle);
        pane.getChildren().add(rectangle);
    }
}
