public class MainClass {
    public static void main(String[] argv) {
        Punkt3D punkt1 = new Punkt3D(1, 0, 0.5);
        Punkt3D punkt2 = new Punkt3D(0, 0, 1);
        System.out.println(punkt1.distance(punkt2));
    }
}