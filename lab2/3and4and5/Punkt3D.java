public class Punkt3D extends Punkt2D {
    private double _z;

    public Punkt3D(double x, double y, double z) {
        super(x, y);
        _z = z;
    }

    public double distance(Punkt3D punkt) {
        return Math.sqrt(Math.pow(this.getX() - punkt.getX(), 2) + Math.pow(this.getY() - punkt.getY(), 2) + Math.pow(this.getZ() - punkt.getZ(), 2));
    }

    public double getZ() {
        return _z;
    }

    public void setZ(double z) {
        _z = z;
    }
}