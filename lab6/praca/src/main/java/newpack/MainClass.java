package newpack;

import java.util.LinkedList;

public class MainClass {
    public static void main(String[] argv) {
        Kadry kadra = new Kadry();
        kadra.dodajPracownika(new Student("98060902141", 2141));
        kadra.wyswietlZawartoscTabeli();
        System.out.println("----------");
        kadra.zmienWynagrodzenieBruttoPracownika("98060902141", 21380.0);
        kadra.wyswietlZawartoscTabeli();
        System.out.println("----------");
        kadra.usunPracownika("98060902141");
        kadra.wyswietlZawartoscTabeli();
        System.out.println("----------");
        kadra.zamknijPolaczenie();
    }
}
