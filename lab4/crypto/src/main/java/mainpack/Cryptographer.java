package mainpack;

import java.io.*;
import java.util.ArrayList;

public class Cryptographer {
    public static void cryptfile(String inputFile, String outputFile, String algorithm) {
        ArrayList<String> lines = new ArrayList<>(FileReadAndWrite.fileRead(inputFile));
        ArrayList<String> processedLines = new ArrayList<>();

        ROT11 rot11 = new ROT11();
        Polybius polybius = new Polybius();

        for (String line : lines) {
            String processedLine = "";
            line += " ";
            for (int i = 0; i < line.length(); ++i) {
                int start = i;
                while (line.charAt(i) != ' ')
                    ++i;
                if (algorithm.equals("rot11")) {
                    processedLine += rot11.crypt(line.substring(start, i));
                    processedLine += " ";
                } else if (algorithm.equals("polybius")) {
                    processedLine += polybius.crypt(line.substring(start, i));
                }

            }
            processedLines.add(processedLine);
        }

        FileReadAndWrite.fileWrite(processedLines, outputFile);
    }

    public static void decryptfile(String inputFile, String outputFile, String algorithm) {
        ArrayList<String> lines = new ArrayList<>(FileReadAndWrite.fileRead(inputFile));
        ArrayList<String> processedLines = new ArrayList<>();
        ROT11 rot11 = new ROT11();
        Polybius polybius = new Polybius();

        for (String line : lines) {
            String processedLine = "";
            line += " ";

            for (int i = 0; i < line.length(); ++i) {
                int start = i;
                while (line.charAt(i) != ' ')
                    ++i;
                if (algorithm.equals("rot11")) {
                    processedLine += rot11.decrypt(line.substring(start, i));
                    processedLine += " ";
                } else if (algorithm.equals("polybius"))
                    processedLine += polybius.decrypt(line.substring(start, i));

            }

            processedLines.add(processedLine.substring(0, processedLine.length() - 1));
        }

        FileReadAndWrite.fileWrite(processedLines, outputFile);
    }
}
