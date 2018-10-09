import java.lang.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] argv) {
        LinkedList<Punkt3D> punkty = new LinkedList<>();
        int c = 0;

        while(c != 4) {
            System.out.println("1. Wczytaj punkt 3D\n2. Wyświetl wszystkie punkty\n3. Oblicz odległość\n4. Zakończ");

            try {
		    InputStreamReader rd = new InputStreamReader(System.in);
		    BufferedReader bfr = new BufferedReader(rd);
		    c = Integer.parseInt(bfr.readLine());
	        } catch(IOException e){e.printStackTrace();}

            switch(c) {
                case 1:
                    System.out.println("Podaj położenie punktu (x, y, z)");

                    int x = 0, y = 0, z = 0;
                    try {
		            InputStreamReader rd = new InputStreamReader(System.in);
		            BufferedReader bfr = new BufferedReader(rd);
		            x = Integer.parseInt(bfr.readLine());
                    y = Integer.parseInt(bfr.readLine());
                    z = Integer.parseInt(bfr.readLine());
	                } catch(IOException e){e.printStackTrace();}

                    punkty.add(new Punkt3D(x, y, z));
                    break;
                case 2:
                    System.out.println("Punkty w liście to:");

                        System.out.printf("(%f; %f; %f)\n", punkt.getX(), punkt.getY(), punkt.getZ());
                    }
                    break;
                case 3:

                    break;
                case 4:
                    break;
                default:
                    System.out.println("Podałeś złą liczbę");
                    break;
            }
        }
    }
}