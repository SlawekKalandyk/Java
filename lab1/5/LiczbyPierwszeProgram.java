import java.io.*;

public class LiczbyPierwszeProgram {
    public static void main(String[] argv) {
        System.out.println("Podaj liczbe");
        int liczba;
        try {
            InputStreamReader rd = new InputStreamReader(System.in);
            BufferedReader bfr = new BufferedReader(rd);
            liczba = Integer.parseInt(bfr.readLine());
            LiczbyPierwsze liczbyPierwsze = new LiczbyPierwsze(liczba);
            liczbyPierwsze.WypiszLiczbyPierwsze();
        } catch(IOException e){e.printStackTrace();}
    }
}