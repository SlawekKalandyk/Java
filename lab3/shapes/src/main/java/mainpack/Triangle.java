package mainpack;

public class Triangle extends Shape {
    public void draw() {
        int height = 5;
        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < i + 1; ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }
}
