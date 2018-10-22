package mainpack;

public class Rectangle extends Shape{
    public void draw() {
        int size1 = 5;
        int size2 = 8;
        for(int i = 0; i < size1; ++i) {
            for(int j = 0; j < size2; ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }
}
