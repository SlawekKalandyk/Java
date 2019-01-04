package mainpack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.function.BiFunction;

public class MaxMultiThread implements Runnable {
    private String fileName;
    private Semaphore sem1, sem2;
    private int lineAmount;

    public MaxMultiThread(int lineAmount, String fileName, Semaphore sem1, Semaphore sem2) {
        this.lineAmount = lineAmount;
        this.fileName = fileName;
        this.sem1 = sem1;
        this.sem2 = sem2;
    }

    public void run() {
        try {
            sem2.acquire();
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            for(int j = 0; j < lineAmount; ++j) {
                sem2.acquire();
                line = bufferedReader.readLine();
                String[] numbers = line.split(" ");

                for(int i = 0; i < numbers.length; ++i)
                    calculate(this::simpleFunction, Integer.parseInt(numbers[i]), 2);

                System.out.println("Loop " + (j + 1) + " completed");
                sem1.release();
            }

            fileReader.close();
            bufferedReader.close();

        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        } catch (InterruptedException ieEx) {
            ieEx.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        }
    }

    private Integer simpleFunction(Integer arg1, Integer arg2) {
        return arg1 * arg2;
    }

    public Integer calculate(BiFunction<Integer, Integer, Integer> function,
                             Integer arg1,
                             Integer arg2) {
        return function.apply(arg1, arg2);
    }
}
