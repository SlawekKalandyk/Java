import java.io.*;

public class LiczbyPierwszeProgram {
    public static void main(String[] argv) {
        System.out.println("Podaj liczbe");
        int liczba;
        liczba = JIn.getInt();
        LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze(liczba);
        liczbyPierwsze.WypiszLiczbyPierwsze();
    }
}