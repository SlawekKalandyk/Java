package mainpack;

public class InvalidInputFormat extends Exception {
    public InvalidInputFormat() {
        super("Input time is not comprised of only numbers");
    }
}