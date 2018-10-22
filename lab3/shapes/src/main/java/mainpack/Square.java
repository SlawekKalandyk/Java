package mainpack;

public class Square extends Shape{
    public void draw() {
        int size = 5;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                System.out.print("x");
            }
            System.out.println();
        }
    }
}
