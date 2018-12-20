package mainpack;

import java.io.*;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class GenerateRandoms implements Runnable {
    private int lineAmount;
    private int numberAmount;
    private String fileName;
    private Semaphore sem1, sem2;

    public GenerateRandoms(int lineAmount, int numberAmount, String fileName, Semaphore sem1, Semaphore sem2) {
        this.lineAmount = lineAmount;
        this.numberAmount = numberAmount;
        this.fileName = fileName;
        this.sem1 = sem1;
        this.sem2 = sem2;
    }

    private void generate() throws IOException, InterruptedException {
        File file = new File(fileName);
        file.delete();
        file.createNewFile();
        sem2.release();
        FileWriter fileWriter = new FileWriter(fileName, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        Random rand = new Random();

        for (int i = 0; i < lineAmount; ++i) {
            sem1.acquire();
            String line = "";
            for (int j = 0; j < numberAmount; ++j) {
                Integer randomNumber = rand.nextInt(500);
                line += randomNumber.toString() + " ";
            }
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            sem2.release();
        }

        bufferedWriter.close();
        fileWriter.close();
    }

    @Override
    public void run() {
        try {
            generate();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        } catch(InterruptedException iEx) {
            iEx.printStackTrace();
            Thread.currentThread().interrupt();
            return;
        }
    }
}
