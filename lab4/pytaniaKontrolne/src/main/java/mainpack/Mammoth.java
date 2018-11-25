package mainpack;

public class Mammoth {
    public void eat(Food food) {
        if( food instanceof Meat) {
            throw new InadequateFoodException("I don't like meat");
        }
    }
}
