package newpack;

import java.util.LinkedList;

public class MainClass {
    public static void main(String[] argv) {
        Kadry kadra = new Kadry();
        kadra.dodajPracownika(new PracownikEtatowy("1", 2500));
        kadra.dodajPracownika(new PracownikEtatowy("2", 5604));
        kadra.dodajPracownika(new PracownikEtatowy("3", 2340));
        kadra.dodajPracownika(new Student("4", 0));

        for(Pracownik pracownik : kadra.sortowaniePracownikow()) {
            System.out.println(pracownik.getPeselStr() + " " + pracownik.getWynagrodzenieBrutto());
        }
    }
}
