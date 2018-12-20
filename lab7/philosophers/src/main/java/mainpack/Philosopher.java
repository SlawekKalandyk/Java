package mainpack;

public class Philosopher implements Runnable {
    private Fork rightFork;
    private Fork leftFork;
    private Integer philosopherId;
    private int interval = 2000;

    public Philosopher(Integer philosopherId, Fork rightFork, Fork leftFork) {
        this.philosopherId = philosopherId;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();

                if(!leftFork.isUsed() && !rightFork.isUsed()) {
                    leftFork.use();
                    rightFork.use();

                    eat();

                    leftFork.putDown();
                    rightFork.putDown();
                }
            }
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
            ie.printStackTrace();
            return;
        }
    }

    public void think() throws InterruptedException{
        //System.out.println("Philosopher " + philosopherId.toString() + " is thinking");
        Thread.sleep(interval);
    }

    public void  eat() throws InterruptedException {
        System.out.println(System.currentTimeMillis() + ": Philosopher " + philosopherId +
                " is eating with forks " + rightFork.getForkId().toString() +
                " and " + leftFork.getForkId().toString());
        Thread.sleep(interval);
    }
}
