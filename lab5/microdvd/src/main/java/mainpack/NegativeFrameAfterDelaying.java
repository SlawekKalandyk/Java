package mainpack;

public class NegativeFrameAfterDelaying extends Exception {
    public NegativeFrameAfterDelaying() {
        super("Delayed time results in a negative number");
    }
}
