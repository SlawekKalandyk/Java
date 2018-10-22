package mainpack;

public class MainClass {
    public static void main(String[] argv) {
        Square a = new Square();
        a.draw();
        System.out.println();
        Triangle b = new Triangle();
        b.draw();
        System.out.println();
        Rectangle c = new Rectangle();
        c.draw();
    }
}
