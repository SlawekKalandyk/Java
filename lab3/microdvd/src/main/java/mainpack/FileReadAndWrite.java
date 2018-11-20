package mainpack;

import java.util.*;
import java.io.*;

public class FileReadAndWrite {
    public static ArrayList<String> fileRead(String inputFile) {
        ArrayList<String> lines = new ArrayList<>();
        String line = null;

        try {
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + inputFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + inputFile + "'");
        }

        return lines;
    }

    public static void fileWrite(ArrayList<String> lines, String outputFile) {
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + outputFile + "'");
        }
    }
}
