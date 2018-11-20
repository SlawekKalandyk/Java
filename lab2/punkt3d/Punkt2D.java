import java.lang.*;

public class Punkt2D {
    private double _x, _y;

    public Punkt2D(double x, double y) {
        _x = x;
        _y = y;
    }

    public double distance(Punkt2D punkt) {
        return Math.sqrt(Math.pow(this.getX() - punkt.getX(), 2) + Math.pow(this.getY() - punkt.getY(), 2));
    }
    
    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void setX(double x) {
        _x = x;
    }

    public void setY(double y) {
        _y = y;
    }

}