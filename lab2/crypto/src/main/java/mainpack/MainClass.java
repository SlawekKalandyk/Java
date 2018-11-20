package mainpack;

import java.io.*;
import java.util.*;

public class MainClass {
    public static void main(String[] argv) {
        String inputFile = argv[0];
        String outputFile = argv[1];

        System.out.println("1. Szyfrowanie\n2. Odszyfrowanie");
        //int choice = JIn.getInt();
        String choice = argv[2];

        if (choice.equals("crypt")) {
            //if(choice == 1) {
            System.out.println("Wybierz szyfr:\n1. Wpisz rot11\n2. Wpisz polybius");
            //String choice2 = JIn.getString();
            String choice2 = argv[3];
            Cryptographer.cryptfile(inputFile, outputFile, choice2);

        } else if (choice.equals("decrypt")) {
            //} else if (choice == 2) {
            System.out.println("Wybierz szyfr:\n1. Wpisz rot11\n2. Wpisz polybius");
            //String choice2 = JIn.getString();
            String choice2 = argv[3];
            Cryptographer.decryptfile(inputFile, outputFile, choice2);
        }
    }
}
/* standard input od razu kończy program, nie wiem czemu */