package mainpack;

import java.util.concurrent.Semaphore;

/*
    This app is alternating between generating a new line in a file and calculating
    a function's value for every number in that line, up to 100 lines.
    I couldn't think of other any sensible way to implement threads here.

    Sometimes errors appear, though I don't know why. Sem2 acquire/release
    blocking MMT from reading a non-existent file should stop them now.
 */

public class MainClass {
    public static void main(String[] argv) {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(0);
        GenerateRandoms generateRandoms = new GenerateRandoms(100, 10000, "randoms", sem1, sem2);
        Thread grThread= new Thread(generateRandoms);

        MaxMultiThread maxMultiThread = new MaxMultiThread(100, "randoms", sem1, sem2);
        Thread mmtThread = new Thread(maxMultiThread);

        grThread.start();
        mmtThread.start();
    }
}
