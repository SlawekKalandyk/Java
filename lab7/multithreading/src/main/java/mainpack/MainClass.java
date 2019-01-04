package mainpack;

import java.util.concurrent.Semaphore;

/*
    This app is alternating between generating a new line in a file and calculating
    a function's value for every number in that line, up to 100 lines.
    The alternative is to use only 1 semaphor, which unlocks each line after generating it
    for calculations. The time of execution is comparable to the previous implementation though
    (to check, comment lines with sem1.acquire/sem1.release in MaxMultiThread and GenerateRandoms).
    I couldn't think of any other sensible way to implement threads here.

    Sometimes errors appear, though I don't know why. Sem2 acquire/release which is
    blocking MMT from reading a non-existent file should stop them now.
 */

public class MainClass {
    public static void main(String[] argv) {
        Semaphore sem1 = new Semaphore(1);
        Semaphore sem2 = new Semaphore(0);
        GenerateRandoms generateRandoms = new GenerateRandoms(100, 10000, "randoms", sem1, sem2);
        Thread grThread = new Thread(generateRandoms);

        MaxMultiThread maxMultiThread = new MaxMultiThread(100, "randoms", sem1, sem2);
        Thread mmtThread = new Thread(maxMultiThread);

        grThread.start();
        mmtThread.start();
    }
}
