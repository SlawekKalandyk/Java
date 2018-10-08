import java.io.*;

public class Reader{
    public static void main(String[] argv){
        InputStreamReader rd = new InputStreamReader(System.in);
        BufferedReader bfr = new BufferedReader(rd);

        try {
            System.out.print("Wpisz jakis tekst: ");
            String text = bfr.readLine();
            System.out.println("Wpisales: " + text);
        } catch(IOException e){e.printStackTrace();}

    }
}	
