package mainpack;

import java.util.ArrayList;
/*
    Works so-so, there are breaks sometimes, as in periods when everyone thinks
    and often only 1 eats at a time
*/

public class MainClass {
    public static void main(String[] argv) {
        ArrayList<Philosopher> philosophers = new ArrayList<>();
        ArrayList<Fork> forks = new ArrayList<>();
        for (int i = 0; i < 5; ++i)
            forks.add(new Fork(i));

        for (int i = 0; i < 5; ++i) {
            philosophers.add(new Philosopher(i, forks.get(i % 5), forks.get((i + 1) % 5)));

            Thread thread = new Thread(philosophers.get(i));
            thread.start();
        }
    }
}
